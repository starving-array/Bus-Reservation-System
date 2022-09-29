package com.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservation.model.Route.Route;

public interface RouteDao extends JpaRepository<Route, Integer>{

}
