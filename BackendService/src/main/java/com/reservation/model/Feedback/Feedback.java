package com.reservation.model.Feedback;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
	private int feedBackId;
	private int  driverRating;
	private int  serviceRating;
	private int  overallRating;
	private String  comments;
	private LocalDate feedbackDate;
	
	private User user;
	private Bus bus;
	
	
	

	
	
	
	
	
	
	
	

}
