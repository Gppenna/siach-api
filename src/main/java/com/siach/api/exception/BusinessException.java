package com.siach.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
public class BusinessException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = -5658630135366125615L;
	private final String devMessage;

	private final HttpStatus status;

	public BusinessException(String message) {
		super(message);
		this.devMessage = "";
		this.status = HttpStatus.BAD_REQUEST;
	}

	public BusinessException(String message, Throwable e) {
		super(message, e);
		this.devMessage = "";
		this.status = HttpStatus.BAD_REQUEST;
	}

	public BusinessException(String message, String devMessage, HttpStatus status) {
		super(message);
		this.devMessage = devMessage;
		this.status = status;
	}

	public BusinessException(String message, Throwable e, String devMessage, HttpStatus status) {
		super(message, e);
		this.devMessage = devMessage;
		this.status = status;
	}

}
