package com.johnfnash.learn.dao.secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.johnfnash.learn.entity.secondary.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	Order findByNumber(String number);

    @Modifying
    @Query("delete from Order o where o.id = :id")
    void deleteOrder(@Param("id")long id);
	
}
