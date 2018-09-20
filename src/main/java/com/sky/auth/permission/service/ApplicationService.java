package com.sky.auth.permission.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.auth.permission.dao.ApplicationDao;
import com.sky.auth.permission.domain.Application;

/**
 * 
 * @author yangfan
 *
 */
@Service
public class ApplicationService {
	
	@Autowired
	private ApplicationDao applicationDao;
	
	/**
	 * 通过id获取用户
	 * @param id 角色编号
	 * @return Application 用户
	 */
	public Application getApplicationById(String id){
		return applicationDao.getById(id);
	}
	
	/**
	 * 通过角色名称获取角色
	 * @param name 角色名称
	 * @return List<Application> 角色列表
	 */
	public List<Application> getApplicationsByName(String name){
		return applicationDao.getByName(name);
	}
	
	/**
	 * 通过部门编号获取角色
	 * @param code 角色编码
	 * @return Application 角色
	 */
	public Application getApplicationByCode(String code){
		return applicationDao.getByCode(code);
	}
	
	/**
	 * 添加角色
	 * @param application 待添加角色信息
	 * @return
	 */
	public int addApplication(Application application){
		return applicationDao.insert(application);
	}
	
	/**
	 * 获取所有角色
	 * @return
	 */
	public List<Application> getAllApplications(){
		return applicationDao.listAll();
	}
	
	/**
	 * 通过角色id修改用户信息
	 * @param user 
	 * @return
	 */
	public int updateApplication(Application application){
		return applicationDao.update(application);
	}
	
	/**
	 * 根据角色id删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deleteApplicationById(String id){
		return applicationDao.deleteById(id);
	}
	
	/**
	 * 通过角色编号批量删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deleteApplicationsByIds(String[] ids){
		return applicationDao.deleteByIds(ids);
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
	 * 分页查询Application
	 * @param pageNum
	 * @param pageSize
	 * @param user
	 * @return
	 */
	public PageInfo<Application> search(int pageNum,int pageSize,Application application){
		PageHelper.startPage(pageNum, pageSize);
		List<Application> applications = applicationDao.search(application);
		PageInfo<Application> pageInfo = new PageInfo<Application>(applications);
		return pageInfo;
	}
	
}
