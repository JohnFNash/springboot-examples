package com.johnfnash.learn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.johnfnash.learn.domain.DTSResult;

@Repository
public interface DTSResultRepository extends JpaRepository<DTSResult, Long> {

}
