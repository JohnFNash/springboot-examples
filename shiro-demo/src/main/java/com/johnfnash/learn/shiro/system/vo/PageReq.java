package com.johnfnash.learn.shiro.system.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

public class PageReq {

	public final static int DEFAULT_PAGE_NO = 1;
	public final static int DEFAULT_PAGE_SIZE = 10;

	@ApiModelProperty("页码，从1开始")
	@Getter
	@Setter
	private Integer pageNo = DEFAULT_PAGE_NO;

	@ApiModelProperty("每页显示数据条数，默认为10")
	@Getter
	@Setter
	private Integer pageSize = DEFAULT_PAGE_SIZE;

	/**
	 * 获取sql分页开始页码
	 * 
	 * @return
	 */
	@ApiModelProperty(hidden = true)
	public int getStartPage() {
		if (pageNo == null) {
			return 0;
		}
		return pageNo - 1;
	}

	/**
	 * 分页开始行
	 * 
	 * @return
	 */
	@ApiModelProperty(hidden = true)
	public int getStartIndex() {
		if (pageNo == null) {
			return 0;
		}

		return (pageNo - 1) * pageSize;
	}

	/**
	 * 分页结束行
	 * 
	 * @return
	 */
	@ApiModelProperty(hidden = true)
	public int getEndIndex() {
		if (pageNo == null) {
			return 0;
		}

		return pageNo * pageSize;
	}
}
