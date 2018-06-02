package com.johnfnash.learn.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	//自定义简单查询
	User findByUserName(String userName);
	
	User findByUserNameOrEmail(String userName, String email);
	
	Long countByUserName(String userName);
	
	List<User> findByEmailLike(String userName);
	
	List<User> findByUserNameOrderByEmailDesc(String email);
	
	List<User> findByUserNameStartingWith(String email);
	
	// 分页查询及限制查询
	Page<User> findByUserName(String userName, Pageable pageable);
	
	User findTopByOrderByUserNameDesc();

	List<User> findFirst10ByUserName(String userName, Sort sort);

	// 自定义SQL查询
	@Modifying
	@Query("update User u set u.userName = ?1 where u.id = ?2")
	int modifyByIdAndUserId(String userName, Long id);
	
	//@Transactional //@Transactional必须定义在service层,而且必须加, JPA 强制要求update/delete加事物
	@Modifying
	@Query("delete from User where id = ?1")
	void deleteByUserId(Long id);
	
}
