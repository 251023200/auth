package com.sky.auth.permission.domain;

import com.sky.auth.domain.BaseEntity;

/**
 * 用户-组
 * @author yangfan
 *
 */
public class UserGroup extends BaseEntity{

	private static final long serialVersionUID = 2933271759425578218L;

	private String userId;
	
	private String groupId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
}
