package com.sky.auth.domain;

import java.io.Serializable;
import java.util.Date;

import com.sky.auth.enums.Status;


/**
 * 基类.
 * @author yangfan
 */
public abstract class BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4069607203419981121L;
	
	//protected String id = StringUtil.get32UUID();// 主键ID.
	protected String id;
	protected Integer version = 0;// 版本号默认为0
	protected int status = Status.ACTIVE.getCode();// 状态 Status
	protected Date createTime = new Date();// 创建时间.
	protected String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
