package com.reservation.service.ReservationService;

import java.time.LocalDate;
import java.util.List;

import com.reservation.exceptions.ResvervationException;
import com.reservation.model.reservation.Reservation;

public interface ReservationService {

	public Reservation addReservation(Integer diaryLog, Integer noOfSeats) throws ResvervationException;

	public Reservation updateReservation(Reservation reservation) throws ResvervationException;

	public Reservation cancleReservation(Integer reservationId) throws ResvervationException;

	public Reservation viewReservation(Integer reservationId) throws ResvervationException;

	public List<Reservation> viewAllReservation() throws ResvervationException;

	public List<Reservation> getAllReservation(LocalDate date) throws ResvervationException;


//	public List<bookingDiary> checkAvaliability(String source, String destination,
//			LocalDateTime journDateTime) throws UserException, RouteException, BusException, bookingException, ResvervationException;

}
