package com.reservation.service.Login;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservation.exceptions.LoginException;
import com.reservation.exceptions.UserException;
import com.reservation.model.User.User;
import com.reservation.model.login.CurrentUserSession;
import com.reservation.model.login.LoginDTO;
import com.reservation.repository.UserDao;
import com.reservation.repository.Dao.SessionDao;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserDao uDao;

	@Autowired
	private SessionDao sDao;

	// ************	**************************************

	@Override
	public String logIntoAccount(LoginDTO dto) throws LoginException {

		User existingUser = uDao.findByContact(dto.getMobileNo());

		if (existingUser == null) {

			throw new LoginException("Please Enter a valid mobile number");

		}

		Optional<CurrentUserSession> validUserSessionOpt = sDao.findById(existingUser.getUserLoginId());

		if (validUserSessionOpt.isPresent()) {

			throw new LoginException("User already Logged In with this number");

		}

		if (existingUser.getPassword().equals(dto.getPassword())) {

			String key = RandomString.make(5);

			CurrentUserSession currentUserSession = new CurrentUserSession(existingUser.getUserLoginId(), key,
					LocalDateTime.now());

			sDao.save(currentUserSession);

			return currentUserSession.toString();
		} else
			throw new LoginException("Please Enter a valid password");

	}

	// **************************************************

	@Override
	public String logOutFromAccount(String key) throws LoginException {

		CurrentUserSession validUserSession = sDao.findByUuid(key);

		if (validUserSession == null) {
			throw new LoginException("User Not Logged In with this number");

		}

		sDao.delete(validUserSession);

		return "Logged Out..!!";

	}

	// **************************************************
//
//	@Override
//	public String deleteUser(String key, Integer userId) throws LoginException, UserException {
//		// TODO Auto-generated method stub
//
//		CurrentUserSession validUserSession = sDao.findByUuid(key);
//		Optional<User> validUser = uDao.findById(userId);
//
//		if (validUserSession == null) {
//			throw new LoginException("Please enter correct key..!!");
//
//		}
//		if (validUser.isEmpty()) {
//			throw new UserException("Please enter correct user id..!!");
//
//		}
//
//		if (validUserSession.getUserId() == validUser.get().getUserId()) {
//			sDao.delete(validUserSession);
//			uDao.delete(validUser.get());
//		}
//
//		return "Account deleted..!!";
//
//	}

	// **************************************************

	@Override
	public User getUser(String key, Integer userId) throws LoginException, UserException {
		// TODO Auto-generated method stub
		User user=new User();
		CurrentUserSession validUserSession = sDao.findByUuid(key);
		Optional<User> validUser = uDao.findById(userId);

		if (validUserSession == null) {
			throw new LoginException("Please enter correct key..!!");

		}
		if (validUser.isEmpty()) {
			throw new UserException("Please enter correct user id..!!");

		}

		if (validUserSession.getUserId() == validUser.get().getUserLoginId	()) {
			user=validUser.get(); return user;
		}
		throw new UserException("Please enter correct user id..!!");
		
	}

}
