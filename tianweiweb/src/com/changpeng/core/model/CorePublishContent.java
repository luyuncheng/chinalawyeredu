package com.changpeng.core.model;

import java.sql.Timestamp;

/**
 * CorePublishContent entity. @author MyEclipse Persistence Tools
 */

public class CorePublishContent implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer publishid;
	private Integer serviceid;
	private Integer contentid;
	private String remark="";
	private int statusid;
	private Timestamp approvetime;
	private Timestamp createtime;
	// Constructors

	public Timestamp getApprovetime() {
		return approvetime;
	}

	public void setApprovetime(Timestamp approvetime) {
		this.approvetime = approvetime;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	/** default constructor */
	public CorePublishContent() {
	}

	/** full constructor */
	public CorePublishContent(Integer publishid, Integer serviceid,
			Integer contentid) {
		this.publishid = publishid;
		this.serviceid = serviceid;
		this.contentid = contentid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPublishid() {
		return this.publishid;
	}

	public void setPublishid(Integer publishid) {
		this.publishid = publishid;
	}

	public Integer getServiceid() {
		return this.serviceid;
	}

	public void setServiceid(Integer serviceid) {
		this.serviceid = serviceid;
	}

	public Integer getContentid() {
		return this.contentid;
	}

	public void setContentid(Integer contentid) {
		this.contentid = contentid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStatusid() {
		return statusid;
	}

	public void setStatusid(int statusid) {
		this.statusid = statusid;
	}

}