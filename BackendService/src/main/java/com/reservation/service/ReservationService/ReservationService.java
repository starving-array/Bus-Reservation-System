package com.reservation.service.ReservationService;

import java.time.LocalDate;
import java.util.List;

import com.reservation.model.reservation.Reservation;

public interface ReservationService {

	public Reservation addReservation(Reservation reservation) throws ResvervationException;

	public Reservation updateReservation(Reservation reservation) throws ResvervationException;

	public Reservation cancleReservation(Reservation reservation) throws ResvervationException;

	public Reservation viewReservation(int reservationId) throws ResvervationException;

	public List<Reservation> viewAllReservation() throws ResvervationException;

	public List<Reservation> getAllReservation(LocalDate date) throws ResvervationException;

	public Boolean checkAvailability(String source, String destination, LocalDate date) throws ResvervationException;

}
