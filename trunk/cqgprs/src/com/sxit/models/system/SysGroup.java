
package com.sxit.models.system;

// Generated 2008-2-21 9:22:49 by Hibernate Tools 3.2.0.CR1

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * TSysGroup generated by hbm2java
 */
public class SysGroup implements java.io.Serializable {
	
	private int groupid;
	private int parentid;
	private short grouplevel;
	private String groupenname;
	private String groupname;
	private String contacter;
	private String phone;
	private String fax;
	private String comments;
	private boolean delflag;
	private String createuser;
	private Timestamp createtime;
	private Set<SysUser> sysUsers = new HashSet<SysUser>(0);



	public SysGroup() {
		
	}

	public SysGroup(int groupid, int parentid, short grouplevel, String groupname) {
		this.groupid = groupid;
		this.parentid = parentid;
		this.grouplevel = grouplevel;
		this.groupname = groupname;
	}

	public SysGroup(int groupid, int parentid, short grouplevel, String groupenname, String groupname, String contacter, String phone, String fax,
			String comments, boolean delflag, String createuser, Timestamp createtime, Set<SysUser> SysUsers) {
		this.groupid = groupid;
		this.parentid = parentid;
		this.grouplevel = grouplevel;
		this.groupenname = groupenname;
		this.groupname = groupname;
		this.contacter = contacter;
		this.phone = phone;
		this.fax = fax;
		this.comments = comments;
		this.delflag = delflag;
		this.createuser = createuser;
		this.createtime = createtime;
		this.sysUsers = SysUsers;
	}

	public int getGroupid() {
		return this.groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getParentid() {
		return this.parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public short getGrouplevel() {
		return this.grouplevel;
	}

	public void setGrouplevel(short grouplevel) {
		this.grouplevel = grouplevel;
	}

	public String getGroupenname() {
		return this.groupenname;
	}

	public void setGroupenname(String groupenname) {
		this.groupenname = groupenname;
	}

	public String getGroupname() {
		return this.groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getContacter() {
		return this.contacter;
	}

	public void setContacter(String contacter) {
		this.contacter = contacter;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public boolean getDelflag() {
		return this.delflag;
	}

	public void setDelflag(boolean delflag) {
		this.delflag = delflag;
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

	public Set<SysUser> getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(Set<SysUser> SysUsers) {
		this.sysUsers = SysUsers;
	}



}