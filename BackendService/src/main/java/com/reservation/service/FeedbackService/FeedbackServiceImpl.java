package com.reservation.service.FeedbackService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.reservation.model.Feedback.Feedback;
import com.reservation.repository.FeedbackDao;

public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackDao feedDao;

	@Override
	public Feedback addFeedBack(Feedback feedback) throws FeedBackException {
		Feedback feedBackNew = feedDao.save(feedback);
		return feedBackNew;
	}

	@Override
	public Feedback updateFeedBack(Feedback feedback) throws FeedBackException {
		Optional<Feedback> feedOpt = feedDao.findById(feedback.getFeedBackId());
		if (feedOpt.isPresent()) {
			return feedDao.save(feedback);
		} else {
			throw new FeedBackException("No data exists");
		}
	}

	@Override
	public Feedback viewFeedBack(int feedbackid) throws FeedBackException {
		Optional<Feedback> feedOpt = feedDao.findById(feedbackid);
		if (feedOpt.isPresent()) {
			return feedDao.get();
		} else {
			throw new FeedBackException("No data exists with this id" + feedbackid);
		}
	}

	@Override
	public List<Feedback> viewAllFeedBack() throws FeedBackException {
		List<Feedback> listOFeedbacks = feedDao.findAll();
		if(listOFeedbacks.size()>0) {
			return listOFeedbacks;
		}else {
			throw new FeedBackException("No data exists");
		}
		
	}

}
