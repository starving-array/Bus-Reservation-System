package com.reservation.service.RouteService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservation.exceptions.RouteException;
import com.reservation.model.Route.Route;
import com.reservation.repository.RouteDao;

@Service
public class RouteServiceImpl implements RouteService {

	@Autowired
	private RouteDao routeDao;

	@Override
	public Route addRoute(Route route) throws RouteException {
		// TODO Auto-generated method stub
		Route routenew = routeDao.save(route); // single data exist in the database; throw warning if already exist
		return routenew;
	}

	@Override
	public Route updateRoute(Route route) throws RouteException {
		Optional<Route> routeExistence = routeDao.findById(route.getRouteId());
		if (routeExistence.isPresent()) {
			return routeDao.save(route);
		} else {
			throw new RouteException("Route doesnot exitsts");
		}
	}

	@Override
	public Route routeActive(Route route, Boolean status) throws RouteException {
		Optional<Route> routeExistence = routeDao.findById(route.getRouteId());
		if (routeExistence.isPresent()) {
			// if route already active throw exception
			// else set statues to its active status
			return routeExistence.get();
		} else {
			throw new RouteException("Route doesnot exitsts");
		}
	}

	@Override
	public Route viewRoute(Integer routeId) throws RouteException {
		Optional<Route> routeExistence = routeDao.findById(routeId);
		if (routeExistence.isPresent()) {
			return routeExistence.get();
		} else {
			throw new RouteException("Route doesnot exitsts");
		}
	}

	@Override
	public List<Route> viewAllRoute() throws RouteException {
		List<Route> listOfRoute = routeDao.findAll();
		if(listOfRoute.size()>0) {
			return listOfRoute;
		}else {
			throw new RouteException("No route found in the database");
		}
	}

}
