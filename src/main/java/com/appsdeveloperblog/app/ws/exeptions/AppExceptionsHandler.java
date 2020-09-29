package com.appsdeveloperblog.app.ws.exeptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.appsdeveloperblog.app.ws.response.ErrorMessage;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handlerAnyException(Exception ex, WebRequest request){
		String errorMensagemDescription = ex.getLocalizedMessage();
		if(errorMensagemDescription==null) errorMensagemDescription = ex.toString();
		ErrorMessage errorMessage = new ErrorMessage(new Date(),errorMensagemDescription);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(value = {NullPointerException.class, UserServiceException.class})
	public ResponseEntity<Object> handlerSpecificExceptions(Exception ex, WebRequest request){
		String errorMensagemDescription = ex.getLocalizedMessage();
		if(errorMensagemDescription==null) errorMensagemDescription = ex.toString();
		ErrorMessage errorMessage = new ErrorMessage(new Date(),errorMensagemDescription);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/*
	@ExceptionHandler(value = {UserServiceException.class})
	public ResponseEntity<Object> handlerUserServiceException(UserServiceException ex, WebRequest request){
		String errorMensagemDescription = ex.getLocalizedMessage();
		if(errorMensagemDescription==null) errorMensagemDescription = ex.toString();
		ErrorMessage errorMessage = new ErrorMessage(new Date(),errorMensagemDescription);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	*/
	
}
