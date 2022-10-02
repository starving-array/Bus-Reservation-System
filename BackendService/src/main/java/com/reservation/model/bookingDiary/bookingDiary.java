package com.reservation.model.bookingDiary;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reservation.model.reservation.Reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class bookingDiary {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingDiaryId;
	private LocalDate journeyDate_bookingDiary;

	private Integer bus_id;

	private Integer seatBooked; // for the current day
	private Integer seatAvaliable;

	private Integer travel_route_id;

	@JsonIgnore	
	@OneToMany
	private List<Reservation> listOfUserTravellingList = new ArrayList<>();
}
