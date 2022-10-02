package com.reservation.exceptions.global;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.reservation.exceptions.BusException;
import com.reservation.exceptions.FeedBackException;
import com.reservation.exceptions.ResvervationException;
import com.reservation.exceptions.RouteException;
import com.reservation.exceptions.UserException;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorFormatDetails> NoExceptionHandler(NoHandlerFoundException se, WebRequest req){
		
		
		ErrorFormatDetails err= new ErrorFormatDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(se.getMessage());
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<ErrorFormatDetails>(err, HttpStatus.NOT_FOUND);
		
	}
	
	
	@ExceptionHandler(BusException.class)
	public ResponseEntity<ErrorFormatDetails> busExceptionHandler(BusException se, WebRequest req){
		
		
		ErrorFormatDetails err= new ErrorFormatDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(se.getMessage());
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<ErrorFormatDetails>(err, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(FeedBackException.class)
	public ResponseEntity<ErrorFormatDetails> feedbackExceptionHandler(FeedBackException se, WebRequest req){
		
		ErrorFormatDetails err= new ErrorFormatDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(se.getMessage());
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<ErrorFormatDetails>(err, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(ResvervationException.class)
	public ResponseEntity<ErrorFormatDetails> reservationExceptionHandler(ResvervationException se, WebRequest req){
		
		
		ErrorFormatDetails err= new ErrorFormatDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(se.getMessage());
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<ErrorFormatDetails>(err, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(RouteException.class)
	public ResponseEntity<ErrorFormatDetails> routeExceptionHandler(RouteException se, WebRequest req){
		
		
		ErrorFormatDetails err= new ErrorFormatDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(se.getMessage());
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<ErrorFormatDetails>(err, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorFormatDetails> userExceptionHandler(UserException se, WebRequest req){
		
		
		ErrorFormatDetails err= new ErrorFormatDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(se.getMessage());
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<ErrorFormatDetails>(err, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorFormatDetails> otherExceptionHandler(Exception se, WebRequest req){
		
		
		ErrorFormatDetails err= new ErrorFormatDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(se.getMessage());
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<ErrorFormatDetails>(err, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	
}
