package com.reservation.service.BusService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservation.exceptions.BusException;
import com.reservation.model.Bus.Bus;
import com.reservation.repository.BusDao;

@Service
public class BusServiceImpl implements BusService {

	@Autowired
	private BusDao dao;
	
	@Override
	public Bus addBus(Bus bus) throws BusException {
		Optional<Bus> opt =  dao.findById(bus.getBusId());
		if(opt.isPresent()) {
			throw new BusException("Bus Already Registered with given BusId : "+bus.getBusId());
		}else {
			Bus bus2 =dao.save(bus);
			return bus;
		}
		//Bus number plate
	}

	@Override
	public Bus updateBus(Bus bus) throws BusException {
		Optional<Bus> opt = dao.findById(bus.getBusId());
		if(opt.isPresent()) {
			return dao.save(bus);
		}else {
			throw new BusException("Bus does not exist with given BusId : "+bus.getBusId());
		}
	}

	@Override
	public Bus deleteBus(int busId) throws BusException {
		Optional<Bus> opt = dao.findById(busId);
		if(opt.isPresent()) {
			Bus busExisting =  opt.get();
			dao.delete(busExisting);
			return busExisting;
		}else {
			throw new BusException("Bus does not exist with given BusId : "+busId);
		}
		
	}

	@Override
	public Bus viewBus(int busId) throws BusException {
		Optional<Bus> opt = dao.findById(busId);
		if(opt.isPresent()) {
			Bus busExisting =  opt.get();
			return busExisting;
		}else{
			throw new BusException("Bus does not exist with given BusId : "+busId);
		}
		// DTO
	}

	@Override
	public String busTypeAc(int busId) throws BusException {
		Optional<Bus> opt = dao.findById(busId);
		if(opt.isPresent()) {
			Bus bus = opt.get();
			String busType = bus.getBusType();
			return busType;
		}else {
			throw new BusException("Bus does not exist with given BusId : "+busId);
		}
		
	}

	@Override
	public List<Bus> viewAllBus() throws BusException {
		List<Bus> buses =  dao.findAll();
		if(buses.size()>0) {
			return buses;
		}else {
			throw new BusException("No Bus Found...");
		}

	}

}
