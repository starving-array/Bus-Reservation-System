package com.reservation.model.Feedback;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reservation.model.Bus.Bus;
import com.reservation.model.reservation.Reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer feedBackId;
	private Integer  driverRating;
	private Integer  serviceRating;
	private Integer  overallRating;
	private String  comments;
	private LocalDate feedbackDate;
//		
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Reservation_Id")
	private Reservation feedback_reservation;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "Bus_Id")
	private Bus feedback_bus;
	
	

	
//feedback
	
}
