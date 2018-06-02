package com.johnfnash.learn.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "article_category")
public class ArticleCategory implements Serializable {

	private static final long serialVersionUID = -5792339556223731978L;

	@Id
	@ManyToOne
	@JoinColumn(name = "article_id")
	private Article article;

    @Id
    @ManyToOne
    @JoinColumn(name = "category_id")
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
