package com.reservation.service.bookingDiary;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservation.exceptions.bookingException;
import com.reservation.model.Bus.Bus;
import com.reservation.model.bookingDiary.bookingDiary;
import com.reservation.repository.bookingDiaryDao;

@Service
public class bookingDiaryServImpl implements bookingDiaryService {

	@Autowired
	private bookingDiaryDao dao;

	@Override
	public List<bookingDiary> getAllBookings() throws bookingException {
		// TODO Auto-generated method stub
		List<bookingDiary> allBookingDiaries = dao.findAll();
		if (allBookingDiaries.size() > 0) {
			return allBookingDiaries;
		} else {
			throw new bookingException("No bus avaliable");
		}
	}

	@Override
	public bookingDiary addBookingDiaryData(bookingDiary data) throws bookingException{
		// TODO Auto-generated method stub
		bookingDiary bDiary = dao.save(data);
		if (bDiary==null) {
			throw new bookingException("Data could not be added");
		}

		return bDiary;
	}

	@Override
	public Bus addBusToBookingCalender(Bus bus, LocalDateTime journeyDate, Integer routeId) throws bookingException {
		// TODO Auto-generated method stub

		return null;
	}

}
