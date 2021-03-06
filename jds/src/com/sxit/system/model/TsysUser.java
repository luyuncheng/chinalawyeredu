package com.sxit.system.model;
// Generated 2008-1-2 14:13:50 by Hibernate Tools 3.2.0.b9


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * TsysUser generated by hbm2java
 */
public class TsysUser  implements java.io.Serializable,java.lang.Comparable<TsysUser>  {


     private long userid;
     private String username;
     private String loginname;
     private Timestamp createtime;
     private String password;
     private String email;
     private String phone;
     private String mobile;
     private int statusid;
     private String description;
     private boolean isadmin;
     private boolean isleader;
     private String userno;
     private String bqqno;
     private String msnmail;
     private String image;
     private int style;
     private Set<TsysFunctionUser> tsysFunctionUsers = new HashSet<TsysFunctionUser>(0);
     private Set<TsysUserRole> tsysUserRoles = new HashSet<TsysUserRole>(0);
     private Set<TsysGroupUser> tsysGroupUsers = new HashSet<TsysGroupUser>(0);
     private TsysDepartment tsysDepartment;
   
     private String positionname;
     
     private boolean isCallUser; //是否催收人员 如果是 则登录后页面头部需要显示应答按钮
     private String iscookie="cookie";
     /**
 	 * 绑定这个用户的此次登录的id
 	 */
 	private Long loginId;
     public void setLoginId(Long loginId){
     	this.loginId=loginId;
     }
     public Long getLoginId(){
     	return this.loginId;
     }
     
    public String getPositionname() {
		return positionname;
	}

	public void setPositionname(String positionname) {
		this.positionname = positionname;
	}

	public TsysUser() {
    }

    public long getUserid() {
        return this.userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getLoginname() {
        return this.loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }
    public Timestamp getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public int getStatusid() {
        return this.statusid;
    }

    public void setStatusid(int statusid) {
        this.statusid = statusid;
    }
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isIsadmin() {
        return this.isadmin;
    }

    public void setIsadmin(boolean isadmin) {
        this.isadmin = isadmin;
    }
    public boolean isIsleader() {
        return this.isleader;
    }

    public void setIsleader(boolean isleader) {
        this.isleader = isleader;
    }
    public String getUserno() {
        return this.userno;
    }

    public void setUserno(String userno) {
        this.userno = userno;
    }
    public String getBqqno() {
        return this.bqqno;
    }

    public void setBqqno(String bqqno) {
        this.bqqno = bqqno;
    }
    public String getMsnmail() {
        return this.msnmail;
    }

    public void setMsnmail(String msnmail) {
        this.msnmail = msnmail;
    }
    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public int getStyle() {
        return this.style;
    }

    public void setStyle(int style) {
        this.style = style;
    }
    public Set<TsysFunctionUser> getTsysFunctionUsers() {
        return this.tsysFunctionUsers;
    }

    public void setTsysFunctionUsers(Set<TsysFunctionUser> tsysFunctionUsers) {
        this.tsysFunctionUsers = tsysFunctionUsers;
    }
    public Set<TsysUserRole> getTsysUserRoles() {
        return this.tsysUserRoles;
    }

    public void setTsysUserRoles(Set<TsysUserRole> tsysUserRoles) {
        this.tsysUserRoles = tsysUserRoles;
    }
    public Set<TsysGroupUser> getTsysGroupUsers() {
        return this.tsysGroupUsers;
    }

    public void setTsysGroupUsers(Set<TsysGroupUser> tsysGroupUsers) {
        this.tsysGroupUsers = tsysGroupUsers;
    }
    public TsysDepartment getTsysDepartment() {
        return this.tsysDepartment;
    }

    public void setTsysDepartment(TsysDepartment tsysDepartment) {
        this.tsysDepartment = tsysDepartment;
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TsysUser) ) return false;
        TsysUser castOther = (TsysUser) other;
        return new EqualsBuilder()
            .append(this.getUserid(), castOther.getUserid())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getUserid())
            .toHashCode();
    }

	//用于排序	
    public int compareTo(TsysUser user){
    	 System.out.println("排序：-------");
    	java.text.RuleBasedCollator rc=
    		  (java.text.RuleBasedCollator)java.text.Collator.getInstance(java.util.Locale.CHINA); 
    	return rc.compare(this.username,user.getUsername());
    }

	public boolean isCallUser() {
		return isCallUser;
	}

	public void setCallUser(boolean isCallUser) {
		this.isCallUser = isCallUser;
	}
	/**
	 * @return the iscookie
	 */
	public String getIscookie() {
		return iscookie;
	}
	/**
	 * @param iscookie the iscookie to set
	 */
	public void setIscookie(String iscookie) {
		this.iscookie = iscookie;
	}
}


