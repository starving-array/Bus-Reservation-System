package com.reservation.service.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservation.exceptions.UserException;
import com.reservation.model.User.User;
import com.reservation.repository.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User addUser(User user) {
		User savuser= userDao.save(user);//throw exception by user Exit or Not by Mobile and UserEmail
		return savuser;
	}

	@Override
	public User updateUser(User user) {
		Optional<User> opt=userDao.findById(user.getUserLoginId());
		if(opt.isPresent()) {
			return userDao.save(user);
		}else {
			throw new UserException("User IS Not Persent With This User ID  : "+emp.getuserId());
		}
	}

	@Override
	public User deleteUser(Integer userId) {
		Optional<User> opt=userDao.findById(userId);
		if(opt.isPresent()) {
			User userDeleted=opt.get();
			 userDao.delete(userDeleted);
			 return userDeleted;
		}else {
			throw new UserException("User IS Not Persent With This User ID : "+userId);
		}
	}

	@Override
	public User viewUser(Integer userId) {
		Optional<User> opt=userDao.findById(userId);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			throw new UserException("User IS Not Persent With This User ID : "+userId);
		}
	}

	@Override
	public List<User> viewAllUser() {
		List<User> listOfAllUser=userDao.findAll();
		if(listOfAllUser.size()>0) {
			return listOfAllUser;
		}else {
				throw new UserException("No User Found");
		}
	}

}
