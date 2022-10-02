package com.reservation.service.BusService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservation.exceptions.BusException;
import com.reservation.model.Bus.Bus;
import com.reservation.model.Bus.BusDTO;
import com.reservation.model.Route.Route;
import com.reservation.repository.BusDao;
import com.reservation.repository.RouteDao;

@Service
public class BusServiceImpl implements BusService {

	@Autowired
	private BusDao dao;

	@Autowired
	private RouteDao routeDao;

	@Override
	public Bus addBus(Bus bus) throws BusException {
		Bus existBus = dao.findByBusNoPlate(bus.getBusNoPlate());
		if (existBus != null) {
			throw new BusException("This bus already registered with us");
		} else {	
			Route newRoute = new Route();
			newRoute.setDistance(bus.getBus_route().getDistance());
			newRoute.setRouteFrom(bus.getBus_route().getRouteFrom());
			newRoute.setRouteTo(bus.getBus_route().getRouteTo());
			newRoute.setActiveStatus(true);
			List<Route> routeList = routeDao.findAll();
			if (routeList.size() > 0) {
				for (int i = 0; i < routeList.size(); i++) {
					if (routeList.get(i).getRouteFrom().equalsIgnoreCase(newRoute.getRouteFrom())
							&& routeList.get(i).getRouteTo().equalsIgnoreCase(newRoute.getRouteTo())) {
//						newRoute = routeDao.findByRouteFrom(newRoute.getRouteFrom());
						newRoute = routeList.get(i);

					}
				}
			}
			routeDao.save(newRoute);

			Set<Bus> list = newRoute.getRouteOfBuses();
			list.add(bus);
			bus.setBus_route(newRoute);
			Bus busNewBus = dao.save(bus);
			return busNewBus;
		}
		// Bus number plate
	}

//
	@Override
	public Bus updateBus(Bus bus) throws BusException {
		Optional<Bus> opt = dao.findById(bus.getBusId());
		if (opt.isPresent()) {
//			if(!bus.getBusNoPlate().equalsIgnoreCase(opt.get().getBusNoPlate())) {
//				throw new BusException("Please contact customer care to change no plate");
//			}
			
			Bus bus2 = opt.get();
			Route newRoute = new Route();
			newRoute.setDistance(bus.getBus_route().getDistance());
			newRoute.setRouteFrom(bus.getBus_route().getRouteFrom());
			newRoute.setRouteTo(bus.getBus_route().getRouteTo());
			newRoute.setActiveStatus(bus.getBus_route().getActiveStatus());
			List<Route> routeList = routeDao.findAll();
			if (routeList.size() > 0) {
				for (int i = 0; i < routeList.size(); i++) {
					if (routeList.get(i).getRouteFrom().equalsIgnoreCase(newRoute.getRouteFrom())
							&& routeList.get(i).getRouteTo().equalsIgnoreCase(newRoute.getRouteTo())) {
						newRoute = routeList.get(i);

					}
				}
			}

			routeDao.save(newRoute);
			bus.setBus_route(newRoute);
			return dao.save(bus);
		} else {
			throw new BusException("Bus does not exist with given BusId : " + bus.getBusId());
		}
	}

	@Override
	public Bus deleteBus(Integer busId) throws BusException {
//		 only admin can do this 
		Optional<Bus> opt = dao.findById(busId);
		if (opt.isPresent()) {
			Bus busExisting = opt.get();
			dao.delete(busExisting);
			return busExisting;
		} else {
			throw new BusException("Bus does not exist with given BusId : " + busId);
		}

	}

	@Override
	public String busTypeAc(Integer busId) throws BusException {
		Optional<Bus> opt = dao.findById(busId);
		if (opt.isPresent()) {
			Bus bus = opt.get();
			String busType = bus.getBusType();
			return busType;
		} else {
			throw new BusException("Bus does not exist with given BusId : " + busId);
		}

	}

	@Override
	public List<Bus> viewAllBus() throws BusException {
		List<Bus> buses = dao.findAll();
		if (buses.size() > 0) {
			return buses;
		} else {
			throw new BusException("No Bus Found...");
		}

	}

	// will do later
	@Override
	public Bus changeRoute(String source, String destination) throws BusException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bus changeArrivalDepurture(String arrival, String departure) throws BusException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bus changeDriver(String driver) throws BusException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BusDTO viewBus(Integer busId) throws BusException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bus> getBusListByRouteId(Integer id) throws BusException {
		// TODO Auto-generated method stub
		List<Bus> listOfMatchedBus = dao.findAll();
		List<Bus> ans = new ArrayList<>();
		for (int index = 0; index < listOfMatchedBus.size(); index++) {
			if (listOfMatchedBus.get(index).getBus_route().getRouteId() == id) {
				ans.add(listOfMatchedBus.get(index));
			}
		}
		if (ans.size() == 0) {
			throw new BusException("No bus is running for this route. We will get back to you soon");
		}

		return ans;
	}

}
