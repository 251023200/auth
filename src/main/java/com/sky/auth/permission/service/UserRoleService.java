package com.sky.auth.permission.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.auth.permission.dao.UserRoleDao;
import com.sky.auth.permission.domain.Role;
import com.sky.auth.permission.domain.User;
import com.sky.auth.permission.domain.UserRole;

/**
 * 用户角色操作
 * @author yangfan
 *
 */
@Service
public class UserRoleService{
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	/**
	 * 通过id获取用户
	 * @param id 用户姓名
	 * @return UserRole 用户角色
	 */
	public UserRole getUserRoleById(String id){
		return userRoleDao.getById(id);
	}
	
	
	/**
	 * 添加用户角色
	 * @param user 待添加用户信息
	 * @return
	 */
	public int addUserRole(UserRole userRole){
		return userRoleDao.insert(userRole);
	}
	
	/**
	 * 获取所有用户角色
	 * @return
	 */
	public List<UserRole> getAllUserRoles(){
		return userRoleDao.listAll();
	}
	
	/**
	 * 根据用户id删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deleteUserRoleById(String id){
		return userRoleDao.deleteById(id);
	}
	
	/**
	 * 通过用户编号批量删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deleteUserRoleByIds(String[] ids){
		return userRoleDao.deleteByIds(ids);
	}
	
	/**
	 * 删除用户的角色
	 * @param userId
	 * @return
	 */
	public int deleteUserRoleByUserId(String userId){
		return userRoleDao.deleteByUserId(userId);
	}
	
	/**
	 * 查询用户拥有的角色
	 * @param userId
	 * @return
	 */
	public List<Role> getRolesByUserId(String userId){
		return userRoleDao.getRolesByUserId(userId);
	}
	
	/**
	 * 批量添加
	 * @param userRoles
	 * @return
	 */
	public int addUserRoles(List<UserRole> userRoles){
		return userRoleDao.batchInsert(userRoles);
	}
	
	/**
	 * 查询用户拥有的角色
	 * @param userId
	 * @return
	 */
	/*
	public List<Role> searchRolesByUserId(int pageNum,int pageSize,Role role){
		return userRoleDao.searchRolesByUserId(pageNum,pageSize,role);
	}
	*/
	
	/**
	 * 查询拥有角色的用户
	 * @param roleId
	 * @return
	 */
	public List<User> getUsersByRoleId(String roleId){
		return userRoleDao.getUsersByRoleId(roleId);
	}
	
	/**
	 * 通过用户编号和角色编号查询
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public UserRole getUserRoleByUserIdAndRoleId(String userId,String roleId){
		return userRoleDao.getByUserIdAndRoleId(userId, roleId);
	}
	
	/**
	 * 通过用户编号和角色编号查询
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public int deleteUserRoleByUserIdAndRoleId(String userId,String roleId){
		return userRoleDao.deleteByUserIdAndRoleId(userId, roleId);
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
	
	public List<Role> searchRoles(String userId,Role role){
		List<Role> roles = userRoleDao.searchRoles(userId,role);
		return roles;
	}
	
	/**
	 * 分页查询分配给用户userId的角色
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @param role
	 * @return
	 */
	public PageInfo<Role> searchRoles(int pageNum,int pageSize,String userId,Role role){
		PageHelper.startPage(pageNum, pageSize);
		List<Role> roles = userRoleDao.searchRoles(userId,role);
		PageInfo<Role> pageInfo = new PageInfo<Role>(roles);
		return pageInfo;
	}
	/**
	 * 分页查询没有分配给用户userId的角色
	 * @param pageNum
	 * @param pageSize
	 * @param userId
	 * @param role
	 * @return
	 */
	public PageInfo<Role> searchLeftRoles(int pageNum,int pageSize,String userId,Role role){
		PageHelper.startPage(pageNum, pageSize);
		List<Role> roles = userRoleDao.searchLeftRoles(userId,role);
		PageInfo<Role> pageInfo = new PageInfo<Role>(roles);
		return pageInfo;
	}
	
}
