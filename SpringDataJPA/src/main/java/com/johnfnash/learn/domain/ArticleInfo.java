package com.johnfnash.learn.domain;

import java.io.Serializable;

public class ArticleInfo implements Serializable {

	private static final long serialVersionUID = 5470958483722827107L;

	private Article article;
	
	private Category category;

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
