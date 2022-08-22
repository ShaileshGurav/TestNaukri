package com.db.trading.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.db.trading.exception.TradeExceptions;

@ControllerAdvice
public class TradeExceptionController {

	@ExceptionHandler(value = TradeExceptions.class )
	public ResponseEntity<Object> exception(TradeExceptions exception) {
	      return new ResponseEntity<>("Invalid Trade "+exception.getMessage(), HttpStatus.EXPECTATION_FAILED);
	   }
}
