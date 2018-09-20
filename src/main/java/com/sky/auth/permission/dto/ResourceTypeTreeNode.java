package com.sky.auth.permission.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源类型树节点
 * @author 杨帆
 *
 */
public class ResourceTypeTreeNode {
	/** 资源名称 **/
	private String name;

	/** 资源地址 **/
	private String code;

	/** 资源编号（用于显示时排序） **/
	private int seq;

	/** 是否为叶子节点 **/
	private String isLeaf;

	/** 资源层级 **/
	private int level;

	/** 父节点:一级资源为0 **/
	private String parentId="0";

	private String icon; 
	
	private String text;
	
	private String id;
	
	private int active;
	
	private List<ResourceTypeTreeNode> nodes = new ArrayList<ResourceTypeTreeNode>(); //子节点
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
		
	public void setActive(int active){
		this.active = active;
	}
	
	public int getActive(){
		return active;
	}
	
	public List<ResourceTypeTreeNode> getNodes() {
		return nodes;
	}

	public void setSubMenuTrees(List<ResourceTypeTreeNode> nodes) {
		this.nodes = nodes;
	}

	public void addChildNode(ResourceTypeTreeNode node){
		if(nodes==null){
			nodes=new ArrayList<ResourceTypeTreeNode>();
		}
		nodes.add(node);
	}	
}
