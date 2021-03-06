package com.changpeng.sns.filemanage.action;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractFileSwfUploadAction;
import com.changpeng.common.util.DesPlus;
import com.changpeng.sns.filemanage.model.SnsDir;
import com.changpeng.sns.filemanage.model.SnsFile;
import com.changpeng.sns.filemanage.service.FileManageService;

public class UploadFileAction extends AbstractFileSwfUploadAction {
	private static Logger LOG = Logger.getLogger(AbstractFileSwfUploadAction.class);
	private static final DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");

	private SnsDir dir;

	public UploadFileAction() {
		this.rightCode="PUBLIC";
		this.moduleid = 16;
		
	}

	@Override
	protected String go() throws Exception {
		try {
			FileManageService filemanageservice=(FileManageService)this.getBean("fileManageService");
			this.dir = (SnsDir)service.get(SnsDir.class,this.albumid);
			for (int i = 0; i < filenames.length; i++) {

				String photoName = FiledataFileName[i].substring(0, FiledataFileName[i].lastIndexOf("."));

				if(photoName.length()>20){
					photoName=photoName.substring(0, 20);
				}
				
				SnsFile snsfile=new SnsFile();
				snsfile.setCreateIp(this.userip);
				snsfile.setCreateTime(new Timestamp(System.currentTimeMillis()));
				snsfile.setDescription("");
				snsfile.setDirid(this.albumid);
				snsfile.setFileName(photoName);
				snsfile.setSmallUrl("");
				snsfile.setStatusid((short)1);
				snsfile.setTypeid(dir.getDirtype());
				snsfile.setUrl(saveurl[i]);
				snsfile.setUserid(userid);
				filemanageservice.save(snsfile);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		this.result="successed";
		return "result";
	}

	@Override
	public String getin() {

		FileManageService filemanageservice=(FileManageService)this.getBean("fileManageService");
		this.dir = (SnsDir)service.get(SnsDir.class, id);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		
		/*    加密前的字符串: "用户ID,相册ID,当前时间戳" 格式   */
		String beforeEncrypt = String.valueOf(this.currentUserid)+","+id+","+sdf.format(new Date());
		System.out.println("加密前的======"+beforeEncrypt);
		/*   加密的密钥，为当天的时间   */
		String passwd = df.format(new Date());
		
		try {
			DesPlus des = new DesPlus(passwd);
			jxq_ = des.encrypt(beforeEncrypt);
			System.out.println("加密后的======"+jxq_);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "input";
	}

	private int id;

	public void setId(int id) {
		this.id = id;
	}

	public SnsDir getDir() {
		return dir;
	}

	public void setDir(SnsDir dir) {
		this.dir = dir;
	}

	public int getId() {
		return id;
	}
	
}
