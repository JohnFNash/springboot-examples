package com.johnfnash.learn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.johnfnash.learn.domain.UserInfo;
import com.johnfnash.learn.domain.ViewInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

	@Query(value = "SELECT new com.johnfnash.learn.domain.ViewInfo(u, a) FROM UserInfo u, Address a WHERE u.addressId = a.addressId")
	public List<ViewInfo> findViewInfo();
	
}
