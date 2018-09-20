package com.sky.auth.util;

public class PageIndex {
	private int index=0;
    private int total=0;
    private int offset=100;//每次取值条数
    
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public synchronized  int add() {
		return index++;
	}
    
    
}
