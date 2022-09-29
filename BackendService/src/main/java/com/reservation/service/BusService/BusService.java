package com.reservation.service.BusService;

import java.util.List;

import com.reservation.exceptions.BusException;
import com.reservation.model.Bus.Bus;

public interface BusService {

	public Bus addBus(Bus bus) throws BusException;
	public Bus updateBus(Bus bus) throws BusException;
	public Bus deleteBus(int busId) throws BusException;
	public Bus viewBus(int busId) throws BusException;
	public String busTypeAc(int busId) throws BusException;
	public List<Bus> viewAllBus() throws BusException;
	
}
