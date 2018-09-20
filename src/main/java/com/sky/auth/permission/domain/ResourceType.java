package com.sky.auth.permission.domain;

import com.sky.auth.domain.BaseEntity;

/**
 * 资源类别
 * 
 * @author：yangfan
 */
public class ResourceType extends BaseEntity {

	private static final long serialVersionUID = -1850274607153125161L;
	private String code; // 资源编码：eg:：menu, operation, button
	private String name; // 资源名称     eg: 菜单,	操作,  按钮
	private String description; // 资源描述
	/** 菜单编号（用于显示时排序） **/
	private int seq = 0;

	/** 是否为叶子节点 **/
	private String isLeaf;

	/** 菜单层级 **/
	private int level = 1;

	/** 父节点:一级资源为0 **/
	private String parentId="0";
	
	private String icon;
	
	/** 激活生效状态 **/
	private int active = 1;	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 资源名称
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 资源名称
	 * 
	 * @return
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String toString(){
		boolean empty = true;
		String res = "{";
		if(id!=null){
			if(!empty){
				res += ",";
			}
			empty = false;
			res+="id:" + id;
		}
		if(code!=null){
			if(!empty){
				res += ",";
			}
			empty = false;
			res+="code:" + code;
		}
		if(name!=null){
			if(!empty){
				res += ",";
			}
			empty = false;
			res+="name:" + name;
		}
		if(description!=null){
			if(!empty){
				res += ",";
			}
			empty = false;
			res+="description:" + description;
		}
		res+="}";
		return res;
	}
}
