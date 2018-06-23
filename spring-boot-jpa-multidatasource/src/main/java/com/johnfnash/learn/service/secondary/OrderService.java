package com.johnfnash.learn.service.secondary;

import com.johnfnash.learn.entity.secondary.Order;

public interface OrderService {

	public Order findByNumber(String number);
	
    public void deleteOrder(Long id);
	
}
