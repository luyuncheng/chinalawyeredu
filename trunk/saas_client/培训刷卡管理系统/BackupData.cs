using System;
using System.Net;
using System.Xml;
using System.Data;
using System.Data.OleDb;
using System.Windows.Forms;
using System.IO;
/*using Microsoft.SharePoint;
using Microsoft.SharePoint.Utilities;
using Microsoft.SharePoint.WebPartPages;
using Microsoft.SharePoint.WebControls;*/

using System.Data.SqlClient;
using System.Configuration;

namespace ��ѵˢ������ϵͳ
{
	/// <summary>
	/// GetSPData ��ժҪ˵����
	/// </summary>
	public class BackupData
	{
        //private LXSKWS.LXSKWS SKWS;
        private ElearningInterace einterface;
		public ProgressBar StateBar;
		public Label ShowState;
        private string PhotoBaseUrl = "";
    
        public BackupData(string WebName,string UserID, string PSWord)
		{
                einterface = new ElearningInterace();
                PhotoBaseUrl = ConfigurationManager.AppSettings["PhotoBaseUrl"].ToString();
		}

		public void ShowActionInfo(string InfoStr)
		{
			ShowState.Text=InfoStr;
			ShowState.Refresh();
		}

        private string ChkPhDirectory()
        {
            string EPath = Application.ExecutablePath;

            string PhotoDirStr = EPath.Substring(0, EPath.LastIndexOf(@"\") + 1) + "photo";

            DirectoryInfo PhotoDir = new DirectoryInfo(PhotoDirStr);

            if (PhotoDir.Exists == false) 
                Directory.CreateDirectory(PhotoDirStr);
            if (!PhotoDirStr.EndsWith("\\"))
                PhotoDirStr += "\\";

            return PhotoDirStr;

        }

		public void BKLawyersInfo(bool DownloadPhoto)
		{
            ShowActionInfo("����������ʦ���Ͽ�......");
            WebClient Weber = new WebClient();

			try
			{
                
                System.Collections.Generic.List<Lawyers> lawyers = einterface.GetLawyersInfo();

                //if (DS != null)
                if(lawyers!=null&&lawyers.Count>0)
                {
                    Access SKDB = new Access();

                    //DataTable DT = DS.Tables[0];

                    //int AllCnt = DT.Rows.Count;
                    int AllCnt = lawyers.Count;

                    SKDB.ExecuteSQL("delete from Lawyers");

                    StateBar.Visible = true;
                    StateBar.Step = 100;
                    StateBar.Maximum = AllCnt;

                    int Cnt = 0, PHCnt = 0;
                    //string PhotoBaseUrl = SKWS.GetPhotoBaseUrl();

                    //foreach (DataRow DR in DT.Rows)
                    foreach(Lawyers lawyer in lawyers)
                    {
                        StateBar.Value = ++Cnt;

                        //string PhotoPath = DR["LawyerPhoto"].ToString();
                        string PhotoPath=lawyer.Photo;

                        //string SQLStr = string.Format("insert into Lawyers(bh,xm,kh,SWSName,LawCreditNo,PhotoURL) values ({0},'{1}','{2}','{3}','{4}','{5}')",
                        //                             Cnt,
                        //                             DR["XM"],
                        //                             DR["KH"].ToString() != "" ? Convert.ToInt64(DR["KH"]).ToString() : "88888" + Cnt.ToString(),
                        //                             DR["SWSName"],
                        //                             DR["LawyerID"],
                        //                             PhotoPath);

                        string SQLStr = string.Format("insert into Lawyers(bh,xm,kh,SWSName,LawCreditNo,PhotoURL,mobile) values ({0},'{1}','{2}','{3}','{4}','{5}','{6}')",
                                       lawyer.Userid,
                                       lawyer.Username,
                                       //lawyer.Cardno != "" ? Convert.ToInt64(lawyer.Cardno).ToString() : "88888" + Cnt.ToString(),
                                       lawyer.Cardno != "" ? lawyer.Cardno : "888888" + Cnt.ToString(),
                                       lawyer.Officename,
                                       lawyer.Lawyerno,
                                       PhotoPath,lawyer.Systemno);
                        SKDB.ExecuteSQL(SQLStr);
                      
                        if (DownloadPhoto && PhotoBaseUrl != "" && PhotoPath != ""&&!PhotoPath.Equals("")&&!PhotoPath.Equals("null"))
                        {
                            string DownPHFile = ChkPhDirectory() + PhotoPath.Replace("/", @"\");
                            string DownDir = DownPHFile.Substring(0, DownPHFile.LastIndexOf(@"\"));

                            //Logger.LogInfo("DownPHFile::" + DownPHFile + ",DownDir::" + DownDir);

                            if (!Directory.Exists(DownDir)) Directory.CreateDirectory(DownDir);

                            if (!PhotoBaseUrl.EndsWith("/"))
                            {
                                PhotoBaseUrl += "/";
                            }
                       
                            if (File.Exists(DownPHFile)) 
                                File.Delete(DownPHFile);
                            try
                            {
                               // Logger.LogInfo("PhotoBaseUrl + PhotoPath:::" + PhotoBaseUrl + PhotoPath + "===" + DownPHFile);
                               
                                    Weber.DownloadFile(PhotoBaseUrl + PhotoPath, DownPHFile);
                                    PHCnt++;
                            }
                            catch(Exception e) 
                            {
                                Logger.LogInfo("������ʦ��Ƭʧ��:::"+e.Message+"\r\n"+e.StackTrace);
                            }
                        }

                        ShowActionInfo(string.Format("����{0}�� ������ʦ��¼[{1}]/��Ƭ[{2}]", AllCnt, Cnt, PHCnt));

                    }

                    StateBar.Visible = false;
                    StateBar.Value = 0;
                }
			}

			catch (System.Web.Services.Protocols.SoapException ex)
			{
				MessageBox.Show("Message:\n" + ex.Message + "\nDetail:\n" + ex.Detail.InnerText + "\nStackTrace:\n" + ex.StackTrace);
			}

            ShowActionInfo("");
		}

        /// <summary>
        /// �õ���ѵ�γ����Ѿ����˲�������ѵ��
        /// </summary>
        private void BKClassRegInfo()
        {


        }
        /// <summary>
        /// �õ��ֳ���ѵ�Ŀγ���Ϣ
        /// </summary>
		public void BKLessonsInfo()
		{
            ShowActionInfo("����������ѵ�γ̿�......");

            try
			{

                //DataSet DS = SKWS.GetLessonsInfo();
                System.Collections.Generic.List<Lessons> lessons = einterface.GetLessonsInfo();
                System.Collections.Generic.List<ClassReg> classregs = einterface.GetClassRegInfo();
                int lessonlen = lessons == null ? 0 : lessons.Count;
                int classreglen = classregs == null ? 0 : classregs.Count;
                //if (DS != null)
                if (lessonlen > 0)
                {

                    Access SKDB = new Access();

             //       SKDB.ExecuteSQL("delete from ClassReg");
                    SKDB.ExecuteSQL("delete from LessonInfo");




                    //DataTable LXLessonDT = DS.Tables["LX_Lesson"];
                    //DataTable LXBMDT = DS.Tables["LX_BMLawyers"];

                    //int AllCnt=LXLessonDT.Rows.Count+LXBMDT.Rows.Count;

                    int AllCnt = lessonlen + classreglen;

                    StateBar.Visible = true;
                    StateBar.Step = 1;
                    StateBar.Maximum = AllCnt;

                    ShowActionInfo(string.Format("��ѵ��ؼ�¼����{0}", AllCnt));

                    int Cnt = 0;
                    //foreach (DataRow DR in LXLessonDT.Rows)
                    foreach(Lessons lesson in lessons)
                    {
                        SKDB.ExecuteSQL(string.Format("insert into LessonInfo(LessonID,LessonName,LessonType,LessonSDT,ReqMinutes) values ('{0}','{1}','{2}','{3}',{4})",
                                                    //DR["LessonID"], DR["LessonName"], DR["LessonState"], DR["LessonDT"], DR["SKReqTime"]));
                                                    lesson.Lessonid,lesson.Title, lesson.Lessontype,lesson.Lessondate, lesson.Reqminute));

                        StateBar.Value = ++Cnt;
                    }

                    //foreach (DataRow DR in LXBMDT.Rows)
                    foreach (ClassReg classreg in classregs)
                    {
                        SKDB.ExecuteSQL(string.Format("insert into ClassReg(LessonID,LawyerID,RegDate,RegType) values('{0}','{1}','{2}','{3}')",
                                                      //DR["LessonID"], DR["LawyerID"], DR["DoDate"], DR["DoItem"]));
                                                      classreg.Lessonid, classreg.Lawyerno, classreg.Dotime, classreg.Regtype));
                        StateBar.Value = ++Cnt;
                    }

                    StateBar.Visible = false;
                    StateBar.Value = 0;
                }
            }
			catch (System.Web.Services.Protocols.SoapException ex)
			{
				MessageBox.Show("Message:\n" + ex.Message + "\nDetail:\n" + ex.Detail.InnerText + 
					"\nStackTrace:\n" + ex.StackTrace);
			}

            ShowActionInfo("");
        }
        /// <summary>
        /// �ϴ��ɼ�
        /// </summary>
        /// <returns></returns>
        public int UploadSKRecs()
        {
            DataSet SKEptRecs = new DataSet();

            Access SKDB = new Access();
            //��ˢ�����ϴ�
            SKDB.AddTableInDataSet(SKEptRecs, "SKRecs", "select * from SKRecs where IsToRemote=false order by id");

            //if (SKWS.SetSKRecs(SKEptRecs))
            if (einterface.SetSKRecs(SKEptRecs))
            {
                int RecsNum = SKEptRecs.Tables["SKRecs"].Rows.Count;

                string UpdateRecIDs = "";

                StateBar.Visible = true;
                StateBar.Step = 1;
                StateBar.Maximum = RecsNum;

                ShowActionInfo(string.Format("�ϴ�ˢ����¼��{0}", RecsNum));

                for (int i = 0; i < RecsNum; i++)
                {
                    DataRow SKRec = SKEptRecs.Tables["SKRecs"].Rows[i];

                    UpdateRecIDs += (UpdateRecIDs == "" ? "" : ",") + SKRec["ID"].ToString();

                    StateBar.Value = i;

                }

                if (UpdateRecIDs != "")
                    SKDB.ExecuteSQL(string.Format("update  SKRecs set IsToRemote=true where ID in ({0})", UpdateRecIDs));

                StateBar.Visible = false;
                StateBar.Value = 0;
                ShowActionInfo("");

                return RecsNum;
            }
            else return 0;
        }

	}
}
