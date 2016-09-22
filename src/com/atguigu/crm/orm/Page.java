package com.atguigu.crm.orm;

import java.util.List;



public class Page<T> {

	private int pageNo;
	private int pageSize=3;
	
	private long totalElments;
	private List<T> content;
	
	public int getNext(){
		if(this.isHasNext()){
			this.pageNo++;
		}
		return this.pageNo;
	}
	
	public boolean isHasNext(){
		return this.pageNo<this.getTotalPage();
	}
	
	public int getPrev(){
		if(this.isHasPrev()){
			return this.pageNo-1;
		}
		return this.pageNo;
	}
	
	public boolean isHasPrev(){
		return this.pageNo>1;
	}
	
	public int getTotalPage(){
		int totalPage=(int)this.totalElments/this.pageSize;
		
		if(this.totalElments%this.pageSize!=0){
			totalPage+=1;
		}
		return totalPage;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		if(pageNo<1){
			pageNo=1;
		}
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalElments() {
		return totalElments;
	}
	public void setTotalElments(long totalElments) {
		
		this.totalElments = totalElments;
	}
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> content) {
		this.content = content;
	}

	public Page(int pageNo, int pageSize, long totalElments, List<T> content) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalElments = totalElments;
		this.content = content;
	}

	public Page() {
		super();
	}
	
	
}
