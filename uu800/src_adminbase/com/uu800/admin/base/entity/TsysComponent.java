package com.uu800.admin.base.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TsysComponent generated by MyEclipse Persistence Tools
 */

public class TsysComponent implements java.io.Serializable
{

	// Fields

	private long componentid;
	private String areacode;
	private String componentname;
	private long componenttype;
	private String comments;
	private long createuser;
	private Date createtime;
	private long modifyuser;
	private Date modifytime;
	private long status;
	private long objtype;
	private long usetype;
	private Date timelimit;
	private Date startusedate;
	private Date finishusedate;	
	private boolean checked;

	// Constructors

	/** default constructor */
	public TsysComponent()
	{
	}

	/** minimal constructor */
	public TsysComponent(String componentname, long componenttype,
			long createuser, Date createtime)
	{
		this.componentname = componentname;
		this.componenttype = componenttype;
		this.createuser = createuser;
		this.createtime = createtime;
	}

	
	// Property accessors

	public long getComponentid()
	{
		return this.componentid;
	}

	public void setComponentid(long componentid)
	{
		this.componentid = componentid;
	}

	public String getComponentname()
	{
		return this.componentname;
	}

	public void setComponentname(String componentname)
	{
		this.componentname = componentname;
	}

	public long getComponenttype()
	{
		return this.componenttype;
	}

	public void setComponenttype(long componenttype)
	{
		this.componenttype = componenttype;
	}

	public String getComments()
	{
		return this.comments;
	}

	public void setComments(String comments)
	{
		this.comments = comments;
	}

	public long getCreateuser()
	{
		return this.createuser;
	}

	public void setCreateuser(long createuser)
	{
		this.createuser = createuser;
	}

	public Date getCreatetime()
	{
		return this.createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public long getModifyuser()
	{
		return this.modifyuser;
	}

	public void setModifyuser(long modifyuser)
	{
		this.modifyuser = modifyuser;
	}

	public Date getModifytime()
	{
		return this.modifytime;
	}

	public void setModifytime(Date modifytime)
	{
		this.modifytime = modifytime;
	}
	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public long getObjtype() {
		return objtype;
	}

	public void setObjtype(long objtype) {
		this.objtype = objtype;
	}

	public long getUsetype() {
		return usetype;
	}

	public void setUsetype(long usetype) {
		this.usetype = usetype;
	}

	public Date getTimelimit() {
		return timelimit;
	}

	public void setTimelimit(Date timelimit) {
		this.timelimit = timelimit;
	}

	public Date getStartusedate() {
		return startusedate;
	}

	public void setStartusedate(Date startusedate) {
		this.startusedate = startusedate;
	}

	public Date getFinishusedate() {
		return finishusedate;
	}

	public void setFinishusedate(Date finishusedate) {
		this.finishusedate = finishusedate;
	}



	public String getAreacode() {
		return areacode;
	}
	public String[] getAreacodeSet(){
		return this.areacode.split(", ");
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}


}