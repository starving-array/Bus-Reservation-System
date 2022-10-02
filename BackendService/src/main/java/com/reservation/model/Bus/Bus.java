package com.reservation.model.Bus;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reservation.model.Feedback.Feedback;
import com.reservation.model.Route.Route;
import com.reservation.model.reservation.Reservation;

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
	private Integer Price;

	private String busNoPlate; // it will prevent dublicate registration
    //	private Driver driver; // in future driver directiry will be there so driver can switch
	// for now lets assume every bus can have only one driver
	private String driverName;
	private String busType; // ac/ no nonac

	private String arrivalTime; // it can be update
	private String departureTime; // same
	private Integer seats; // total seat decides by bus owner

	@ManyToOne
//	@JoinColumn(name = "Route_Bus_Id")
	private Route bus_route; // get route from Route where source=? and destination=?

	// bus can change its route
	// once bus complete a journey, the route will be auto reverse
	// unless the driver manually request for any new route
	// which can be from anywhere t anywhere as driver not bound to
	// carry the passenger all time

	// feedback update everytime a user post one

	@JsonIgnore
	@OneToMany(mappedBy = "feedback_bus", cascade = CascadeType.ALL)
	private List<Feedback> bus_feedbacks = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "reservation_bus", cascade = CascadeType.ALL)
	private List<Reservation> bus_reservation = new ArrayList<>();

}
