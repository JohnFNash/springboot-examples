package com.johnfnash.learn.dao.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.johnfnash.learn.entity.primary.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

    @Modifying
    @Query("delete from User u where u.id = :id")
    void deleteUser(@Param("id")Long id);
	
}
