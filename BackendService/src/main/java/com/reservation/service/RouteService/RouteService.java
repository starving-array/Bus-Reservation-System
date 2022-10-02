package com.reservation.service.RouteService;

import java.time.LocalDateTime;
import java.util.List;

import com.reservation.exceptions.BusException;
import com.reservation.exceptions.RouteException;
import com.reservation.exceptions.bookingException;
import com.reservation.model.Route.Route;
import com.reservation.model.bookingDiary.bookingDiary;

public interface RouteService {

	public Route addRoute(Route route) throws RouteException;

	public Route updateRoute(Route route) throws RouteException;

	public Route routeActive(Route route, Boolean status) throws RouteException;

	public Route viewRoute(Integer routeId) throws RouteException;

	public List<Route> viewAllRoute() throws RouteException;

	// user search 
	public List<bookingDiary> getRouteIdbySourceDestinationJourneyDate(String source, String destination, LocalDateTime date) throws RouteException, BusException, bookingException;

}
