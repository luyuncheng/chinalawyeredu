package com.sxit.system.model;
// Generated 2007-9-26 16:57:08 by Hibernate Tools 3.2.0.b9



/**
 * TsysFunctionUser generated by hbm2java
 */
public class TsysFunctionUser  implements java.io.Serializable {


     private TsysFunctionUserPK comp_id;
     private int power;

    public TsysFunctionUser() {
    }

    public TsysFunctionUser(TsysFunctionUserPK comp_id, int power) {
       this.comp_id = comp_id;
       this.power = power;
    }
   
    public TsysFunctionUserPK getComp_id() {
        return this.comp_id;
    }
    
    public void setComp_id(TsysFunctionUserPK comp_id) {
        this.comp_id = comp_id;
    }
    public int getPower() {
        return this.power;
    }
    
    public void setPower(int power) {
        this.power = power;
    }




}


