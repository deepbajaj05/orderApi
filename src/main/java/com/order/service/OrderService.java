package com.order.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.domain.model.Orders;
import com.order.repository.OrderRepository;

import reactor.core.publisher.Mono;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public void save(@Valid List<Orders> order) {

		orderRepository.saveAll(order).subscribe();

	}

	public Mono<Orders> getOrderById(String id) {
		return orderRepository.findByOrderId(id);
	}

	public Mono<Orders> saveOrder(Orders order) {
		return orderRepository.save(order);

	}

}
