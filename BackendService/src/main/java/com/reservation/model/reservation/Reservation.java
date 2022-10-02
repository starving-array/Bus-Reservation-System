package com.reservation.model.reservation;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reservation.model.Bus.Bus;
import com.reservation.model.Feedback.Feedback;
import com.reservation.model.User.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
	private Integer reservationId;
	private String reservationStatus;
	private LocalDate reservationDate;
//	private LocalDateTime reservationTime;
	private LocalDate journeyDate;
	private Integer noOfSeats;
	private String source;
	private String destination;
//	Reservation// price
	private Double priceWithoutTax;
	private String tax;
	private Double priceWithTax;
	private Double refund;
	
//	@JsonIgnore
//	@OneToMany(mappedBy = "seatUserId")
//	private List<String> seatNumbers;
	
	// bus details
	

	@JsonIgnore
	@JoinColumn(name = "feedback_res_Id")
	@OneToOne(mappedBy = "feedback_reservation")
	private Feedback reservation_feedback;

	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "reservation_user_Id")
	private User reservation_User;

	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "reservation_bus_Id")
	private Bus reservation_bus; // bus update here through getter setter

}
