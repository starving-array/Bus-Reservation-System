package com.reservation.model.Bus;

import java.sql.Driver;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Bus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer busId;
	private String busName;
	private Driver driver;
	private String busType;
	private String routeFrom;
	private String routeTo;
	private LocalTime arrivalTime; 
	private LocalTime departureTime; 
	private Integer seats;
	private Integer availableSeats;   
	private Integer nonAcPrice;
	private Integer acPrice;
	
}
