package com.johnfnash.learn.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false, length = 20)
	private String name;

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	// /级联保存、更新、删除、刷新;延迟加载。当删除用户，会级联删除该用户的所有文章
	// 拥有mappedBy注解的实体类为关系被维护端
    //mappedBy="author"中的author是Article中的author属性
	private List<Article> articleList;
	
	public Author() {
		super();
	}

	public Author(String name, List<Article> articleList) {
		super();
		this.name = name;
		this.articleList = articleList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}

}
