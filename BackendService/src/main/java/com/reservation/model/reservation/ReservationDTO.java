package com.reservation.model.reservation;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.reservation.model.Feedback;
import com.reservation.model.Route;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

//*************************************************************


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ReservationDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int reservationId;
	
	private LocalDate reservationDate;
	private LocalTime reservationTime;
	private String source;
	private String destination;
	private Route route;
	
}
