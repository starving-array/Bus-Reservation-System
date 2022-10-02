package com.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.reservation.model.Route.Route;

@Repository
public interface RouteDao extends JpaRepository<Route, Integer> {

	public Route findByRouteFrom(String routeFrom);

	public Route findByRouteTo(String routeTo);
	
	
	@Query("select r.routeId from Route r where r.routeFrom=?1 and  r.routeTo=?2")
	public Integer getRouteIdBySourceDestination(String source, String destination);
}
