package com.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reservation.model.User.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	public User findByEmail(String email);

	public User findByContact(Long contact);
}
