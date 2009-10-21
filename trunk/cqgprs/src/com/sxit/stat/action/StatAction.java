/**
 * 
 */
package com.sxit.stat.action;

import java.util.Calendar;
import java.util.Date;

import jofc2.model.Chart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.common.action.AbstractListAction;

/**
 * @author 华锋 Oct 20, 2009-10:05:21 PM
 * 
 */
public abstract class StatAction extends AbstractListAction {
	private static Log _LOG = LogFactory.getLog(StatAction.class);

	protected com.sxit.stat.service.StatService statservice;

	protected static int PREV_DAYS=7;
	
	public StatAction() {
		this.statservice = (com.sxit.stat.service.StatService) getBean("statService");
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	protected abstract String go() throws Exception;

	protected String start;
	protected String end;

	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(String start) {
		if (start != null && start.equals("")) {
			try {
				this.startDate = df.parse(start);
			} catch (Exception e) {
				_LOG.error("起始时间解析错误:", e);
			}
		}
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public String getEnd() {
		return end;
	}

	/**
	 * @param end
	 *            the end to set
	 */
	public void setEnd(String end) {
		if (end != null && end.equals("")) {
			try {
				this.endDate = df.parse(end);
			} catch (Exception e) {
				_LOG.error("结束时间解析错误:", e);
			}
		}
		this.end = end;
	}

	/**
	 * 显示图形用的
	 */
	protected Chart flashChart;

	public Chart getFlashChart() {
		return this.flashChart;
	}

	/**
	 * 查询的起始日期
	 */
	protected Date startDate;
	/**
	 * 查询的终止日期
	 */
	protected Date endDate;

	protected static java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	protected static java.text.DateFormat dftime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 显示什么类型的结果,excel的形式?flash图表?还是普通的数据
	 */
	protected String resultType = "list";
	/**
	 * 显示饼图?柱状图还是线图还是其他的?
	 */
	protected String flashType = "line";
	/**
	 * 根据什么维度来产生图形
	 */
	protected String flashby = "total";

	/**
	 * @return the resultType
	 */
	public String getResultType() {
		return resultType;
	}

	/**
	 * @param resultType
	 *            the resultType to set
	 */
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	/**
	 * @return the flashType
	 */
	public String getFlashType() {
		return flashType;
	}

	/**
	 * @param flashType
	 *            the flashType to set
	 */
	public void setFlashType(String flashType) {
		this.flashType = flashType;
	}

	/**
	 * @return the flashby
	 */
	public String getFlashby() {
		return flashby;
	}

	/**
	 * @param flashby
	 *            the flashby to set
	 */
	public void setFlashby(String flashby) {
		this.flashby = flashby;
	}

	/*
	 * 得到当前日期的前一天
	 */
	protected Date getPrevDate() {
		return getPrevCountDate(1);
	}
	/*
	 * 得到当前日期的前一天
	 */
	protected Date getPrevCountDate(int days) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 0-days);
		Date d = new Date();
		d.setTime(c.getTimeInMillis());
		return d;
	}
}
