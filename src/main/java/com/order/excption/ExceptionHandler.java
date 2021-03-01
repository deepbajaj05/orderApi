package com.order.excption;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.domain.model.ExceptionResponse;

import reactor.core.publisher.Mono;

@Configuration
@Order(-2)
@RestControllerAdvice
public class ExceptionHandler implements ErrorWebExceptionHandler {

	private ObjectMapper objectMapper;

	public ExceptionHandler(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {

		DataBufferFactory bufferFactory = serverWebExchange.getResponse().bufferFactory();
		serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
		if (throwable instanceof IOException || throwable instanceof WebExchangeBindException) {
			serverWebExchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
			DataBuffer dataBuffer = null;
			try {
				dataBuffer = bufferFactory
						.wrap(objectMapper.writeValueAsBytes(new ExceptionResponse(LocalDateTime.now(), "Bad Request",
								throwable.getLocalizedMessage(), "", HttpStatus.BAD_REQUEST.toString())));
			} catch (JsonProcessingException e) {
				dataBuffer = bufferFactory.wrap("".getBytes());
			}
			return serverWebExchange.getResponse().writeWith(Mono.just(dataBuffer));
		}

		serverWebExchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
		serverWebExchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);

		DataBuffer dataBuffer;
		try {
			dataBuffer = bufferFactory.wrap(
					objectMapper.writeValueAsBytes(new ExceptionResponse(LocalDateTime.now(), "Internal Server Error",
							throwable.getLocalizedMessage(), "", HttpStatus.INTERNAL_SERVER_ERROR.toString())));
		} catch (JsonProcessingException e) {
			dataBuffer = bufferFactory.wrap("".getBytes());
		}

		return serverWebExchange.getResponse().writeWith(Mono.just(dataBuffer));
	}

}