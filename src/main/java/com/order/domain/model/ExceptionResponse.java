package com.order.domain.model;

import java.time.LocalDateTime;

public class ExceptionResponse {
	private LocalDateTime timestamp;
	private String message;
	private String details;
	private String path;
	private String status;

	public ExceptionResponse(LocalDateTime timestamp, String message, String details, String path, String status) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.status = status;
		this.path = path;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
