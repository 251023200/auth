package com.sky.auth.permission.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.auth.permission.dao.ResourcePermissionDao;
import com.sky.auth.permission.domain.Permission;
import com.sky.auth.permission.domain.Resource;
import com.sky.auth.permission.domain.ResourcePermission;

/**
 * 资源权限操作
 * @author yangfan
 *
 */
@Service
public class ResourcePermissionService{
	
	@Autowired
	private ResourcePermissionDao resourcePermissionDao;
	
	/**
	 * 通过id获取资源
	 * @param id 资源姓名
	 * @return ResourcePermission 资源权限
	 */
	public ResourcePermission getResourcePermissionById(String id){
		return resourcePermissionDao.getById(id);
	}
	
	
	/**
	 * 添加资源权限
	 * @param resource 待添加资源信息
	 * @return
	 */
	public int addResourcePermission(ResourcePermission resourcePermission){
		return resourcePermissionDao.insert(resourcePermission);
	}
	
	/**
	 * 获取所有资源权限
	 * @return
	 */
	public List<ResourcePermission> getAllResourcePermissions(){
		return resourcePermissionDao.listAll();
	}
	
	/**
	 * 根据资源id删除资源
	 * @param id 资源编号
	 * @return
	 */
	public int deleteResourcePermissionById(String id){
		return resourcePermissionDao.deleteById(id);
	}
	
	/**
	 * 通过资源编号批量删除资源
	 * @param id 资源编号
	 * @return
	 */
	public int deleteResourcePermissionByIds(String[] ids){
		return resourcePermissionDao.deleteByIds(ids);
	}
	
	/**
	 * 删除资源的权限
	 * @param resourceId
	 * @return
	 */
	public int deleteResourcePermissionByResourceId(String resourceId){
		return resourcePermissionDao.deleteByResourceId(resourceId);
	}
	
	/**
	 * 删除权限拥有的全部资源
	 * @param resourceId
	 * @return
	 */
	public int deleteResourcePermissionByPermissionId(String permissionId){
		return resourcePermissionDao.deleteByPermissionId(permissionId);
	}
	
	/**
	 * 查询资源拥有的权限
	 * @param resourceId
	 * @return
	 */
	public List<Permission> getPermissionsByResourceId(String resourceId){
		return resourcePermissionDao.getPermissionsByResourceId(resourceId);
	}
	
	/**
	 * 批量添加
	 * @param resourcePermissions
	 * @return
	 */
	public int addResourcePermissions(List<ResourcePermission> resourcePermissions){
		return resourcePermissionDao.batchInsert(resourcePermissions);
	}
	
	/**
	 * 查询资源拥有的权限
	 * @param resourceId
	 * @return
	 */
	/*
	public List<Permission> searchPermissionsByResourceId(int pageNum,int pageSize,Permission permission){
		return resourcePermissionDao.searchPermissionsByResourceId(pageNum,pageSize,permission);
	}
	*/
	
	/**
	 * 查询拥有权限的资源
	 * @param permissionId
	 * @return
	 */
	public List<Resource> getResourcesByPermissionId(String permissionId){
		return resourcePermissionDao.getResourcesByPermissionId(permissionId);
	}
	
	/**
	 * 通过资源编号和权限编号查询
	 * @param resourceId
	 * @param permissionId
	 * @return
	 */
	public ResourcePermission getResourcePermissionByResourceIdAndPermissionId(String resourceId,String permissionId){
		return resourcePermissionDao.getByResourceIdAndPermissionId(resourceId, permissionId);
	}
	
	/**
	 * 通过资源编号和权限编号查询
	 * @param resourceId
	 * @param permissionId
	 * @return
	 */
	public int deleteResourcePermissionByResourceIdAndPermissionId(String resourceId,String permissionId){
		return resourcePermissionDao.deleteByResourceIdAndPermissionId(resourceId, permissionId);
	}
	
	/**
	 * 分页查询
	 * @param pageNum 页数
	 * @param pageSize 页大小
	 * @param List<Resource>
	 * @return
	 */
//	public List<Resource> search(int offset,int limit,String orderBy,int asc,Resource resource){
//		return resourceDao.search(offset,limit,orderBy,asc,resource);
//	}
	
	public PageInfo<Permission> search(int pageNum,int pageSize,String resourceId,Permission permission){
		PageHelper.startPage(pageNum, pageSize);
		List<Permission> permissions = resourcePermissionDao.searchPermissionsByResourceId(resourceId,permission);
		PageInfo<Permission> pageInfo = new PageInfo<Permission>(permissions);
		return pageInfo;
	}
	
}
