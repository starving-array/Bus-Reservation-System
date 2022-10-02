package com.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.exceptions.bookingException;
import com.reservation.model.bookingDiary.bookingDiary;
import com.reservation.service.bookingDiary.bookingDiaryService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/diaryDatabase")
public class bookDiaryController {

	@Autowired
	 private bookingDiaryService bService;
	
	@GetMapping("/")
	public ResponseEntity<List<bookingDiary>> getAllBooking() throws bookingException{
		List<bookingDiary> bookingsBookingDiaries = bService.getAllBookings();
		return new ResponseEntity<List<bookingDiary>>(bookingsBookingDiaries, HttpStatus.OK);
	}
	@PostMapping("/add")
	public ResponseEntity<bookingDiary> addBookingDiaryData(@RequestBody bookingDiary data) throws bookingException {
		bookingDiary dataBookingDiary = bService.addBookingDiaryData(data);
		return new ResponseEntity<bookingDiary>(dataBookingDiary, HttpStatus.OK);
	}
	
	
}
