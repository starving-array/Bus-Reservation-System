package com.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reservation.model.Bus.Bus;

@Repository
public interface BusDao extends JpaRepository<Bus, Integer> {

}
