package com.sky.auth.util;

/**
 * 分页
 * @author 杨帆
 *
 */
public class Page {

	public static final int DEFAULT_PAGE_SIZE = 5;

	
	private int pageNum = 1;
	private int pageSize = DEFAULT_PAGE_SIZE;
	
	public Page(String pageNum,String pageSize){
		if(pageNum!=null&&!"".equals(pageNum.trim())){
			int iPageNum = Integer.parseInt(pageNum.trim());
			if(iPageNum>0){
				this.pageNum=iPageNum;
			}
		}
		if(pageSize!=null&&!"".equals(pageSize.trim())){
			int iPageSize = Integer.parseInt(pageSize.trim());
			if(iPageSize>0){
				this.pageSize = iPageSize;
			}
		}
	}
	
	public int getPageNum(){
		return pageNum;
	}
	
	public int getPageSize(){
		return pageSize;
	}
	
}
