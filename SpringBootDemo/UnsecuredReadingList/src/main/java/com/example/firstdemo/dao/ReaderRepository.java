package com.example.firstdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.firstdemo.entity.Reader;

public interface ReaderRepository extends JpaRepository<Reader, String> {

}
