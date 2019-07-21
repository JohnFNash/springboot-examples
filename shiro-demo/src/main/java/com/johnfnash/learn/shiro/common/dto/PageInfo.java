package com.johnfnash.learn.shiro.common.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.johnfnash.learn.shiro.system.vo.PageReq;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页数据封装
 */
@ApiModel("分页查询结构体")
public class PageInfo<T> {

	@ApiModelProperty("当前页码")
	private int pageNo; // 当前页码
	
	@ApiModelProperty("每页数据记录数")
	private int pageSize; // 每页数量
	
	@ApiModelProperty("总记录数")
	private long totalCount; // 总记录数
	
	@ApiModelProperty("总页数")
	private int totalPage;//总页数
	
	@ApiModelProperty("数据列表")
	private List<T> content = Collections.emptyList(); //List<T> 数据

	
	public PageInfo(int pageNo, int pageSize, long totalCount, int totalPage,
			List<T> content) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.content = content;
	}

	public PageInfo() {
		this(PageReq.DEFAULT_PAGE_NO, PageReq.DEFAULT_PAGE_SIZE);
	}

	public PageInfo(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
	
	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
		countTotalPage();
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void countTotalPage(){
		if(this.pageSize>0){
			if(totalCount%pageSize==0){
				this.setTotalPage((int)(totalCount/pageSize)); 
			}else{
				this.setTotalPage((int)(totalCount/pageSize+1)); 
			}
			
		}
	}
	
	public void reset(){
		setPageNo(PageReq.DEFAULT_PAGE_NO);
		setPageSize(PageReq.DEFAULT_PAGE_SIZE);
		setTotalPage(0);
		setTotalCount(0);
		setContent(null);
	}

	public <R> PageInfo<R> map(Function<T, R> function) {
	    List<T> notNullContent = Optional.ofNullable(getContent()).orElse(new ArrayList<>(0));
	    List<R> newContent = notNullContent.stream().map(function).collect(Collectors.toList());
	    return new PageInfo<>(pageNo, pageSize, 
	            totalCount, totalPage, newContent);
	}

}
