package com.reservation.model.reservation;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.annotation.Generated;
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
import net.bytebuddy.asm.Advice.Local;

//*************************************************************

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int reservationId;
	
	private String reservationStatus;
	private String reservationType;
	private LocalDate reservationDate;
	private LocalTime reservationTime;
	private String source;
	private String destination;
	private Route route;
	private Feedback feedback;
	
	
}
