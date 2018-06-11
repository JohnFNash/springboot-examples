package com.johnfnash.learn.domain;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Article {

	@Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id
	
	@Column(nullable = false, length = 50)
	private String title;
	
	@Lob	 // 大对象，映射 MySQL 的 Long Text 类型
	@Basic(fetch = FetchType.LAZY)
	@Column(nullable = false)  // 懒加载
	private String content;
	
	//可选属性optional=false,表示author不能为空。删除文章，不影响用户
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
	@JoinColumn(name = "author_id") //设置在article表中的关联字段(外键)
	private Author author;

	public Article() {
		super();
	}
	
	public Article(String title, String content, Author author) {
		super();
		this.title = title;
		this.content = content;
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
	
}
