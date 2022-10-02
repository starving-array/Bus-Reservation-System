package com.reservation.service.ReservationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservation.exceptions.ResvervationException;
import com.reservation.model.Bus.Bus;
import com.reservation.model.Route.Route;
import com.reservation.model.User.User;
import com.reservation.model.bookingDiary.bookingDiary;
import com.reservation.model.reservation.Reservation;
import com.reservation.repository.BusDao;
import com.reservation.repository.ReservationDao;
import com.reservation.repository.RouteDao;
import com.reservation.repository.bookingDiaryDao;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationDao dao;

	@Autowired
	private RouteDao routedao;

	@Autowired
	private BusDao bDao;

	@Autowired
	private bookingDiaryDao diaryDao;
	


	// ********************************************************************

	@Override
	public Reservation addReservation(bookingDiary diaryLog, Integer noOfSeaats) throws ResvervationException {
		if (diaryLog == null) {
			throw new ResvervationException("Please select one bus to book");
		}
		if (diaryLog.getSeatAvaliable() < noOfSeaats) {
			throw new ResvervationException("This bus has less number of seats than you want. Please look other buses");
		}

		Reservation newReservation = new Reservation();
		newReservation.setReservationStatus("booked");
		newReservation.setReservationDate(LocalDateTime.now().toLocalDate());
		newReservation.setReservationTime(LocalDateTime.now().toLocalTime());
		newReservation.setJourneyDate(diaryLog.getJourneyDate_bookingDiary().toLocalDate());

		// get route detail with route id
		Optional<Route> opt = routedao.findById(diaryLog.getTravel_route_id());
		Route route = opt.get();
		newReservation.setSource(route.getRouteFrom());
		newReservation.setDestination(route.getRouteTo());

		// get bus detail with bus id++++++++++++++++++++++++++++++++++++++++++++++++
		Optional<Bus> opt2 = bDao.findById(diaryLog.getBus_id());
		Bus bus = opt2.get();

		Double price = (double) (noOfSeaats * bus.getPrice());
		Double taxPrice = price * 1.08;

		newReservation.setPriceWithoutTax(price);
		newReservation.setTax("18%");
		newReservation.setPriceWithTax(taxPrice);

		// feedback will be added directly from feedback after journey complete
//		Feedback feedback = new Feedback();
//		feedback.setFeedback_reservation(newReservation); 
//		feedback.setFeedback_bus(bus);
//		newReservation.setReservation_feedback(feedback);
//		bus.getBus_feedbacks().add(feedback);
		// user will be added from active login module ++++++++++++++++++++++
//		for now default user

		// seat numbers+++++++++++++++++++++++++++++++++++++++++++++
//		+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		newReservation.setNoOfSeats(noOfSeaats);
		Optional<bookingDiary> optDiary = diaryDao.findById(diaryLog.getBookingDiaryId());
		bookingDiary diaryToAdd = optDiary.get(); // this is current log for that bus in log database
		// temporary seat allign== sonnar selection will be avaliable
		Integer lastSeat = bus.getSeats() - diaryToAdd.getSeatAvaliable() + 1;

		diaryToAdd.setSeatAvaliable(diaryToAdd.getSeatAvaliable() - noOfSeaats);
		diaryToAdd.setSeatBooked(diaryLog.getSeatBooked() + noOfSeaats);

//		for (int i = 0; i < noOfSeaats; i++) {
//			newReservation.getSeatNumbers().add("A" + lastSeat + i);
//		}

		// user id need to be attach to the reservation
		// from login current user module
		User user = new User(); // dummy user
		newReservation.setReservation_User(user);
		user.getUser_reservation().add(newReservation);

		// bus
		// a feedback added to reservation+++++++++++++++++++++++++++++++

		bus.getBus_reservation().add(newReservation);

		return dao.save(newReservation);
	}

	// ********************************************************************

	@Override
	public Reservation updateReservation(Reservation reservation) throws ResvervationException {

		
		// reschedule behaviour
		Optional<Reservation> optional = dao.findById(reservation.getReservationId());

		if (optional.isPresent())
			return dao.save(reservation);

		throw new ResvervationException("Please enter proper details..!!");

	}

	// ********************************************************************

	@Override
	public Reservation cancleReservation(Reservation reservation) throws ResvervationException {
		// Reservation state from active to inactive, modification needed after
		Optional<Reservation>  opt= dao.findById(reservation.getReservationId());
		if(opt.isPresent()) {
			Reservation reservationExist = opt.get();
			String resStatu = reservationExist.getReservationStatus();
			if(resStatu.equalsIgnoreCase("cancel")) {
				throw new  ResvervationException("The reservation already cancelled");
			}else if(resStatu.equalsIgnoreCase("complete")) {
				throw new ResvervationException("The reservation can't be cancel as it's already comppleted.");
			}else {
				int totay = LocalDate.now().getDayOfYear();
				if(totay > reservationExist.getJourneyDate().getDayOfYear()) {
					reservationExist.setReservationStatus("complete");
				}else {
					reservationExist.setReservationStatus("complete");
					Double costDouble = reservationExist.getPriceWithTax();
					Double refunDouble = costDouble - (costDouble*20/100);
					User userOptional = reservationExist.getReservation_User();
					if(userOptional==null) {
						throw new ResvervationException("No user found to send the refund");
					}else {
//						userOptional.getUser_wallet().setBalance(userOptional.getUser_wallet().getBalance()+refunDouble);
						userOptional.setWallet(userOptional.getWallet()+refunDouble);
						reservationExist.setRefund(refunDouble);
					}
				}
			}
			return  reservationExist;
		}else {
			throw new ResvervationException("No reservation exists");
		}
		// mapping..!!
	}

	// ********************************************************************

	@Override
	public Reservation viewReservation(Integer reservationId) throws ResvervationException {

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

	

	// ********************************************************************

}
