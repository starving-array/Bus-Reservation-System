package com.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reservation.model.Feedback.Feedback;

@Repository
public interface FeedbackDao extends JpaRepository<Feedback, Integer> {

}
