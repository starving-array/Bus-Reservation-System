package com.reservation.repository.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservation.model.login.CurrentUserSession;

public interface SessionDao extends JpaRepository<CurrentUserSession, Integer> {

	public CurrentUserSession findByUuid(String uuid);

}
