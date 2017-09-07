package com.nexmo.task.controller;


import com.nexmo.task.exceptions.SystemException;
import com.nexmo.task.response.StringResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
@ResponseBody
public class ExceptionHandlerController {
	private static final transient Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);


	@ExceptionHandler(SystemException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<StringResponse> runtimeExceptionHandler(SystemException ex) {
		logger.error(ex.getMessage());
		return ResponseEntity.badRequest().body(new StringResponse(ex.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<StringResponse> runtimeExceptionHandler(Exception ex) {
		logger.error(ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StringResponse(ex.getMessage()));
	}



}
