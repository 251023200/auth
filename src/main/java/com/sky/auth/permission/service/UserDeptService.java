package com.sky.auth.permission.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sky.auth.permission.dao.UserDeptDao;
import com.sky.auth.permission.domain.Dept;
import com.sky.auth.permission.domain.User;
import com.sky.auth.permission.domain.UserDept;

/**
 * 用户-部门
 * @author yangfan
 *
 */
@Service
public class UserDeptService{
	
	@Autowired
	private UserDeptDao userDeptDao;
	
	/**
	 * 通过用户编号和部门编号查找
	 * @param userId 用户编号
	 * @param deptId 部门编号
	 * @return UserDept 用户部门
	 */
	public UserDept getByUserIdAndDeptId(String userId,String deptId){
		return userDeptDao.getByUserIdAndDeptId(userId, deptId);
	}
	
	
	/**
	 * 通过id获取用户
	 * @param id 用户姓名
	 * @return UserDept 用户角色
	 */
	public UserDept getUserDeptById(String id){
		return userDeptDao.getById(id);
	}
	
	
	/**
	 * 添加用户角色
	 * @param user 待添加用户信息
	 * @return
	 */
	public int addUserDept(UserDept userDept){
		return userDeptDao.insert(userDept);
	}
	
	/**
	 * 获取所有用户角色
	 * @return
	 */
	public List<UserDept> getAllUserDepts(){
		return userDeptDao.listAll();
	}
	
	/**
	 * 根据用户id删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deleteUserDeptById(String id){
		return userDeptDao.deleteById(id);
	}
	
	/**
	 * 通过用户编号批量删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deleteUserDeptByIds(String[] ids){
		return userDeptDao.deleteByIds(ids);
	}
	
	/**
	 * 查询用户所在的部门
	 * @param userId
	 * @return
	 */
	public List<Dept> getDeptsByUserId(String userId){
		return userDeptDao.getDeptsByUserId(userId);
	}
	
	/**
	 * 查询部门下的用户
	 * @param deptId
	 * @return
	 */
	public List<User> getUsersByDeptId(String deptId){
		return userDeptDao.getUsersByDeptId(deptId);
	}
	
	/**
	 * 通过用户编号和角色编号查询
	 * @param userId
	 * @param deptId
	 * @return
	 */
	public UserDept getUserDeptByUserIdAndDeptId(String userId,String deptId){
		return userDeptDao.getByUserIdAndDeptId(userId, deptId);
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
	
}
