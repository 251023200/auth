package com.sky.auth.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 基类.
 * @author yangfan
 */
public abstract class BaseEntityV0 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4069607203419981121L;
	
	//protected String id = StringUtil.get32UUID();// 主键ID.
	protected String id;
	protected Integer version = 0;// 版本号默认为0
	protected String status;// 状态 Status
	protected String creater;// 创建人.
	protected Date createTime = new Date();// 创建时间.
	protected String editor;// 修改人.
	protected Date editTime;// 修改时间.
	protected String remark;// 描述

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
