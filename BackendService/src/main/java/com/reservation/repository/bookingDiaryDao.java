package com.reservation.repository;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.reservation.model.bookingDiary.bookingDiary;

@Repository
public interface bookingDiaryDao extends JpaRepository<bookingDiary, Integer> {

	@Query("select b from bookingDiary b where b.travel_route_id=?1 and b.journeyDate_bookingDiary=?2")
	public List<bookingDiary> getBookingByRouteIdJourneyDate(Integer routeId, LocalDate journeyDate);
}
	