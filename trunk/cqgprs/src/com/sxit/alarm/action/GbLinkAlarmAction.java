/**
 * 
 */
package com.sxit.alarm.action;

import java.util.List;

import com.sxit.alarm.service.AlarmService;
import com.sxit.common.action.AbstractAction;

/**
 * 
 * gb流量告警
 * 从alarm_gb表中拿数据
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class GbLinkAlarmAction extends AbstractAction {

	private List alarmlist;
	
	public List getAlarmlist(){
		return this.alarmlist;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		System.out.println("====================================");
		
		AlarmService service=(AlarmService)this.getBean("alarmService");
		alarmlist=service.getGbAlarms();
		
		service.updateAlarmHasRead();
		
		return SUCCESS;
	}

}
