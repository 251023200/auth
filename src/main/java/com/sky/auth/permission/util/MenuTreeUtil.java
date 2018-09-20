package com.sky.auth.permission.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.sky.auth.permission.domain.Menu;
import com.sky.auth.permission.dto.TreeNode;
import com.sky.auth.permission.dto.MenuTreeNode;
import com.sky.auth.permission.dto.StatedMenuTreeNode;

public class MenuTreeUtil {
	/**
	 * 所有菜单
	 * @param list
	 * @return
	 */
	public static List<MenuTreeNode> wapTree(List<Menu> list) {
		List<MenuTreeNode> menuTreeNodes = new ArrayList<MenuTreeNode>();
		Map<String, MenuTreeNode> map = new HashMap<String, MenuTreeNode>();
		for (Menu menu : list) {
			if (menu != null) {
				MenuTreeNode node = new MenuTreeNode();
				node.setId(menu.getId());
				node.setText(menu.getName());
				node.setIcon(menu.getIcon());
				node.setName(menu.getName());
				node.setUrl(menu.getUrl());
				node.setSeq(menu.getSeq());
				node.setActive(menu.getActive());
				node.setParentId(menu.getParentId());
				
				//System.out.println(menu.getId() + "---" +menu.getParentId() + "===" +menu.getName());
				map.put(menu.getId(), node);
			}
		}
		
		for (Entry<String, MenuTreeNode> entry : map.entrySet()) {
			MenuTreeNode node = entry.getValue();
			String parentId = node.getParentId();
			//System.out.println(node.getId()+" xxx "+node.getParentId()+" xxx "+node.getText());
			if (parentId == null || parentId.equals("0")) {
				menuTreeNodes.add(node);
			} else {
				MenuTreeNode parentNode = map.get(parentId);
				if (parentNode != null) {
					parentNode.addChildNode(node);
				}
			}

		}
		//sortTree(treeNodes);
		List<TreeNode> treeNodes = new ArrayList<TreeNode>(menuTreeNodes.size());
		for(int i=0;i<menuTreeNodes.size();i++){
			treeNodes.add(menuTreeNodes.get(i));
		}
		sortTree(treeNodes);
		for(int i=0;i<treeNodes.size();i++){
			menuTreeNodes.set(i, (MenuTreeNode)treeNodes.get(i));
		}
		return menuTreeNodes;
	}

	private static void sortTree(List<TreeNode> nodeList) {
		if (nodeList == null) {
			return;
		}
		Collections.sort(nodeList, new Comparator<TreeNode>() {

			@Override
			public int compare(TreeNode o1, TreeNode o2) {
				int seq1 = o1.getSeq();
				int seq2 = o2.getSeq();
				if (seq1 == seq2) {
					return 0;
				}
				return seq1 < seq2 ? -1 : 1;
				/*
				 * Integer n1 = Integer.valueOf(o1.getSeq()); Integer n2
				 * =Integer.valueOf( o2.getSeq()); if (n1 == null && n2 == null)
				 * { return 0; } if (n1 == null) { return 1; } if (n2 == null) {
				 * return -1; } return n1.compareTo(n2);
				 */
			}

		});

		for (TreeNode TreeNode : nodeList) {
			sortTree(TreeNode.getNodes());
		}
	}
	
	/**
	 * 所有菜单,以及针对某个角色,该菜单是否被赋予该角色
	 * @param allMenus
	 * @param menus
	 * @return
	 */
	public static List<StatedMenuTreeNode> wapStatedTree(List<Menu> allMenus, List<Menu> menus) {
		HashSet<String> menuIds = new HashSet<String>();
		for (int i = 0; i < menus.size(); i++) {
			menuIds.add(menus.get(i).getId());
		}
		List<StatedMenuTreeNode> treeNodes = new ArrayList<StatedMenuTreeNode>();
		Map<String, StatedMenuTreeNode> map = new HashMap<String, StatedMenuTreeNode>();
		for (Menu menu : allMenus) {
			if (menu != null) {
				StatedMenuTreeNode node = new StatedMenuTreeNode();
				node.setId(menu.getId());
				node.setText(menu.getName());
				node.setIcon(menu.getIcon());
				node.setName(menu.getName());
				node.setUrl(menu.getUrl());
				node.setSeq(menu.getSeq());
				node.setActive(menu.getActive());
				node.setParentId(menu.getParentId());
				if (menuIds.contains(menu.getId())) {
					node.setAssigned(1);
				} else {
					node.setAssigned(0);
				}
				map.put(menu.getId(), node);
			}
		}
		for (Entry<String, StatedMenuTreeNode> entry : map.entrySet()) {
			StatedMenuTreeNode node = entry.getValue();
			String parentId = node.getParentId();
			if (parentId == null || parentId.equals("0")) {
				treeNodes.add(node);
			} else {
				MenuTreeNode parentNode = map.get(parentId);
				if (parentNode != null) {
					parentNode.addChildNode(node);
				}
			}

		}
		StatedMenuTree(treeNodes);
		return treeNodes;
	}

	private static void StatedMenuTree(List<StatedMenuTreeNode> nodeList) {
		if (nodeList == null) {
			return;
		}
		Collections.sort(nodeList, new Comparator<MenuTreeNode>() {

			@Override
			public int compare(MenuTreeNode o1, MenuTreeNode o2) {
				int seq1 = o1.getSeq();
				int seq2 = o2.getSeq();
				if (seq1 == seq2) {
					return 0;
				}
				return seq1 < seq2 ? -1 : 1;
				/*
				 * Integer n1 = Integer.valueOf(o1.getSeq()); Integer n2
				 * =Integer.valueOf( o2.getSeq()); if (n1 == null && n2 == null)
				 * { return 0; } if (n1 == null) { return 1; } if (n2 == null) {
				 * return -1; } return n1.compareTo(n2);
				 */
			}

		});

		for (MenuTreeNode menuTreeNode : nodeList) {
			sortTree(menuTreeNode.getNodes());
		}
	}
}
