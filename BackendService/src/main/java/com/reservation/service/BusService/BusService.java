package com.reservation.service.BusService;

import java.util.List;

import com.reservation.exceptions.BusException;
import com.reservation.model.Bus.Bus;
import com.reservation.model.Bus.BusDTO;

public interface BusService {

	public Bus addBus(Bus bus) throws BusException;

	public Bus updateBus(Bus bus) throws BusException;

	public Bus changeRoute(String source, String destination) throws BusException;

	public Bus changeArrivalDepurture(String arrival, String departure) throws BusException;

	public Bus changeDriver(String driver) throws BusException;

	public Bus deleteBus(Integer busId) throws BusException;

	public BusDTO viewBus(Integer busId) throws BusException;

	public String busTypeAc(Integer busId) throws BusException;

	public List<Bus> viewAllBus() throws BusException;


}
