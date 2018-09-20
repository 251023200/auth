package com.sky.auth.permission.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.auth.permission.dao.GroupDao;
import com.sky.auth.permission.domain.Group;


/**
 * 组 操作
 * @author yangfan
 *
 */
@Service
public class GroupService{
	
	@Autowired
	private GroupDao groupDao;
	
	/**
	 * 通过id获取用户
	 * @param id 角色编号
	 * @return Group 用户
	 */
	public Group getGroupById(String id){
		return groupDao.getById(id);
	}
	
	/**
	 * 通过角色名称获取角色
	 * @param name 角色名称
	 * @return List<Group> 角色列表
	 */
	public List<Group> getGroupsByName(String name){
		return groupDao.getByName(name);
	}
	/**
	 * 通过部门名称获取部门
	 * @param name 部门名称
	 * @return List<Group> 部门
	 */
	public List<Group> getByName(String name){
		return groupDao.getByName(name);
	}
	
	/**
	 * 通过部门编号获取部门
	 * @param code 部门编号
	 * @return Group 部门
	 */
	public Group getByCode(String code){
		return groupDao.getByCode(code);
	}
	
	/**
	 * 添加角色
	 * @param role 待添加角色信息
	 * @return
	 */
	public int addGroup(Group role){
		return groupDao.insert(role);
	}
	
	/**
	 * 获取所有角色
	 * @return
	 */
	public List<Group> getAllGroups(){
		return groupDao.listAll();
	}
	
	/**
	 * 通过角色id修改用户信息
	 * @param user 
	 * @return
	 */
	public int updateGroup(Group role){
		return groupDao.update(role);
	}
	
	/**
	 * 根据角色id删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deleteGroupById(String id){
		return groupDao.deleteById(id);
	}
	
	/**
	 * 通过角色编号批量删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deleteGroupsByIds(String[] ids){
		return groupDao.deleteByIds(ids);
	}
	
	
	/**
	 * 分页查询
	 * @param pageNum 页数
	 * @param pageSize 页大小
	 * @param List<User>
	 * @return
	 */
//	public List<User> search(int offset,int limit,String orderBy,int asc,User user){
//		return userDao.search(offset,limit,orderBy,asc,user);
//	}
	
	/**
	 * 分页查询Group
	 * @param pageNum
	 * @param pageSize
	 * @param user
	 * @return
	 */
	public PageInfo<Group> search(int pageNum,int pageSize,Group group){
		PageHelper.startPage(pageNum, pageSize);
		List<Group> groups = groupDao.search(group);
		PageInfo<Group> pageInfo = new PageInfo<Group>(groups);
		return pageInfo;
	}
	
}
