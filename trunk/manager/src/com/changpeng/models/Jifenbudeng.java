package com.changpeng.models;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Jifenbudeng entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Jifenbudeng implements java.io.Serializable {

	// Fields

	private int budengid;
	private String title;
	private Date budengdate;
	private String lawyerno;
	private int lawyerid;
	private String lawyername;

	private Float xuefen;
	private int createuserid;
	private String createuser;
	private Timestamp createtime;
	
	private int provinceid;
	private int cityid;
	private int officeid;

	// Constructors

	/**
	 * @return the provinceid
	 */
	public int getProvinceid() {
		return provinceid;
	}

	/**
	 * @param provinceid the provinceid to set
	 */
	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}

	/**
	 * @return the cityid
	 */
	public int getCityid() {
		return cityid;
	}

	/**
	 * @param cityid the cityid to set
	 */
	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	/**
	 * @return the officeid
	 */
	public int getOfficeid() {
		return officeid;
	}

	/**
	 * @param officeid the officeid to set
	 */
	public void setOfficeid(int officeid) {
		this.officeid = officeid;
	}

	/** default constructor */
	public Jifenbudeng() {
	}

	

	

	// Property accessors

	public int getBudengid() {
		return this.budengid;
	}

	public void setBudengid(int budengid) {
		this.budengid = budengid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getBudengdate() {
		return this.budengdate;
	}

	public void setBudengdate(Date budengdate) {
		this.budengdate = budengdate;
	}

	public String getLawyerno() {
		return this.lawyerno;
	}

	public void setLawyerno(String lawyerno) {
		this.lawyerno = lawyerno;
	}

	public int getLawyerid() {
		return this.lawyerid;
	}

	public void setLawyerid(int lawyerid) {
		this.lawyerid = lawyerid;
	}

	public String getLawyername() {
		return this.lawyername;
	}

	public void setLawyername(String lawyername) {
		this.lawyername = lawyername;
	}

	
	public Float getXuefen() {
		return this.xuefen;
	}

	public void setXuefen(Float xuefen) {
		this.xuefen = xuefen;
	}

	public int getCreateuserid() {
		return this.createuserid;
	}

	public void setCreateuserid(int createuserid) {
		this.createuserid = createuserid;
	}

	public String getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	

}