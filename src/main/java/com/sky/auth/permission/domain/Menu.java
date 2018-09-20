package com.sky.auth.permission.domain;

import com.sky.auth.domain.BaseEntity;

/**
 * 资源
 * 
 * @author yangfan
 *
 */
public class Menu extends BaseEntity {

	private static final long serialVersionUID = -8699337175529772379L;
	
	/** 菜单名称 **/
	private String name;

	/** 菜单地址 **/
	private String url = "#";

	/** 菜单编号（用于显示时排序） **/
	private int seq = 0;

	/** 是否为叶子节点 **/
	private String isLeaf;

	/** 菜单层级 **/
	private int level = 1;

	/** 父节点:一级菜单为0 **/
	private String parentId="0";
	
	private String icon;
	
	/** 激活生效状态 **/
	private int active = 1;	
	

	/** 菜单名称 **/
	public String getName() {
		return name;
	}

	/** 菜单名称 **/
	public void setName(String name) {
		this.name = name;
	}

	/** 菜单地址 **/
	public String getUrl() {
		return url;
	}

	/** 菜单地址 **/
	public void setUrl(String url) {
		this.url = url;
	}

	/** 菜单编号（用于显示时排序） **/
	public int getSeq() {
		return seq;
	}

	/** 菜单编号（用于显示时排序） **/
	public void setSeq(int seq) {
		this.seq = seq;
	}

	/** 是否为叶子节点 **/
	public String getIsLeaf() {
		return isLeaf;
	}

	/** 是否为叶子节点 **/
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	/** 菜单层级 **/
	public int getLevel() {
		return level;
	}

	/** 菜单层级 **/
	public void setLevel(int level) {
		this.level = level;
	}

	/** 父级菜单 **/
	public String getParentId() {
		return parentId;
	}

	/** 父级菜单 **/
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/** 菜单图标   **/
	public String getIcon() {
		return icon;
	}

	/** 菜单图标   **/
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	/** 激活 **/
	public int getActive(){
		return active;
	}
	
	/** 激活 **/
	public void setActive(int active){
		this.active = active;
	}
	
	/** 是否是根菜单 **/
	public boolean isRoot(){
		if("0".equals(parentId))return true;
		return false;
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
		if(name!=null){
			if(!empty){
				res += ",";
			}
			empty = false;
			res+="name:" + name;
		}
		if(url!=null){
			if(!empty){
				res += ",";
			}
			empty = false;
			res+="url:" + url;
		}
//		if(seq!=null){
//			if(!empty){
//				res += ",";
//			}
//			empty = false;
//			res+="seq:" + seq;
//		}
		if(isLeaf!=null){
			if(!empty){
				res += ",";
			}
			empty = false;
			res+="isLeaf:" + isLeaf;
		}
//		if(level!=null){
//			if(!empty){
//				res += ",";
//			}
//			empty = false;
//			res+="level:" + level;
//		}
		if(parentId!=null){
			if(!empty){
				res += ",";
			}
			empty = false;
			res+="parentId:" + parentId;
		}
		res+="}";
		return res;
	}
}