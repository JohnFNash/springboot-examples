package com.johnfnash.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnfnash.learn.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
