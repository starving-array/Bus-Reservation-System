package com.reservation.service.RouteService;

import java.util.List;

import com.reservation.exceptions.RouteException;
import com.reservation.model.Route.Route;

public interface RouteService {
	
	public Route addRoute(Route route) throws RouteException;
	
	public Route updateRoute(Route route) throws RouteException;
	
	public Route routeActive(Route route, Boolean status) throws RouteException;
	
	public Route viewRoute(Integer routeId) throws RouteException;
	
	public List<Route> viewAllRoute() throws RouteException;

}
