package com.sky.auth.permission.dto;

/**
 * 带状态的菜单树
 * @author 杨帆
 *
 */
public class StatedMenuTreeNode extends MenuTreeNode{

	//private String roleId;	//对应的角色
	private int assigned;	//是否分配给角色了(对某个角色而言)
	
	public int getAssigned(){
		return assigned;
	}
	
	public void setAssigned(int assigned){
		this.assigned  = assigned;
	}
	
}
