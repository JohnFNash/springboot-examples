package com.johnfnash.learn.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(BookAuthorPK.class)
@Table(name = "book_author")
public class BookAuthor {
	
	@Id
	private Integer bookId;
	
	@Id
	private Integer authorId;
	
	public BookAuthor() {
		super();
	}

	public BookAuthor(Integer bookId, Integer authorId) {
		super();
		this.bookId = bookId;
		this.authorId = authorId;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	
}
