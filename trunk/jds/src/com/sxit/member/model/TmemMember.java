package com.sxit.member.model;
// Generated 2008-6-11 15:32:49 by Hibernate Tools 3.2.0.b9


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * TmemMember generated by hbm2java
 */
public class TmemMember  implements java.io.Serializable {


     private int memberid;
     private String loginname;
     private String password;
     private int statusid;
     private Timestamp createdate;
     private String username;
     private String phone;
     private String mobile;
     private String email;
     private TmemResume tmemResume;
    
    public TmemMember() {
    }

    public TmemMember(String loginname, String password, int statusid, Timestamp createdate, String username, String phone, String mobile, String email, TmemResume tmemResume) {
       this.loginname = loginname;
       this.password = password;
       this.statusid = statusid;
       this.createdate = createdate;
       this.username = username;
       this.phone = phone;
       this.mobile = mobile;
       this.email = email;
       this.tmemResume = tmemResume;
       
    }
   
    public int getMemberid() {
        return this.memberid;
    }
    
    public void setMemberid(int memberid) {
        this.memberid = memberid;
    }
    public String getLoginname() {
        return this.loginname;
    }
    
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public int getStatusid() {
        return this.statusid;
    }
    
    public void setStatusid(int statusid) {
        this.statusid = statusid;
    }
    public Timestamp getCreatedate() {
        return this.createdate;
    }
    
    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
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
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public TmemResume getTmemResume() {
        return this.tmemResume;
    }
    
    public void setTmemResume(TmemResume tmemResume) {
        this.tmemResume = tmemResume;
    }

}


