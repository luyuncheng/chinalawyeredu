package com.sxit.system.model;

import java.util.Date;

/**
 * SysLoginlog generated by MyEclipse Persistence Tools
 */

public class SysLoginlog implements java.io.Serializable {

	// Fields

	private Long loginid;

	private String loginname;

	private Date logintime;

	private Date logouttime;

	private String loginip;

	private Long insystime;

	private String contextid;
	
	
	private String logoutremarks; //退出信息
	private String loginremarks; //登录信息

	// Constructors

	/** default constructor */
	public SysLoginlog() {
	}



	// Property accessors

	public Long getLoginid() {
		return this.loginid;
	}

	public void setLoginid(Long loginid) {
		this.loginid = loginid;
	}

	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public Date getLogintime() {
		return this.logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public Date getLogouttime() {
		return this.logouttime;
	}

	public void setLogouttime(Date logouttime) {
		this.logouttime = logouttime;
	}

	public String getLoginip() {
		return this.loginip;
	}

	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	public Long getInsystime() {
		return this.insystime;
	}

	public void setInsystime(Long insystime) {
		this.insystime = insystime;
	}

	public String getContextid() {
		return this.contextid;
	}

	public void setContextid(String contextid) {
		this.contextid = contextid;
	}

	public String getLogoutremarks() {
		return logoutremarks;
	}

	public void setLogoutremarks(String logoutremarks) {
		this.logoutremarks = logoutremarks;
	}

	public String getLoginremarks() {
		return loginremarks;
	}

	public void setLoginremarks(String loginremarks) {
		this.loginremarks = loginremarks;
	}



}