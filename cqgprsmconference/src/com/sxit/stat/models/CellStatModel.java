/**
 * 
 */
package com.sxit.stat.models;

import java.text.DateFormat;

import com.sxit.netquality.models.Cell;

/**
 * 
 * sgsn的流量分析 以及sgsn按23g的流量分析
 * 
 * @author 华锋 Oct 16, 2009-5:03:50 PM
 * 
 */
public class CellStatModel extends StatModel {

	private static final DateFormat dfhh = new java.text.SimpleDateFormat("yyyy-MM-dd HH:00:00");

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	private String bscrncid;
	private String sgsnid;
	private String cellid;
	private String lac;
	private String cellname;

	/**
	 * 网络类型,td,gsm等
	 */
	private String nettype;
	/**
	 * 某天的数据
	 */
	private String date;
	/**
	 * 某天-时的数据
	 */
	private String datetime;
	private boolean setdatetime;
	private boolean setdate;
	private long stattime;

	/**
	 * @return the stattime
	 */
	public long getStattime() {
		return stattime;
	}

	/**
	 * @param stattime
	 *            the stattime to set
	 */
	public void setStattime(long stattime) {
		this.stattime = stattime;
	}

	public String getCellkey() {
		if (lac == null || lac.equals("")) {
			System.out.println("lac==null||lac.equals(\"\")");
			return cellid;
		}
		return lac + "-" + cellid;
	}

	/**
	 * @return the bscrncid
	 */
	public String getBscrncid() {
		// return bscrncid;
		return com.sxit.netquality.service.BasicSetService.CELL_BSC.get(getCellkey());
	}

	/**
	 * @param bscrncid
	 *            the bscrncid to set
	 */
	public void setBscrncid(String bscrncid) {
		this.bscrncid = bscrncid;
	}

	/**
	 * @return the nettype
	 */
	public String getNettype() {
		if (nettype == null || nettype.equals(""))
			return "未知";
		if (nettype.equals("3")) {
			return "WLAN";
		} else if (nettype.equals("2")) {
			return "GSM";

		} else if (nettype.equals("1")) {
			return "TD";
		} else
			return nettype;
	}

	/**
	 * @param nettype
	 *            the nettype to set
	 */
	public void setNettype(String nettype) {
		this.nettype = nettype;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		if (setdate)
			return date;
		return df.format(new java.sql.Timestamp(stattime * 1000));
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		setdate = true;
		this.date = date;
	}

	/**
	 * @return the datetime
	 */
	public String getDatetime() {
		if (setdatetime)
			return datetime;
		return dfhh.format(new java.sql.Timestamp(stattime * 1000));
	}

	/**
	 * @param datetime
	 *            the datetime to set
	 */
	public void setDatetime(String datetime) {
		setdatetime = true;
		this.datetime = datetime;
	}

	/**
	 * @return the sgsnid
	 */
	public String getSgsnid() {
		return com.sxit.netquality.service.BasicSetService.BSC_SGSN.get(getBscrncid());
		// return sgsnid;
	}

	/**
	 * @param sgsnid
	 *            the sgsnid to set
	 */
	public void setSgsnid(String sgsnid) {
		this.sgsnid = sgsnid;
	}

	/**
	 * @return the cellid
	 */
	public String getCellid() {
		return cellid;
	}

	/**
	 * @param cellid
	 *            the cellid to set
	 */
	public void setCellid(String cellid) {
		this.cellid = cellid;
	}

	/**
	 * @return the cellname
	 */
	public String getCellname() {
		// return cellname;
		Cell cell = com.sxit.netquality.service.BasicSetService.ALL_CELLS.get(getCellkey());
		if (cell != null)
			return cell.getCellname();
		return getCellkey();
	}

	/**
	 * @param cellname
	 *            the cellname to set
	 */
	public void setCellname(String cellname) {
		this.cellname = cellname;
	}

	/**
	 * @return the lac
	 */
	public String getLac() {
		return lac;
	}

	/**
	 * @param lac
	 *            the lac to set
	 */
	public void setLac(String lac) {
		this.lac = lac;
	}

}