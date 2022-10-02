package com.reservation.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.exceptions.ResvervationException;
import com.reservation.exceptions.UserException;
import com.reservation.model.bookingDiary.bookingDiary;
import com.reservation.model.reservation.Reservation;
import com.reservation.service.ReservationService.ReservationService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/reservation")
public class ReservationServiceController {

	@Autowired
	private ReservationService rDao;

	@PostMapping("/reservation/{diaryId}/{seat}")
	public ResponseEntity<Reservation> bookReservation(@PathVariable("diaryId") Integer data,
			@PathVariable("seat") Integer noofSeat) throws UserException, ResvervationException {
		Reservation bookingData = rDao.addReservation(data, noofSeat);
		return new ResponseEntity<Reservation>(bookingData, HttpStatus.OK);
	}

	@PutMapping("/cancel/{id}")
	public ResponseEntity<Reservation> cancelReservation(@PathVariable("id") Integer reservation)
			throws ResvervationException {
		Reservation r = rDao.cancleReservation(reservation);
		return new ResponseEntity<Reservation>(r, HttpStatus.OK);
	}

	@GetMapping("/view/{id}")
	public ResponseEntity<Reservation> viewReservation(@PathVariable("id") Integer id) throws ResvervationException {
		Reservation reservation = rDao.viewReservation(id);
		return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
	}

	@GetMapping("/view")
	public ResponseEntity<List<Reservation>> viewAllReservation() throws ResvervationException {
		List<Reservation> reservation = rDao.viewAllReservation();
		return new ResponseEntity<List<Reservation>>(reservation, HttpStatus.OK);
	}

	@GetMapping("/viewByDate/{date}")
	public ResponseEntity<List<Reservation>> viewAllReservationByDate(
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) throws ResvervationException {
		List<Reservation> reservation = rDao.getAllReservation(date);
		return new ResponseEntity<List<Reservation>>(reservation, HttpStatus.OK);
	}

}
