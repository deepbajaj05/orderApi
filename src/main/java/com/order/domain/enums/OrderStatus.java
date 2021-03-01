package com.order.domain.enums;

public enum OrderStatus {

	UNFULFILLED(0), FULFILLED(1), PENDING(2);

	public final int value;

	private OrderStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
}
