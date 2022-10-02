package com.reservation.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.exceptions.BusException;
import com.reservation.exceptions.RouteException;
import com.reservation.exceptions.bookingException;
import com.reservation.model.Route.Route;
import com.reservation.model.bookingDiary.bookingDiary;
import com.reservation.service.RouteService.RouteService;

@RestController
@RequestMapping("/route")
public class RouteServiceController {

	@Autowired
	private RouteService rService;

	@PostMapping("/new")
	public ResponseEntity<Route> addNewRoute(@RequestBody Route route) throws RouteException {
		Route newRoute = rService.addRoute(route);
		return new ResponseEntity<Route>(newRoute, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Route> updateRoute(@RequestBody Route route) throws RouteException {
		Route newRoute = rService.updateRoute(route);
		return new ResponseEntity<Route>(newRoute, HttpStatus.OK);
	}

	@PutMapping("/{bool}")
	public ResponseEntity<Route> isActivateRoute(@RequestBody Route route, @PathVariable("bool") Boolean status)
			throws RouteException {
		Route newRoute = rService.routeActive(route, status);
		return new ResponseEntity<Route>(newRoute, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Route> getRouteDetail(@PathVariable("id") Integer id) throws RouteException {
		Route route = rService.viewRoute(id);
		return new ResponseEntity<Route>(route, HttpStatus.OK);
	}

	@GetMapping("/allRoute")
	public ResponseEntity<List<Route>> getAllRouteDetail() throws RouteException {
		List<Route> route = rService.viewAllRoute();
		return new ResponseEntity<List<Route>>(route, HttpStatus.OK);
	}

	@GetMapping("/search/{source}/{destination}/{JourneyDate}")
	public ResponseEntity<List<bookingDiary>> getRouteIdbySourceDestinationJourneyDate(
			@PathVariable("source") String source, @PathVariable("destination") String destination,
			@PathVariable("JourneyDate")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate journeyDate)
			throws RouteException, BusException, bookingException {
		List<bookingDiary> list = rService.getRouteIdbySourceDestinationJourneyDate(source, destination, journeyDate);
		return new ResponseEntity<List<bookingDiary>>(list, HttpStatus.OK);

	}

}
