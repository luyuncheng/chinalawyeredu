/**
 * VideoLookAction.java
 */

package com.changpeng.jifen.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.jifen.service.LawyerlessonxfService;
import com.changpeng.jifen.service.LxnetrecsService;
import com.changpeng.models.Lawyerlessonxf;
import com.changpeng.models.Lessons;
import com.changpeng.models.Lxnetrecs;

/**
 * <pre>
 *  
 * 看视频,获取课程名称并获取这个人现在看的分数
 * 如果这个人对这个课程已经获得了满分了，则能看，但是不计入分数
 * </pre>
 * 
 * @author 华锋 2008-5-5 下午12:46:19
 * 
 */
public class VideoLookPreAction extends AbstractAction {

	private int lessonid;
	private float videotimeout = 0;
	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	public int getLessonid() {
		return this.lessonid;
	}

	private int userid;

	public int getUserid() {
		return this.userid;
	}

	@Override
	public String go() throws Exception {
		this.videotimeout = Float.parseFloat(com.changpeng.common.CommonDatas.SysParameter.get("videotimeout").toString());
		this.userid = this.getLoginUser().getLawyerid();

		LxnetrecsService lxnetrecsService = (LxnetrecsService) getBean("lxnetrecsService");
		BasicService basicService = (BasicService) getBean("basicService");
		this.lessons = (Lessons) basicService.get(Lessons.class, lessonid);
		if(this.lessons==null){
			this.message="系统有误,请在在线课程里,选择课程点击观看";
			this.nextPage="javascript:window.close()";
			return "message";
		}else if(this.lessons.getOnlinefile()==null||this.lessons.getOnlinefile().equals("")){
			this.message="该课程视频文件为空,请返回";
			this.nextPage="javascript:window.close()";
			return "message";
		}
			
		this.lxnetrecs = (Lxnetrecs) lxnetrecsService.getLxnetrecs(lessonid, getLoginUser().getLawyerid());

		debug("this.lxnetrecs==" + this.lxnetrecs);

		LawyerlessonxfService xfservice = (LawyerlessonxfService) this.getBean("lawyerlessonxfService");
		Lawyerlessonxf xuefen = xfservice.getXuefen(lessonid, this.userid, 0);
		// 满分了，不管是通过什么方式的
		if (xuefen != null) {
			debug("xuefen.getPxxf().floatValue() == lessons.getXuefen().floatValue()"
					+ (xuefen.getPxxf().floatValue() == lessons.getXuefen().floatValue()));
			debug(xuefen.getPxxf() + "===" + lessons.getXuefen());
		}
		if(xuefen!=null&&xuefen.getIsfull())
		{
			settime = false;
		}

		//如果参加了现场培训，就不
		if(xuefen!=null&&xuefen.getLearnmode()==1){
	        settime=false;
			localelesson=true;
		}
		
		if (lxnetrecs == null)
			lxnetrecs = new Lxnetrecs();

		return SUCCESS;

	}
	
	private boolean localelesson;
	public boolean getLocalelesson(){
		return this.localelesson;
	}

	// 是否还需要计时
	private boolean settime = true;

	public boolean getSettime() {
		return this.settime;
	}



	private Lxnetrecs lxnetrecs;

	public Lxnetrecs getLxnetrecs() {
		return this.lxnetrecs;
	}

//	private int netrecsid;
//
//	public int getNetrecsid() {
//		return this.netrecsid;
//	}

	private Lessons lessons;

	public Lessons getLessons() {
		return this.lessons;
	}

	public float getVideotimeout() {
		return videotimeout;
	}
}