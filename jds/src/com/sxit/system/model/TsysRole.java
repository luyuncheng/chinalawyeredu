package com.sxit.system.model;
// Generated 2007-9-26 16:57:08 by Hibernate Tools 3.2.0.b9


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * TsysRole generated by hbm2java
 */
public class TsysRole  implements java.io.Serializable {


     private int roleid;
     private String rolename;
     private String description;
     private Timestamp createtime;
     private int statusid;
     private Set<TsysFunctionRole> tsysFunctionRoles = new HashSet<TsysFunctionRole>(0);
     private Set<TsysUserRole> tsysUserRoles = new HashSet<TsysUserRole>(0);

    public TsysRole() {
    }

    public TsysRole(String rolename, String description, Timestamp createtime, int statusid, Set<TsysFunctionRole> tsysFunctionRoles, Set<TsysUserRole> tsysUserRoles) {
       this.rolename = rolename;
       this.description = description;
       this.createtime = createtime;
       this.statusid = statusid;
       this.tsysFunctionRoles = tsysFunctionRoles;
       this.tsysUserRoles = tsysUserRoles;
    }
   
    public int getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }
    public String getRolename() {
        return this.rolename;
    }
    
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Timestamp getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }
    public int getStatusid() {
        return this.statusid;
    }
    
    public void setStatusid(int statusid) {
        this.statusid = statusid;
    }
    public Set<TsysFunctionRole> getTsysFunctionRoles() {
        return this.tsysFunctionRoles;
    }
    
    public void setTsysFunctionRoles(Set<TsysFunctionRole> tsysFunctionRoles) {
        this.tsysFunctionRoles = tsysFunctionRoles;
    }
    public Set<TsysUserRole> getTsysUserRoles() {
        return this.tsysUserRoles;
    }
    
    public void setTsysUserRoles(Set<TsysUserRole> tsysUserRoles) {
        this.tsysUserRoles = tsysUserRoles;
    }




}

