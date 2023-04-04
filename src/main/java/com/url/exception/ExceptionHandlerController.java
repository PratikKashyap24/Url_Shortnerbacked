package com.url.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerController {
 
	@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason = "Incorrect Input Entered")
	@ExceptionHandler(IncorrectInputException.class)
	public String getIncorrectInputException() {
		return "Incorrect Input";
	}
	
	

	@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "No Records Found")
	@ExceptionHandler(ValueNotPresentException.class)
	public String getValueNotPresentException() {
		return "No Records Found";
	}
}
