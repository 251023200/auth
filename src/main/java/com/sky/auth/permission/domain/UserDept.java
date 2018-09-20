package com.sky.auth.permission.domain;

import com.sky.auth.domain.BaseEntity;

/**
 * 用户-部门
 * @author yangfan
 *
 */
public class UserDept extends BaseEntity{

	private static final long serialVersionUID = -7304009660984441744L;

	private String userId;	//用户ID
	
	private String deptId;	//部门ID

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	
}
