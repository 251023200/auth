package com.sky.auth.permission.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.auth.permission.dao.UserDao;
import com.sky.auth.permission.domain.Menu;
import com.sky.auth.permission.domain.Operation;
import com.sky.auth.permission.domain.Permission;
import com.sky.auth.permission.domain.User;

/**
 * 用户操作
 * @author yangfan
 *
 */
@Service
public class UserService{
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * 通过id获取用户
	 * @param name 用户姓名
	 * @return User 用户
	 */
	public User getUserById(String id){
		return userDao.getById(id);
	}
	
	/**
	 * 通过用户姓名获取用户
	 * @param name 用户姓名
	 * @return User 用户
	 */
	public List<User> getUserByName(String name){
		return userDao.getByName(name);
	}
	
	/**
	 * 通过系统用户名获取用户
	 * @param userName 系统用户名
	 * @return User 用户
	 */
	public User getUserByUserName(String userName){
		return userDao.getByUserName(userName);
	}
	
	/**
	 * 通过用户编号获取用户
	 * @param userNo 用户编号(不同于id,id为唯一标识,userNo为用户有意义的编号)
	 * @return User 用户
	 */
	public User getUserByUserNo(String userNo){
		return userDao.getByUserNo(userNo);
	}
	
	/**
	 * 添加用户
	 * @param user 待添加用户信息
	 * @return
	 */
	public int addUser(User user){
		return userDao.insert(user);
	}
	
	/**
	 * 获取所有用户
	 * @return
	 */
	public List<User> getAllUsers(){
		return userDao.listAll();
	}
	
	/**
	 * 通过用户id修改用户信息
	 * @param user 
	 * @return
	 */
	public int updateUser(User user){
		return userDao.update(user);
	}
	
	/**
	 * 根据用户id删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deleteUserById(String id){
		return userDao.deleteById(id);
	}
	
	/**
	 * 通过用户编号批量删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deleteUsersByIds(String[] userIds){
		return userDao.deleteByIds(userIds);
	}
	
	/**
	 * 通过用户编号批量删除用户
	 * @param users [{"id":"xx"},{"id":"xx"},...]
	 * @return
	 */
	public int deleteUsers(List<User> users){
		return userDao.delete(users);
	}
	
	/**
	 * 通过用户名和密码查询用户
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	public User getUserByUserNameAndPassword(String username,String password){
		return userDao.getByUserNameAndPassword(username,password);
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
	
	
	public PageInfo<User> search(int pageNum,int pageSize,User user){
		PageHelper.startPage(pageNum, pageSize);
		List<User> users = userDao.search(user);
		PageInfo<User> pageInfo = new PageInfo<User>(users);
		return pageInfo;
	}
	
	public PageInfo<Permission> searchPermissions(int pageNum,int pageSize,String userId,Permission permission){
		PageHelper.startPage(pageNum, pageSize);
		List<Permission> permissions = userDao.searchPermissions(userId,permission);
		PageInfo<Permission> pageInfo = new PageInfo<Permission>(permissions);
		return pageInfo;
	}
	
	/**
	 * 获取用户所有的操作接口
	 * @param userId
	 * @return
	 */
	public List<Operation> getAllOperations(String userId){
		List<Operation> operations = userDao.getAllOperations(userId);
		return operations;
	}
	
	public PageInfo<Operation> searchOperations(int pageNum,int pageSize,String userId,Operation operation){
		PageHelper.startPage(pageNum, pageSize);
		List<Operation> operations = userDao.searchOperations(userId,operation);
		PageInfo<Operation> pageInfo = new PageInfo<Operation>(operations);
		return pageInfo;
	}
	
	/**
	 * 获取用户所有的菜单
	 * @param userId
	 * @return
	 */
	public List<Menu> getMenus(String userId){
		return userDao.getMenus(userId);
	}
}
