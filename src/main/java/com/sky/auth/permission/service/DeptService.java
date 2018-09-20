package com.sky.auth.permission.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.auth.permission.dao.DeptDao;
import com.sky.auth.permission.domain.Dept;

/**
 * 部门操作
 * @author yangfan
 *
 */
@Service
public class DeptService{
	
	@Autowired
	private DeptDao deptDao;
	
	/**
	 * 通过id获取用户
	 * @param id 角色编号
	 * @return Dept 用户
	 */
	public Dept getDeptById(String id){
		return deptDao.getById(id);
	}
	
	/**
	 * 通过角色名称获取角色
	 * @param name 角色名称
	 * @return List<Dept> 角色列表
	 */
	public List<Dept> getDeptsByName(String name){
		return deptDao.getByName(name);
	}
	/**
	 * 通过部门名称获取部门
	 * @param name 部门名称
	 * @return List<Dept> 部门
	 */
	public List<Dept> getByName(String name){
		return deptDao.getByName(name);
	}
	
	/**
	 * 通过部门编号获取部门
	 * @param code 部门编号
	 * @return Dept 部门
	 */
	public Dept getByCode(String code){
		return deptDao.getByCode(code);
	}
	
	/**
	 * 添加角色
	 * @param role 待添加角色信息
	 * @return
	 */
	public int addDept(Dept role){
		return deptDao.insert(role);
	}
	
	/**
	 * 获取所有角色
	 * @return
	 */
	public List<Dept> getAllDepts(){
		return deptDao.listAll();
	}
	
	/**
	 * 通过角色id修改用户信息
	 * @param user 
	 * @return
	 */
	public int updateDept(Dept role){
		return deptDao.update(role);
	}
	
	/**
	 * 根据角色id删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deleteDeptById(String id){
		return deptDao.deleteById(id);
	}
	
	/**
	 * 通过角色编号批量删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deleteDeptsByIds(String[] ids){
		return deptDao.deleteByIds(ids);
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
	 * 分页查询Dept
	 * @param pageNum
	 * @param pageSize
	 * @param user
	 * @return
	 */
	public PageInfo<Dept> search(int pageNum,int pageSize,Dept dept){
		PageHelper.startPage(pageNum, pageSize);
		List<Dept> depts = deptDao.search(dept);
		PageInfo<Dept> pageInfo = new PageInfo<Dept>(depts);
		return pageInfo;
	}
	
}
