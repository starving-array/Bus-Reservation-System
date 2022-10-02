package com.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.reservation.model.Bus.Bus;

import java.util.*;;

@Repository
public interface BusDao extends JpaRepository<Bus, Integer> {
	
	public Bus findByBusNoPlate(String string);
	
	@Query("select c from Bus c where bus_route_route_Id=?1")
	public List<Bus> getBusByRouteId(Integer Id);
}
