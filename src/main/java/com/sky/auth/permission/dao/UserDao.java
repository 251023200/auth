package com.sky.auth.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sky.auth.dao.BaseDao;
import com.sky.auth.permission.domain.Menu;
import com.sky.auth.permission.domain.Operation;
import com.sky.auth.permission.domain.Permission;
import com.sky.auth.permission.domain.User;

/**
 * 用户操作
 * @author yangfan
 *
 */
public interface UserDao extends BaseDao<User>{
	
	public List<User> getByName(String name);			//通过姓名获取用户
	
	public User getByUserName(String userName);	//通过用户名获取用户
	
	public User getByUserNo(String userNo);		//通过用户编号获取用户
	
	public List<Permission> getPermissionsById(String id); //获取用户权限
	
	public User getByUserNameAndPassword(@Param("userName") String userName,@Param("password") String password); //通过用户名和密码获取用户
	
	 //public List<User> search(User user);	//搜索
	
	public List<Operation> getAllOperations(@Param("userId") String userId);	//获取用户的所有接口
	
	public List<Permission> searchPermissions(@Param("userId") String userId,@Param("permission") Permission permission);	//获取用户的权限
	
	public List<Operation> searchOperations(@Param("userId") String userId,@Param("operation") Operation operation);	//获取用户的操作
	
	public List<Menu> getMenus(String userId);	//获取用户的菜单
}
