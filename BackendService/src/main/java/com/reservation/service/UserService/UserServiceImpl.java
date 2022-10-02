package com.reservation.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservation.exceptions.UserException;
import com.reservation.model.User.User;
import com.reservation.model.login.CurrentUserSession;
import com.reservation.repository.UserDao;
import com.reservation.repository.Dao.SessionDao;

@Service
public class UserServiceImpl implements UserService {

//	@Autowired
//	private UserDao userDao;
//
//	@Override
//	public User addUser(User user) throws UserException {
//		User checkMail = userDao.findByEmail(user.getEmail());
//		User checkContact = userDao.findByContact(user.getContact());
//		if (checkMail != null) {
//			throw new UserException("This mail id already registered with us");
//		}
//		if (checkContact != null) {
//			throw new UserException("This phone number already registed with us. Try to reset password if you forgot");
//		}
//		user.setWallet(1000.00); // signup bonus
//		User savuser = userDao.save(user);// throw exception by user Exit or Not by Mobile and UserEmail
////		Wallet wallet = new Wallet();
////		wallet.setBalance(1000.00); // sign up bonus Rs 1000/-
////		wallet.setWallet_user(user);
////		savuser.setUser_wallet(wallet);
//		
//		return savuser;
//	}
//
//	@Override
//	public User updateUser(User user) throws UserException {
//		Optional<User> opt = userDao.findById(user.getUserLoginId());
//
//		if (opt.isPresent()) {
//			return userDao.save(user);
//		} else {
//			throw new UserException("User IS Not Persent With This User ID  : " + user.getUserLoginId());
//		}
//	}
//
//	@Override
//	public User deleteUser(Integer userId) throws UserException {
//		Optional<User> opt = userDao.findById(userId);
//		if (opt.isPresent()) {
//			User userDeleted = opt.get();
//			userDao.delete(userDeleted);
//			return userDeleted;
//		} else {
//			throw new UserException("User IS Not Persent With This User ID : " + userId);
//		}
//	}
//
//	@Override
//	public User viewUser(Integer userId) throws UserException {
//		Optional<User> opt = userDao.findById(userId);
//		if (opt.isPresent()) {
//			return opt.get();
//		} else {
//			throw new UserException("User IS Not Persent With This User ID : " + userId);
//		}
//	}
//
//	@Override
//	public List<User> viewAllUser() throws UserException {
//		List<User> listOfAllUser = userDao.findAll();
//		if (listOfAllUser.size() > 0) {
//			return listOfAllUser;
//		} else {
//			throw new UserException("No User Found");
//		}
//	}
	@Autowired
	private UserDao uDao;

	@Autowired
	private SessionDao sDao;

	// **********************************************************

	@Override
	public User createUser(User user) throws UserException {

		User existingUser = uDao.findByContact(user.getContact());

		if (existingUser != null)
			throw new UserException("User Already Registered with Mobile number");
		user.setWallet(1000.00);
		return uDao.save(user);

	}

	// **********************************************************

	@Override
	public User updateUser(User user, String key) throws UserException {

		CurrentUserSession loggedInUser = sDao.findByUuid(key);

		if (loggedInUser == null) {
			throw new UserException("Please provide a valid key to update a customer");
		}

		if (user.getUserLoginId() == loggedInUser.getUserId()) {
			// If LoggedInUser id is same as the id of supplied User which we want to update
			return uDao.save(user);
		} else
			throw new UserException("Invalid User Details, please login first");

	}

	// **********************************************************

}
