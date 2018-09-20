package com.sky.auth.permission.dao;

import java.util.List;
import java.util.Map;

import com.sky.auth.dao.BaseDao;
import com.sky.auth.permission.domain.ResourceType;


/**
 * 权限菜单
 * 
 * @author：yangfan
 */
public interface ResourceTypeDao extends BaseDao<ResourceType>{
	/**
	 * 通过资源名称获取资源
	 * @param name 角色名称
	 * @return List<Role> 角色列表
	 */
	public List<ResourceType> getByName(String name);			
	
	/**
	 * 通过资源编号获取用户
	 * @param code 角色编码
	 * @return Role 角色
	 */
	public ResourceType getByCode(String code);	

	/**
	 * 根据父菜单ID获取该菜单下的所有子孙菜单.<br/>
	 * 
	 * @param parentId
	 *            (如果为空，则为获取所有的菜单).<br/>
	 * @return ResourceTypeList.
	 */
	public List<ResourceType> listByParent(String parentId);

	/**
	 * 根据菜单ID查找菜单（可用于判断菜单下是否还有子菜单）.
	 * 
	 * @param parentId
	 *            .
	 * @return ResourceTypeList.
	 */
	public List<ResourceType> listByParentId(String parentId);

	/***
	 * 根据名称和是否叶子节点查询数据
	 * 
	 * @param isLeaf
	 *            是否是叶子节点
	 * @param name
	 *            节点名称
	 * @return
	 */
	public List<ResourceType> getResourceTypeByNameAndIsLeaf(Map<String, Object> map);
	
	public int deleteSubResourceTypes(String parentId);

}