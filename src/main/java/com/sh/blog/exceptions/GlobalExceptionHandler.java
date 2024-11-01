package com.sh.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sh.blog.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundHandler(ResourceNotFoundException ex)
	{
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message , false);
		return new ResponseEntity<ApiResponse>(apiResponse , HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException mv)
	{
		Map<String, String> errors = new HashMap<>();
		mv.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError)error).getField();
			String fieldMessage = error.getDefaultMessage();
//			
			errors.put(fieldName , fieldMessage);
//			errors.put(error.getCode(), error.getDefaultMessage());
		});
		return new ResponseEntity<Map<String,String>>(errors, HttpStatus.BAD_REQUEST);
	}
}
