package com.sky.auth.permission.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.auth.permission.dao.ResourceDao;
import com.sky.auth.permission.domain.Resource;

/**
 * 资源操作
 * @author yangfan
 *
 */
@Service
public class ResourceService{
	
	@Autowired
	private ResourceDao resourceDao;
	
	/**
	 * 通过id获取资源
	 * @param id 资源编号
	 * @return Resource 资源
	 */
	public Resource getResourceById(String id){
		return resourceDao.getById(id);
	}
	
	/**
	 * 通过资源名称获取资源
	 * @param name 资源名称
	 * @return List<Resource> 资源列表
	 */
	public List<Resource> getResourcesByName(String name){
		return resourceDao.getByName(name);
	}
	
	/**
	 * 通过部门编号获取资源
	 * @param code 资源编码
	 * @return Resource 资源
	 */
	public Resource getResourceByCode(String code){
		return resourceDao.getByCode(code);
	}
	
	/**
	 * 添加资源
	 * @param role 待添加资源信息
	 * @return
	 */
	public int addResource(Resource role){
		return resourceDao.insert(role);
	}
	
	/**
	 * 获取所有资源
	 * @return
	 */
	public List<Resource> getAllResources(){
		return resourceDao.listAll();
	}
	
	/**
	 * 通过资源id修改资源信息
	 * @param user 
	 * @return
	 */
	public int updateResource(Resource role){
		return resourceDao.update(role);
	}
	
	/**
	 * 根据资源id删除资源
	 * @param id 资源编号
	 * @return
	 */
	public int deleteResourceById(String id){
		return resourceDao.deleteById(id);
	}
	
	/**
	 * 通过资源编号批量删除资源
	 * @param id 资源编号
	 * @return
	 */
	public int deleteResourcesByIds(String[] ids){
		return resourceDao.deleteByIds(ids);
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
	public PageInfo<Resource> search(int pageNum,int pageSize,Resource resource){
		PageHelper.startPage(pageNum, pageSize);
		List<Resource> resources = resourceDao.search(resource);
		PageInfo<Resource> pageInfo = new PageInfo<Resource>(resources);
		return pageInfo;
	}
	
	
}
