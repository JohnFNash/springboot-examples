package com.johnfnash.learn.service.secondary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.johnfnash.learn.dao.secondary.OrderRepository;
import com.johnfnash.learn.entity.secondary.Order;

@Service
@Transactional("transactionManagerSecondary")
public class OrderServiceImpl implements OrderService {

	@Autowired
    private OrderRepository orderRepository;
	
	@Override
	public Order findByNumber(String number) {
		return orderRepository.findByNumber(number);
	}

	@Override
	public void deleteOrder(Long id) {
		orderRepository.deleteOrder(id);
	}

}
