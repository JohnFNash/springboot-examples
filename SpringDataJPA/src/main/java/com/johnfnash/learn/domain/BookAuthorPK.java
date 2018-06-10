package com.johnfnash.learn.domain;

import java.io.Serializable;

public class BookAuthorPK implements Serializable {

	private static final long serialVersionUID = -1158141803682305656L;

	private Integer bookId;
	
	private Integer authorId;

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
