package com.sky.auth.permission.dto;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	/** 菜单名称 **/
	private String name;

	/** 菜单编号（用于显示时排序） **/
	private int seq;

	/** 是否为叶子节点 **/
	private String isLeaf;

	/** 菜单层级 **/
	private int level;

	/** 父节点:一级菜单为0 **/
	private String parentId="0";

	private String icon; 
	
	private String text;
	
	private String id;
	
	private int active;
	
	private List<TreeNode> nodes = new ArrayList<TreeNode>(); //子节点
	
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
	
	public List<TreeNode> getNodes() {
		return nodes;
	}

	public void setSubMenuTrees(List<TreeNode> nodes) {
		this.nodes = nodes;
	}

	public void addChildNode(TreeNode node){
		if(nodes==null){
			nodes=new ArrayList<TreeNode>();
		}
		nodes.add(node);
	}	
}
