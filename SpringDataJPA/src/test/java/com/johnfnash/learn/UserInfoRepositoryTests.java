package com.johnfnash.learn;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.johnfnash.learn.domain.Address;
import com.johnfnash.learn.domain.UserInfo;
import com.johnfnash.learn.domain.ViewInfo;
import com.johnfnash.learn.repository.AddressRepository;
import com.johnfnash.learn.repository.UserInfoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoRepositoryTests {

	@Autowired
    private UserInfoRepository userInfoRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Before
    public void init() {
        Address addr1 = new Address("027","CN","HuBei", "WuHan","WuChang", "123 street");
        Address addr2 = new Address("023","CN","ChongQing", "ChongQing","YuBei", "123 road");
        addressRepository.save(addr1);
        addressRepository.save(addr2);
        
        UserInfo user1 = new UserInfo("ZS", 21,"Male","123@xx.com", addr1.getAddressId());
        UserInfo user2 = new UserInfo("Ww", 25,"Male","234@xx.com", addr2.getAddressId());
        userInfoRepository.save(user1);
        userInfoRepository.save(user2);
    }
	
	@After
	public void deleteAll() {
		userInfoRepository.deleteAll();
		
		addressRepository.deleteAll();
	}
	
	@Test
	public void testQuery() {
		List<ViewInfo> viewInfos = userInfoRepository.findViewInfo();
		for (ViewInfo viewInfo : viewInfos) {
			System.out.println(viewInfo.getUserInfo());
			System.out.println(viewInfo.getAddress());
		}
	}
	
}
