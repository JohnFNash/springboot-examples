package com.johnfnash.learn.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Author implements Serializable {

	private static final long serialVersionUID = 1227555837798655046L;

	@Id
    @GeneratedValue
    private Integer id;

    private String name;

	public Author() {
		super();
	}
    
	public Author(String name) {
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
        return String.format("Author [id=%s, name=%s]", id, name);
    }
	
}
