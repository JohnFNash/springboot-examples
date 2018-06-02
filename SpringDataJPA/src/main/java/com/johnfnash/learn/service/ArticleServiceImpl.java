package com.johnfnash.learn.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.johnfnash.learn.domain.ArticleInfo;
import com.johnfnash.learn.domain.ArticleRepository;

@Service("articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	
	@Override
	public Page<ArticleInfo> findAll(Pageable pageable) {
		return articleRepository.findAllByOrderByStatusDescIdDesc(pageable);
	}

}
