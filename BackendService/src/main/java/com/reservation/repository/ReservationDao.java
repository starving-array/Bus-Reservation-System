package com.reservation.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reservation.model.reservation.Reservation;

@Repository
public interface ReservationDao extends JpaRepository<Reservation, Integer>{

	
	public List<Reservation> findByReservationDate(LocalDate date);
	
}
