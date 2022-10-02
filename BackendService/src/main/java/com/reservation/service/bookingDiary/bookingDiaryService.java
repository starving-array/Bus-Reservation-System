package com.reservation.service.bookingDiary;

import java.time.LocalDateTime;
import java.util.List;

import com.reservation.exceptions.bookingException;
import com.reservation.model.Bus.Bus;
import com.reservation.model.bookingDiary.bookingDiary;


public interface bookingDiaryService {

	
	public List<bookingDiary> getAllBookings() throws bookingException;	
	
	public bookingDiary addBookingDiaryData(bookingDiary data) throws bookingException;
	
	public Bus addBusToBookingCalender(Bus bus, LocalDateTime journeyDate, Integer routeId) throws bookingException;	
	
}
