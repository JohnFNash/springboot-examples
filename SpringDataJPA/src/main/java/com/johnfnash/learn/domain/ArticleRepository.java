package com.johnfnash.learn.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

	//获取文章列表，按status和id降序
	@Query("select a.title as title, a.content as content, c.name as name"
			+ " from Article a left outer join a.articleCategoryList ac left join ac.category c")
	Page<ArticleInfo> findAllByOrderByStatusDescIdDesc(Pageable pageable);
	
}
