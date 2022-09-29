package com.reservation.service.UserService;

import java.util.List;

import com.reservation.exceptions.UserException;
import com.reservation.model.User.User;

public interface UserService {

	public User addUser(User user) throws UserException;

	public User updateUser(User user) throws UserException;

	public User deleteUser(Integer userId) throws UserException;

	public User viewUser(Integer userId) throws UserException;

	public List<User> viewAllUser() throws UserException;

}
