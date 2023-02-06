package com.petritkrasniqi.weatherapi.mapper;

import static com.petritkrasniqi.weatherapi.util.LoggerUtil.logError;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.petritkrasniqi.weatherapi.exception.BadRequestException;

@ControllerAdvice
public class BadRequestExceptionHandler  {
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
		logError("Bad Request. Returning 400 status. " + ex.getMessage());
		
		return ResponseEntity.status(BAD_REQUEST).body(ex.getMessage());
	}

}
