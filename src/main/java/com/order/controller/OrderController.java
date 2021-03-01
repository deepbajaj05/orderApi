package com.order.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.config.EnableWebFlux;

import com.order.domain.model.Orders;
import com.order.excption.RecordNotFoundException;
import com.order.service.OrderService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
@EnableWebFlux
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/{orderId}")
	public Mono<Orders> getOrderById(@Valid @PathVariable String orderId) {

		return orderService.getOrderById(orderId)
				.switchIfEmpty(Mono.defer(() -> Mono.error(new RecordNotFoundException("Record not exist"))));

	}

	@PostMapping("")
	public ResponseEntity<String> saveOrder(@Valid @RequestBody List<Orders> order) {
		orderService.save(order);
		return new ResponseEntity<>("OK", HttpStatus.CREATED);

	}

	@PostMapping("/create")
	public Mono<Orders> saveOrder(@Valid @RequestBody Orders order) {
		return orderService.saveOrder(order);

	}

}
