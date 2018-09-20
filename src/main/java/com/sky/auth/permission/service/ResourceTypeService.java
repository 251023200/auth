package com.sky.auth.permission.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sky.auth.permission.dao.ResourceTypeDao;
import com.sky.auth.permission.domain.ResourceType;


/**
 * 
 * @author yangfan
 *
 */
@Service
public class ResourceTypeService {
	
	@Autowired
	private ResourceTypeDao resourceTypeDao;
	
	
	/**
	 * 通过id获取用户
	 * @param id 资源编号
	 * @return ResourceType 用户
	 */
	public ResourceType getResourceTypeById(String id){
		return resourceTypeDao.getById(id);
	}
	
	/**
	 * 添加资源
	 * @param resourceType 待添加资源信息
	 * @return
	 */
	public int addResourceType(ResourceType resourceType){
		return resourceTypeDao.insert(resourceType);
	}
	
	/**
	 * 获取所有资源
	 * @return
	 */
	public List<ResourceType> getAllResourceTypes(){
		return resourceTypeDao.listAll();
	}
	
	/**
	 * 通过资源id修改用户信息
	 * @param user 
	 * @return
	 */
	public int updateResourceType(ResourceType resourceType){
		return resourceTypeDao.update(resourceType);
	}
	
	/**
	 * 根据资源id删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deleteResourceTypeById(String id){
		return resourceTypeDao.deleteById(id);
	}
	
	
	/**
	 * 通过资源编号批量删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deleteResourceTypesByIds(String[] ids){
		return resourceTypeDao.deleteByIds(ids);
	}
	
	/**
	 * 返回树形资源
	 * @return
	 */
//	public List<ResourceType> listTree() {
//		List<ResourceType> resourceTypes=resourceTypeDao.listAll();
//		return resourceTypes;
//	}
	
	 /**
     * 返回
     */
//    public List<ResourceType> listTree(String userId){
//    	List<ResourceType> resourceTypes=resourceTypeDao.getResourceTypesByUserId(userId);
//		return resourceTypes;
//    }
    
    
//	private List<ResourceTypeTreeNode> wapTree(List<ResourceType> list){
//		List<ResourceTypeTreeNode> treeNodes=new ArrayList<ResourceTypeTreeNode>();
//	     Map<String,ResourceTypeTreeNode> map=new HashMap<String,ResourceTypeTreeNode>();
//		for(ResourceType resourceType:list){
//			if(resourceType!=null){
//				ResourceTypeTreeNode node=new ResourceTypeTreeNode();
//				node.setId(resourceType.getId());
//				node.setText(resourceType.getName());
//				node.setIcon(resourceType.getIcon());
//				node.setName(resourceType.getName());
//				node.setUrl(resourceType.getUrl());
//				node.setSeq(resourceType.getSeq());
//				node.setActive(resourceType.getActive());
//				node.setParentId(resourceType.getParentId());
//				map.put(resourceType.getId(), node);
//			}
//		}
//		for(Entry<String,ResourceTypeTreeNode> entry:map.entrySet()){
//			ResourceTypeTreeNode node=entry.getValue();
//			String parentId=node.getParentId();
//			if(parentId==null ||parentId.equals("0")){
//				treeNodes.add(node);
//			}else{
//				ResourceTypeTreeNode parentNode=map.get(parentId);
//				if (parentNode != null) {
//					parentNode.addChildNode(node);
//				}
//			}
//			
//		}
//		sortTree(treeNodes);
//		return treeNodes;
//	}
//    private void sortTree(List<ResourceTypeTreeNode> nodeList){
//    	if (nodeList == null) {
//			return;
//		}
//		Collections.sort(nodeList, new Comparator<ResourceTypeTreeNode>() {
//
//			@Override
//			public int compare(ResourceTypeTreeNode o1, ResourceTypeTreeNode o2) {
//				return o1.getSeq()<o2.getSeq() ? 1 : -1;
//				/*
//				Integer n1 = Integer.valueOf(o1.getSeq());
//				Integer n2 =Integer.valueOf( o2.getSeq());
//				if (n1 == null && n2 == null) {
//					return 0;
//				}
//				if (n1 == null) {
//					return 1;
//				}
//				if (n2 == null) {
//					return -1;
//				}
//				return n1.compareTo(n2);
//				*/
//			}
//			
//		});
//		
//		for (ResourceTypeTreeNode resourceTypeTreeNode : nodeList) {
//			sortTree(resourceTypeTreeNode.getNodes());
//		}
//    }
    
    
    
