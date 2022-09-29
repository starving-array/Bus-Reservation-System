package com.reservation.service.ReservationService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservation.exceptions.ResvervationException;
import com.reservation.model.reservation.Reservation;
import com.reservation.repository.ReservationDao;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationDao dao;

	// ********************************************************************

	@Override
	public Reservation addReservation(Reservation reservation) throws ResvervationException {

		if (reservation == null) {
			throw new ResvervationException("Please enter proper details..!!");
		}
		Reservation addedReservation = dao.save(reservation);

		return addedReservation;
	}

	// ********************************************************************

	@Override
	public Reservation updateReservation(Reservation reservation) throws ResvervationException {

		Optional<Reservation> optional = dao.findById(reservation.getReservationId());

		if (optional.isPresent())
			return dao.save(reservation);

		throw new ResvervationException("Please enter proper details..!!");

	}

	// ********************************************************************

	@Override
	public Reservation cancleReservation(Reservation reservation) throws ResvervationException {
		// Reservation state from active to inactive, modification needed after
		// mapping..!!
		return null;
	}

	// ********************************************************************

	@Override
	public Reservation viewReservation(int reservationId) throws ResvervationException {

		Optional<Reservation> optional = dao.findById(reservationId);

		if (optional.isPresent()) {
			return optional.get();
		}
		throw new ResvervationException("No resvercations found with ID " + reservationId + " ..!!");
	}

	// ********************************************************************

	@Override
	public List<Reservation> viewAllReservation() throws ResvervationException {

		List<Reservation> reservations = dao.findAll();

		if (reservations.isEmpty())
			throw new ResvervationException("No resvervations found..!!");

		return reservations;
	}

	// ********************************************************************

	@Override
	public List<Reservation> getAllReservation(LocalDate date) throws ResvervationException {

		List<Reservation> reservations = dao.findByReservationDate(date);

		if (reservations.isEmpty())
			throw new ResvervationException("No resvercations found on date " + date + " ..!!");

		return reservations;

// ReservationDao: add @Repository, extend JpaRepo		
//		public List<Reservation> findByReservationDate(LocalDate date); to be added in ReservationDao!!
	}

	// ********************************************************************

	@Override
	public Boolean checkAvailability(String source, String destination, LocalDate date) throws ResvervationException {
		// TODO Auto-generated method stub

		if (date.isBefore(LocalDate.now())) {
			return true;
		}
		// modification needed after mapping..!!
		throw new ResvervationException("Past dates not allowed. Please enter upcoming future dates..!!");

	}

	// ********************************************************************

}
