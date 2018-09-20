package com.sky.auth.permission.domain;

import com.sky.auth.domain.BaseEntityV0;

public class OperatorUser extends BaseEntityV0{
   
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 2256073236486338742L;


	private String orgid;

    
    private String userid;

    private String type;

    
   
    public String getOrgid() {
        return orgid;
    }

    
    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getUserid() {
        return userid;
    }

    
    public void setUserid(String userid) {
        this.userid = userid;
    }

    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}