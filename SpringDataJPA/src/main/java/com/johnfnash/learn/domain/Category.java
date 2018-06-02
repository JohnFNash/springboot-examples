package com.johnfnash.learn.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category implements Serializable {

	private static final long serialVersionUID = 8470082802473481128L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    //private String keys;
    private Integer status;
    
    @OneToMany(mappedBy = "category")
    private Set<ArticleCategory> articleCategoryList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public String getKeys() {
//		return keys;
//	}
//
//	public void setKeys(String keys) {
//		this.keys = keys;
//	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set<ArticleCategory> getArticleCategoryList() {
		return articleCategoryList;
	}

	public void setArticleCategoryList(Set<ArticleCategory> articleCategoryList) {
		this.articleCategoryList = articleCategoryList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
