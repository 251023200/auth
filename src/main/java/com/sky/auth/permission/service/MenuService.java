package com.sky.auth.permission.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sky.auth.permission.dao.MenuDao;
import com.sky.auth.permission.domain.Menu;


/**
 * 
 * @author yangfan
 *
 */
@Service
public class MenuService {
	
	@Autowired
	private MenuDao menuDao;
	
	
	/**
	 * 通过id获取用户
	 * @param id 菜单编号
	 * @return Menu 用户
	 */
	public Menu getMenuById(String id){
		return menuDao.getById(id);
	}
	
	/**
	 * 添加菜单
	 * @param menu 待添加菜单信息
	 * @return
	 */
	public int addMenu(Menu menu){
		return menuDao.insert(menu);
	}
	
	/**
	 * 获取所有菜单
	 * @return
	 */
	public List<Menu> getAllMenus(){
		return menuDao.listAll();
	}
	
	/**
	 * 通过菜单id修改用户信息
	 * @param user 
	 * @return
	 */
	public int updateMenu(Menu menu){
		return menuDao.update(menu);
	}
	
	/**
	 * 根据菜单id删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deleteMenuById(String id){
		return menuDao.deleteById(id);
	}
	
	/**
	 * 根据菜单id删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int cascadeDeleteMenus(String id){
		int count=0;
		List<Menu> menus = menuDao.listByParentId(id);
		for(int i=0;i<menus.size();i++){
			count+=cascadeDeleteMenus(menus.get(i).getId());
		}
		count += menuDao.deleteById(id);
		return count;
	}
	
	
	/**
	 * 通过菜单编号批量删除用户
	 * @param id 用户编号
	 * @return
	 */
	public int deleteMenusByIds(String[] ids){
		return menuDao.deleteByIds(ids);
	}
	
	public List<Menu> getMenusByUserId(String userId){
		return menuDao.getMenusByUserId(userId);
	}
	/**
	 * 返回树形菜单
	 * @return
	 */
//	public List<Menu> listTree() {
//		List<Menu> menus=menuDao.listAll();
//		return menus;
//	}
	
	 /**
     * 返回
     */
//    public List<Menu> listTree(String userId){
//    	List<Menu> menus=menuDao.getMenusByUserId(userId);
//		return menus;
//    }
    
    
//	private List<MenuTreeNode> wapTree(List<Menu> list){
//		List<MenuTreeNode> treeNodes=new ArrayList<MenuTreeNode>();
//	     Map<String,MenuTreeNode> map=new HashMap<String,MenuTreeNode>();
//		for(Menu menu:list){
//			if(menu!=null){
//				MenuTreeNode node=new MenuTreeNode();
//				node.setId(menu.getId());
//				node.setText(menu.getName());
//				node.setIcon(menu.getIcon());
//				node.setName(menu.getName());
//				node.setUrl(menu.getUrl());
//				node.setSeq(menu.getSeq());
//				node.setActive(menu.getActive());
//				node.setParentId(menu.getParentId());
//				map.put(menu.getId(), node);
//			}
//		}
//		for(Entry<String,MenuTreeNode> entry:map.entrySet()){
//			MenuTreeNode node=entry.getValue();
//			String parentId=node.getParentId();
//			if(parentId==null ||parentId.equals("0")){
//				treeNodes.add(node);
//			}else{
//				MenuTreeNode parentNode=map.get(parentId);
//				if (parentNode != null) {
//					parentNode.addChildNode(node);
//				}
//			}
//			
//		}
//		sortTree(treeNodes);
//		return treeNodes;
//	}
//    private void sortTree(List<MenuTreeNode> nodeList){
//    	if (nodeList == null) {
//			return;
//		}
//		Collections.sort(nodeList, new Comparator<MenuTreeNode>() {
//
//			@Override
//			public int compare(MenuTreeNode o1, MenuTreeNode o2) {
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
//		for (MenuTreeNode menuTreeNode : nodeList) {
//			sortTree(menuTreeNode.getNodes());
//		}
//    }
    
    
    
    /**
	 * 返回树形菜单
	 * @return
	 */
//	public List<StatedMenuTreeNode> listStatedTree(String roleId) {
//		List<Menu> allMenus=menuDao.listAll();
//		List<Menu> menus = roleMenuDao.getMenusByRoleId(roleId);
//		return wapStatedTree(allMenus,menus);
//	}
//	private List<StatedMenuTreeNode> wapStatedTree(List<Menu> allMenus,List<Menu> menus){
//		HashSet<String> menuIds = new HashSet<String>();
//		for(int i=0;i<menus.size();i++){
//			menuIds.add(menus.get(i).getId());
//		}
//		List<StatedMenuTreeNode> treeNodes=new ArrayList<StatedMenuTreeNode>();
//	     Map<String,StatedMenuTreeNode> map=new HashMap<String,StatedMenuTreeNode>();
//		for(Menu menu:allMenus){
//			if(menu!=null){
//				StatedMenuTreeNode node=new StatedMenuTreeNode();
//				node.setId(menu.getId());
//				node.setText(menu.getName());
//				node.setIcon(menu.getIcon());
//				node.setName(menu.getName());
//				node.setUrl(menu.getUrl());
//				node.setSeq(menu.getSeq());
//				node.setActive(menu.getActive());
//				node.setParentId(menu.getParentId());
//				if(menuIds.contains(menu.getId())){
//					node.setAssigned(1);
//				}else{
//					node.setAssigned(0);
//				}
//				map.put(menu.getId(), node);
//			}
//		}
//		for(Entry<String,StatedMenuTreeNode> entry:map.entrySet()){
//			StatedMenuTreeNode node=entry.getValue();
//			String parentId=node.getParentId();
//			if(parentId==null ||parentId.equals("0")){
//				treeNodes.add(node);
//			}else{
//				MenuTreeNode parentNode=map.get(parentId);
//				if (parentNode != null) {
//					parentNode.addChildNode(node);
//				}
//			}
//			
//		}
//		StatedMenuTree(treeNodes);
//		return treeNodes;
//	}
//	private void StatedMenuTree(List<StatedMenuTreeNode> nodeList){
//    	if (nodeList == null) {
//			return;
//		}
//		Collections.sort(nodeList, new Comparator<MenuTreeNode>() {
//
//			@Override
//			public int compare(MenuTreeNode o1, MenuTreeNode o2) {
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
//		for (MenuTreeNode menuTreeNode : nodeList) {
//			sortTree(menuTreeNode.getNodes());
//		}
//    }
}
