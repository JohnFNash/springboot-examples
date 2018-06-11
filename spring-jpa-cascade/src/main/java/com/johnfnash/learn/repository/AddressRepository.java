package com.johnfnash.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnfnash.learn.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
