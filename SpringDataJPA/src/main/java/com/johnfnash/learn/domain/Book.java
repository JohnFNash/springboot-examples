package com.johnfnash.learn.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book implements Serializable {

	private static final long serialVersionUID = -2470510857424220408L;

	@Id
    @GeneratedValue
    private Integer id;

    private String name;
    
    public Book() {
        super();
    }

    public Book(String name) {
        super();
        this.name = name;
    }

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

	@Override
	public String toString() {
		return String.format("Book [id=%s, name=%s]", id, name);
	}

}