    /**
	 * 返回树形资源
	 * @return
	 */
//	public List<StatedResourceTypeTreeNode> listStatedTree(String roleId) {
//		List<ResourceType> allResourceTypes=resourceTypeDao.listAll();
//		List<ResourceType> resourceTypes = roleResourceTypeDao.getResourceTypesByRoleId(roleId);
//		return wapStatedTree(allResourceTypes,resourceTypes);
//	}
//	private List<StatedResourceTypeTreeNode> wapStatedTree(List<ResourceType> allResourceTypes,List<ResourceType> resourceTypes){
//		HashSet<String> resourceTypeIds = new HashSet<String>();
//		for(int i=0;i<resourceTypes.size();i++){
//			resourceTypeIds.add(resourceTypes.get(i).getId());
//		}
//		List<StatedResourceTypeTreeNode> treeNodes=new ArrayList<StatedResourceTypeTreeNode>();
//	     Map<String,StatedResourceTypeTreeNode> map=new HashMap<String,StatedResourceTypeTreeNode>();
//		for(ResourceType resourceType:allResourceTypes){
//			if(resourceType!=null){
//				StatedResourceTypeTreeNode node=new StatedResourceTypeTreeNode();
//				node.setId(resourceType.getId());
//				node.setText(resourceType.getName());
//				node.setIcon(resourceType.getIcon());
//				node.setName(resourceType.getName());
//				node.setUrl(resourceType.getUrl());
//				node.setSeq(resourceType.getSeq());
//				node.setActive(resourceType.getActive());
//				node.setParentId(resourceType.getParentId());
//				if(resourceTypeIds.contains(resourceType.getId())){
//					node.setAssigned(1);
//				}else{
//					node.setAssigned(0);
//				}
//				map.put(resourceType.getId(), node);
//			}
//		}
//		for(Entry<String,StatedResourceTypeTreeNode> entry:map.entrySet()){
//			StatedResourceTypeTreeNode node=entry.getValue();
//			String parentId=node.getParentId();
//			if(parentId==null ||parentId.equals("0")){
//				treeNodes.add(node);
//			}else{
//				ResourceTypeTreeNode parentNode=map.get(parentId);
//				if (parentNode != null) {
//					parentNode.addChildNode(node);
//				}
//			}
//			
//		}
//		StatedResourceTypeTree(treeNodes);
//		return treeNodes;
//	}
//	private void StatedResourceTypeTree(List<StatedResourceTypeTreeNode> nodeList){
//    	if (nodeList == null) {
//			return;
//		}
//		Collections.sort(nodeList, new Comparator<ResourceTypeTreeNode>() {
//
//			@Override
//			public int compare(ResourceTypeTreeNode o1, ResourceTypeTreeNode o2) {
//				return o1.getSeq()<o2.getSeq() ? 1 : -1;
//				/*
//				Integer n1 = Integer.valueOf(o1.getSeq());
//				Integer n2 =Integer.valueOf( o2.getSeq());
//				if (n1 == null && n2 == null) {
//					return 0;
//				}
//				if (n1 == null) {
//					return 1;
//				}
//				if (n2 == null) {
//					return -1;
//				}
//				return n1.compareTo(n2);
//				*/
//			}
//			
//		});
//		
//		for (ResourceTypeTreeNode resourceTypeTreeNode : nodeList) {
//			sortTree(resourceTypeTreeNode.getNodes());
//		}
//    }
}
