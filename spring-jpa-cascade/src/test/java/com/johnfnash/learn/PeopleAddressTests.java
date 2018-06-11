package com.johnfnash.learn;

import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.johnfnash.learn.domain.Address;
import com.johnfnash.learn.domain.People;
import com.johnfnash.learn.repository.PeopleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleAddressTests {

	@Autowired
	private PeopleRepository peopleRepository;
	
	@Test
	public void savePeople() {
		Address address = new Address("12345678901", "54002", "xxxx road");
		People people = new People("ZS", "1", new Timestamp(System.currentTimeMillis()), address);
		peopleRepository.save(people);
		System.out.println(people.getId());
	}
	
	@Test
	public void queryPeople() {
		People people = peopleRepository.findOne(1L);		
		System.out.println(people);
	}
	
	@Test
	public void deletePeople() {
		peopleRepository.deleteById(1L);
	}
	
}
