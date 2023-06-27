package com.shop.orderservice.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shop.orderservice.dto.error.ErrorDetails;
import com.shop.orderservice.dto.error.ValidationError;
import com.shop.orderservice.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) 
	{
		ErrorDetails details = new ErrorDetails();
		details.setTimestamp(new Date().getTime());
		details.setTitle("Message Not Readable");
		details.setStatus(status.value());
		details.setDeveloperMsg(ex.getClass().getName());
		details.setMessage(ex.getMessage());
		return handleExceptionInternal(ex, details, headers, status, request);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<?> sqlIntegrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException ex)
	{
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setTitle("Constraints violated.");
		errorDetails.setDeveloperMsg(ex.getClass().getName());
		errorDetails.setMessage(ex.getMessage());
		errorDetails.setTimestamp(new Date().getTime());
		errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundExceptionHandler(ResourceNotFoundException exception)
	{
		ErrorDetails details = new ErrorDetails();
		details.setTitle("Resource not found.");
		details.setDeveloperMsg(exception.getClass().getName());
		details.setStatus(HttpStatus.NOT_FOUND.value());
		details.setTimestamp(new Date().getTime());
		details.setMessage(exception.getMessage());
		
		return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) 
	{
		ErrorDetails details = new ErrorDetails();
		details.setDeveloperMsg(exception.getClass().getName());
		details.setStatus(HttpStatus.BAD_REQUEST.value());
		details.setTitle("Field validation error.");
		details.setMessage("Input validation error");
		details.setTimestamp(new Date().getTime());
		List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
		for(ObjectError error : allErrors)
		{
			List<ValidationError> list = details.getErrors().get(error.getCode());
			
			if(list == null)
			{
				list = new ArrayList<>();
				details.getErrors().put(error.getCode(), list);
			}
			
			ValidationError e = new ValidationError();
			e.setCode(error.getCode());
			e.setMessage(error.getDefaultMessage());
			
			list.add(e);
		}
		
		
		return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
	}

}
