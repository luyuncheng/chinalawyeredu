using System;
using System.Data;
using System.Data.OleDb;
using System.Threading;


namespace ��ѵˢ������ϵͳ
{
	/// <summary>
	/// SKProcess ��ժҪ˵����
	/// </summary>
	public class SKProcess
	{
		public SKProcess()
		{
			//
			// TODO: �ڴ˴���ӹ��캯���߼�
			//
		}

		public void TestModeSK()
		{
			Access SKDB=new Access();

			OleDbDataReader TmpReader=SKDB.ExecuteQuery("select count(*) from lawyers");
			int LawyerCnt;

			while(true)
			{
				TmpReader.Read();
				LawyerCnt= Convert.ToInt32(TmpReader.GetValue(0).ToString());
				TmpReader.Close();
                Logger.LogInfo("SKProcess��ϵͳ������" + LawyerCnt + "����ʦ");
                if (LawyerCnt > 0)
                {
                 
                    break;
                }

				Thread.Sleep(10000);
			}

			Random rm=new Random();

			while(true)
			{
				int TMBH=rm.Next(1,LawyerCnt);
				DateTime DT=DateTime.Now;

				DataSet RMRecs=new DataSet();
				Access AccessDB=new Access();
				AccessDB.AddTableInDataSet(RMRecs,"Recs",string.Format("select top {0} bh from lawyers order by bh",TMBH));
                string sql = string.Format("insert into yskq(bh,rq,sj,SKMode) values({0},'{1}','{2}','ģ��')",
                                RMRecs.Tables["Recs"].Rows[TMBH - 1][0].ToString(),
                                DT.ToString("yyyy-MM-dd"),
                                DT.ToString("HH-mm-ss"));
            //    System.Windows.Forms.MessageBox.Show(sql);
				SKDB.ExecuteSQL(sql);

				Thread.Sleep(2000);
			}

/*			public void TestModeSK()
			{
				Access SKDB=new Access();

				OleDbDataReader TmpReader=SKDB.ExecuteQuery("select min(bh) ,max(bh) from lawyers"); 

				while (!TmpReader.HasRows)
				{
					TmpReader=SKDB.ExecuteQuery("select min(bh) ,max(bh) from lawyers"); 

				}

				TmpReader.Read();

				int MinBH= Convert.ToInt32(TmpReader.GetValue(0).ToString()),MaxBH= Convert.ToInt32(TmpReader.GetValue(1).ToString());

				TmpReader.Close();

				Random rm=new Random();
				while(true)
				{
				
					int TMBH=rm.Next(MinBH,MaxBH);
					DateTime DT=DateTime.Now;

					SKDB.ExecuteSQL(string.Format("insert into yskq(bh,rq,sj) values({0},'{1}','{2}')",TMBH,DT.ToString("yyyy-MM-dd"),DT.ToString("HH-mm-ss")));

					Thread.Sleep(6000);
				}
*/
		}

	}
}
