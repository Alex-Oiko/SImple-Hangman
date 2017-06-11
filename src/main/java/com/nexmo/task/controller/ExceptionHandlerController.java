package com.nexmo.task.controller;


import com.nexmo.task.exceptions.SystemException;
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
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ResponseEntity<String> runtimeExceptionHandler(SystemException ex) {
		logger.error(ex.getMessage());
		return ResponseEntity.ok().body(ex.getMessage());
	}

}
