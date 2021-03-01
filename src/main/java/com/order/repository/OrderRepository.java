package com.order.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.order.domain.model.Orders;

import reactor.core.publisher.Mono;


public interface OrderRepository extends ReactiveCrudRepository<Orders, String>{

	Mono<Orders> findByOrderId(String id);

}
