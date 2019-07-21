package com.johnfnash.learn.shiro.common.utils;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.johnfnash.learn.shiro.common.dto.PageInfo;

public class PageInfoUtils {

	public static <T, R> PageInfo<R> toPageInfo(Page<T> page, List<R> content) {
		PageInfo<R> pageInfo = new PageInfo<>();
		pageInfo.setPageNo((int) page.getCurrent());
		pageInfo.setTotalCount(page.getTotal());
		pageInfo.setTotalPage((int) page.getPages());
		pageInfo.setPageSize((int)page.getSize());
		pageInfo.setContent(content);
		return pageInfo;
	}

	public static <T> PageInfo<T> toPageInfo(PageInfo<T> pageInfo, Integer pageNo, Integer pageSize, Integer count) {
		pageInfo.setPageNo(pageNo);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotalCount(count);
		if (pageInfo.getPageSize() > 0) {
			if (pageInfo.getTotalCount() % (long) pageInfo.getPageSize() == 0L) {
				pageInfo.setTotalPage((int) (pageInfo.getTotalCount() / (long) pageInfo.getPageSize()));
			} else {
				pageInfo.setTotalPage((int) (pageInfo.getTotalCount() / (long) pageInfo.getPageSize() + 1L));
			}
		}
		return pageInfo;
	}

	/**
	 * 获取总页数
	 * @param totalCount
	 * @param pageSize
	 * @return
	 */
	public static long getTotalPage(long totalCount, int pageSize) {
		return totalCount % pageSize == 0 ? (totalCount / pageSize) : (totalCount / pageSize + 1);
	}
	
}
