package com.johnfnash.learn.service;

import com.johnfnash.learn.domain.Address;

public interface AddressService {

	public Address save(Address address);
	
	public Address getOne(Long id);
	
}
