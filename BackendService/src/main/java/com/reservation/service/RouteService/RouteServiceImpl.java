package com.reservation.service.RouteService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservation.exceptions.BusException;
import com.reservation.exceptions.RouteException;
import com.reservation.exceptions.bookingException;
import com.reservation.model.Bus.Bus;
import com.reservation.model.Route.Route;
import com.reservation.model.bookingDiary.bookingDiary;
import com.reservation.repository.RouteDao;
import com.reservation.service.BusService.BusService;
import com.reservation.service.bookingDiary.bookingDiaryService;

@Service
public class RouteServiceImpl implements RouteService {

	@Autowired
	private RouteDao routeDao;

	@Autowired
	private BusService dao;

	@Autowired
	private bookingDiaryService bookingdao;

	@Override
	public Route addRoute(Route route) throws RouteException {
		Route to = routeDao.findByRouteTo(route.getRouteTo());
		Route from = routeDao.findByRouteFrom(route.getRouteFrom());

		if (to == null || from == null) {

			return routeDao.save(route);
		} else {
			throw new RouteException("This root already added");
		}
	}

	@Override
	public Route updateRoute(Route route) throws RouteException {
		Optional<Route> routeExistence = routeDao.findById(route.getRouteId());
		if (routeExistence.isPresent()) {
			return routeDao.save(route);
		} else {
			throw new RouteException("This route doesn't exitsts with us");
		}
	}

	@Override
	public Route routeActive(Route route, Boolean status) throws RouteException {
		Optional<Route> routeExistence = routeDao.findById(route.getRouteId());
		if (routeExistence.isPresent()) {
			// if route already active throw exception
			if (routeExistence.get().getActiveStatus() == status) {
				String statString = "Active";
				if (status == false) {
					statString = "Deactivated";
				}
				throw new RouteException("The route already " + statString);
			} else {
				routeExistence.get().setActiveStatus(status);
			}
			// else set statues to its active status
			return routeDao.save(routeExistence.get());
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
		if (listOfRoute.size() > 0) {
			return listOfRoute;
		} else {
			throw new RouteException("No route found in the database");
		}
	}

	// search data for user based on source destination and dataofJourney
	@Override
	public List<bookingDiary> getRouteIdbySourceDestinationJourneyDate(String source, String destination,
			LocalDateTime journeyDate) throws RouteException, BusException, bookingException {
		List<bookingDiary> selectedBusForUser = new ArrayList<>(); // answer to show on search for user/ presentation

		// get the route id with matching destination
		Integer routeId = routeDao.getRouteIdBySourceDestination(source, destination); 
		
		//with this id we can find all the buses currently active for a particular route
		// though there is no guarantee if that function will still run on that future booking date
		// will fix that issue in future
		// if a bus cancel, the user for that bus will be either shifted to another bus or they will get refund
		
		if (routeId == null) {
			
			
			throw new BusException("No buses avaliable from " + source + " to " + destination);
		
		
		} else {

			List<Bus> listOfBus = dao.getBusListByRouteId(routeId); // get all the buses registered and ready for this route given by user

			if (listOfBus.size() == 0) {
				throw new BusException("No buses avaliable now from " + source + " to " + destination + " for "
						+ journeyDate.toLocalDate());
			} else {
				// this will hold record for those buses who are already accepted a few booking and registered on the diary
				selectedBusForUser = getRouteIdbySourceDestinationJourneyDate(source, destination, journeyDate); 

				// need to check if any bus that is able to go to same route is missing from the log book
				// if missing then register them and then return them to UI so user can choose accordingly
				// the size for both list above will tell for missing no
				
				if (selectedBusForUser.size() != listOfBus.size()) {
					
					Set<Integer> set = new HashSet<>();
					selectedBusForUser.forEach(a -> set.add(a.getBus_id()));

					for (int i = 0; i < listOfBus.size(); i++) {
						if (!set.contains(listOfBus.get(i).getBusId())) {
							// match found
							// add to log book and also to the list for UI
							bookingDiary booking = new bookingDiary();
							booking.setJourneyDate_bookingDiary(journeyDate);
							booking.setBus_id(listOfBus.get(i).getBusId());
							booking.setSeatBooked(0);
							booking.setSeatAvaliable(listOfBus.get(i).getSeats());
							booking.setTravel_route_id(routeId);
							bookingdao.addBookingDiaryData(booking);
							selectedBusForUser.add(booking);
						}
					}
				}
				return selectedBusForUser;
			}

		}

	}

}

//List<Route> allRoutes = routeDao.findAll();
//// getting routeId with matching source and destination from route table
//if (allRoutes.size() > 0) {
//	for (int i = 0; i < allRoutes.size(); i++) {
//		if (allRoutes.get(i).getRouteFrom().equalsIgnoreCase(destination)
//				&& allRoutes.get(i).getRouteTo().equalsIgnoreCase(destination)) {
//			routeId = allRoutes.get(i).getRouteId();
//
//		}
//	}
//} else {
//	throw new RouteException("Sorry for the inconveninence. We don't have service for this path now");
//}
