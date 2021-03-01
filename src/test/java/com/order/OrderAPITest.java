package com.order;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

import com.order.controller.OrderController;
import com.order.domain.model.Orders;
import com.order.repository.OrderRepository;
import com.order.service.OrderService;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@ComponentScan(basePackages = "com.order")
@EnableAutoConfiguration
@Log4j2
@WebFluxTest(OrderController.class)
@RunWith(SpringRunner.class)
class OrderAPITest {
	@Autowired
	WebTestClient webTestClient;
	WebClient testClient = WebClient.builder().baseUrl("http://localhost:8080")
			.clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection().compress(true))).build();

	@MockBean
	OrderRepository repository;

	@MockBean
	OrderService orderService;

	@Test
	void testGetOrderById() {
		Orders order = new Orders();
		order.setCustomerId("f5b46027-1a04-423f-b87c-daba6aa1b1a7");
		order.setItemId("f5b46027-1a04-423f-b87c-daba6aa1b1a7");
		order.setOrderId("f5b46027-1a04-423f-b87c-daba6aa1b1a7");
		order.setNote("nothing");
		order.setOrderTotal(4.3);
		order.setQuantity(33);
		order.setShippingCost(22);
		order.setStatus(1);
		order.setSystemId(1);

		Mono<Orders> ordereMono = Mono.just(order);

		Mockito.when(orderService.getOrderById("f5b46027-1a04-423f-b87c-daba6aa1b1a7")).thenReturn(ordereMono);

		webTestClient.get().uri("/orders/f5b46027-1a04-423f-b87c-daba6aa1b1a7").accept(MediaType.APPLICATION_JSON)
				.exchange().expectStatus().isOk().expectBody().jsonPath("$.itemId").isNotEmpty();

	}

	@Test
	void testCreateOrder() {
		String testId = UUID.randomUUID().toString();

		Orders order = new Orders();
		order.setCustomerId(testId);
		order.setItemId(testId);
		order.setOrderId(testId);
		order.setNote("nothing");
		order.setOrderTotal(4.3);
		order.setQuantity(33);
		order.setShippingCost(22);
		order.setStatus(1);
		order.setTime(System.currentTimeMillis());

		Mono<Orders> ordereMono = Mono.just(order);
		Mockito.when(orderService.saveOrder(order)).thenReturn(ordereMono);

		webTestClient.post().uri("/orders/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).body(Mono.just(order), Orders.class).exchange().expectStatus()
				.isOk();

	}

	@Test
	void testValidateOrder() {
		String testId = UUID.randomUUID().toString();

		Orders order = new Orders();
		order.setCustomerId(testId);
		order.setItemId(testId);
		order.setOrderId(testId);
		order.setNote("nothing");
		order.setOrderTotal(4.3);
		order.setQuantity(0);
		order.setShippingCost(22);
		order.setStatus(1);

		Mono<Orders> ordereMono = Mono.just(order);
		Mockito.when(orderService.saveOrder(order)).thenReturn(ordereMono);

		webTestClient.post().uri("/orders/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).body(Mono.just(order), Orders.class).exchange().expectStatus()
				.isBadRequest();

	}

}
