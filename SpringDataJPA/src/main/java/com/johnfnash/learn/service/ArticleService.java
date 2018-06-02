package com.johnfnash.learn.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.johnfnash.learn.domain.ArticleInfo;

public interface ArticleService {

	//获得文章列表
    Page<ArticleInfo> findAll(Pageable pageable);
	
}
