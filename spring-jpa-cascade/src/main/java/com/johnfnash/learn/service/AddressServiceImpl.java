package com.johnfnash.learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnfnash.learn.domain.Address;
import com.johnfnash.learn.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public Address save(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public Address getOne(Long id) {
		return addressRepository.getOne(id);
	}

}
