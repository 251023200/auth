package com.sky.auth.permission.dto;

public class MenuTreeNode extends TreeNode{
	
	/** 菜单地址 **/
	private String url="#";


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	
	/*
	public void setSubMenuTrees(List<MenuTreeNode> nodes) {
		this.nodes = nodes;
	}

	public void addChildNode(MenuTreeNode node){
		if(nodes==null){
			nodes=new ArrayList<MenuTreeNode>();
		}
		nodes.add(node);
	}	
	*/
}
