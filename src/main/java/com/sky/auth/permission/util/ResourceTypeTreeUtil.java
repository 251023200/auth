package com.sky.auth.permission.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.sky.auth.permission.domain.ResourceType;
import com.sky.auth.permission.dto.ResourceTypeTreeNode;

public class ResourceTypeTreeUtil {
	/**
	 * 所有菜单
	 * @param list
	 * @return
	 */
	public static List<ResourceTypeTreeNode> wapTree(List<ResourceType> list) {
		List<ResourceTypeTreeNode> treeNodes = new ArrayList<ResourceTypeTreeNode>();
		Map<String, ResourceTypeTreeNode> map = new HashMap<String, ResourceTypeTreeNode>();
		for (ResourceType resourceType : list) {
			if (resourceType != null) {
				ResourceTypeTreeNode node = new ResourceTypeTreeNode();
				node.setId(resourceType.getId());
				node.setText(resourceType.getName());
				node.setIcon(resourceType.getIcon());
				node.setName(resourceType.getName());
				//node.setUrl(resourceType.getUrl());
				node.setCode(resourceType.getCode());
				node.setSeq(resourceType.getSeq());
				node.setActive(resourceType.getActive());
				node.setParentId(resourceType.getParentId());
				
				//System.out.println(resourceType.getId() + "---" +resourceType.getParentId() + "===" +resourceType.getName());
				map.put(resourceType.getId(), node);
			}
		}
		
		for (Entry<String, ResourceTypeTreeNode> entry : map.entrySet()) {
			ResourceTypeTreeNode node = entry.getValue();
			String parentId = node.getParentId();
			System.out.println(node.getId()+" xxx "+node.getParentId()+" xxx "+node.getText());
			if (parentId == null || parentId.equals("0")) {
				treeNodes.add(node);
			} else {
				ResourceTypeTreeNode parentNode = map.get(parentId);
				if (parentNode != null) {
					parentNode.addChildNode(node);
				}
			}

		}
		sortTree(treeNodes);
		return treeNodes;
	}

	private static void sortTree(List<ResourceTypeTreeNode> nodeList) {
		if (nodeList == null) {
			return;
		}
		Collections.sort(nodeList, new Comparator<ResourceTypeTreeNode>() {

			@Override
			public int compare(ResourceTypeTreeNode o1, ResourceTypeTreeNode o2) {
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

		for (ResourceTypeTreeNode resourceTypeTreeNode : nodeList) {
			sortTree(resourceTypeTreeNode.getNodes());
		}
	}
	
	/**
	 * 所有菜单,以及针对某个角色,该菜单是否被赋予该角色
	 * @param allResourceTypes
	 * @param resourceTypes
	 * @return
	 */
//	public static List<StatedResourceTypeTreeNode> wapStatedTree(List<ResourceType> allResourceTypes, List<ResourceType> resourceTypes) {
//		HashSet<String> resourceTypeIds = new HashSet<String>();
//		for (int i = 0; i < resourceTypes.size(); i++) {
//			resourceTypeIds.add(resourceTypes.get(i).getId());
//		}
//		List<StatedResourceTypeTreeNode> treeNodes = new ArrayList<StatedResourceTypeTreeNode>();
//		Map<String, StatedResourceTypeTreeNode> map = new HashMap<String, StatedResourceTypeTreeNode>();
//		for (ResourceType resourceType : allResourceTypes) {
//			if (resourceType != null) {
//				StatedResourceTypeTreeNode node = new StatedResourceTypeTreeNode();
//				node.setId(resourceType.getId());
//				node.setText(resourceType.getName());
//				node.setIcon(resourceType.getIcon());
//				node.setName(resourceType.getName());
//				node.setUrl(resourceType.getUrl());
//				node.setSeq(resourceType.getSeq());
//				node.setActive(resourceType.getActive());
//				node.setParentId(resourceType.getParentId());
//				if (resourceTypeIds.contains(resourceType.getId())) {
//					node.setAssigned(1);
//				} else {
//					node.setAssigned(0);
//				}
//				map.put(resourceType.getId(), node);
//			}
//		}
//		for (Entry<String, StatedResourceTypeTreeNode> entry : map.entrySet()) {
//			StatedResourceTypeTreeNode node = entry.getValue();
//			String parentId = node.getParentId();
//			if (parentId == null || parentId.equals("0")) {
//				treeNodes.add(node);
//			} else {
//				ResourceTypeTreeNode parentNode = map.get(parentId);
//				if (parentNode != null) {
//					parentNode.addChildNode(node);
//				}
//			}
//
//		}
//		StatedResourceTypeTree(treeNodes);
//		return treeNodes;
//	}

//	private static void StatedResourceTypeTree(List<StatedResourceTypeTreeNode> nodeList) {
//		if (nodeList == null) {
//			return;
//		}
//		Collections.sort(nodeList, new Comparator<ResourceTypeTreeNode>() {
//
//			@Override
//			public int compare(ResourceTypeTreeNode o1, ResourceTypeTreeNode o2) {
//				int seq1 = o1.getSeq();
//				int seq2 = o2.getSeq();
//				if (seq1 == seq2) {
//					return 0;
//				}
//				return seq1 < seq2 ? -1 : 1;
//				/*
//				 * Integer n1 = Integer.valueOf(o1.getSeq()); Integer n2
//				 * =Integer.valueOf( o2.getSeq()); if (n1 == null && n2 == null)
//				 * { return 0; } if (n1 == null) { return 1; } if (n2 == null) {
//				 * return -1; } return n1.compareTo(n2);
//				 */
//			}
//
//		});
//
//		for (ResourceTypeTreeNode resourceTypeTreeNode : nodeList) {
//			sortTree(resourceTypeTreeNode.getNodes());
//		}
//	}
}
