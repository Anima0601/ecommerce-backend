
	package com.cg.exception;

	import org.springframework.http.*;

	
	import org.springframework.web.bind.MethodArgumentNotValidException;
	import java.util.stream.Collectors;
	import org.springframework.web.bind.annotation.*;

import com.cg.dto.ErrorMessageDto;

	@RestControllerAdvice
	public class GlobalExceptionHandler {
		

	    // 🔴 Not Found
	    @ExceptionHandler(NotFoundException.class)
	    public ResponseEntity<ErrorMessageDto> handleNotFound(NotFoundException ex) {
	        return new ResponseEntity<>(
	                new ErrorMessageDto(ex.getMessage(), HttpStatus.NOT_FOUND.value()),
	                HttpStatus.NOT_FOUND
	        );
	    }

	    @ExceptionHandler(AlreadyExistsException.class)
	    public ResponseEntity<ErrorMessageDto> handleAlreadyExists(AlreadyExistsException ex) {
	        return new ResponseEntity<>(
	                new ErrorMessageDto(ex.getMessage(), HttpStatus.CONFLICT.value()),
	                HttpStatus.CONFLICT
	        );
	    }

	    // 🟠 Bad Request
	    @ExceptionHandler(BadRequestException.class)
	    public ResponseEntity<ErrorMessageDto> handleBadRequest(BadRequestException ex) {
	        return new ResponseEntity<>(
	                new  ErrorMessageDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()),
	                HttpStatus.BAD_REQUEST
	        );
	    }

	    // ⚫ Generic Exception
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorMessageDto> handleGeneric(Exception ex) {
	        return new ResponseEntity<>(
	                new  ErrorMessageDto(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()),
	                HttpStatus.INTERNAL_SERVER_ERROR
	        );
	    }
	    
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<ErrorMessageDto> handleValidation(MethodArgumentNotValidException ex) {

	        String errors = ex.getBindingResult()
	                .getFieldErrors()
	                .stream()
	                .map(err -> err.getDefaultMessage())
	                .collect(Collectors.joining(", "));

	        return new ResponseEntity<>(
	                new ErrorMessageDto(errors, HttpStatus.BAD_REQUEST.value()),
	                HttpStatus.BAD_REQUEST
	        );
	    }
	}
