package com.reservation.service.FeedbackService;

import java.util.List;

import com.reservation.model.Feedback.Feedback;

public interface FeedbackService {
	public Feedback addFeedBack(Feedback feedback) throws FeedBackException;

	public Feedback updateFeedBack(Feedback feedback) throws FeedBackException;

	public Feedback viewFeedBack(int feedbackid) throws FeedBackException;

	public List<Feedback> viewAllFeedBack() throws FeedBackException;

}
