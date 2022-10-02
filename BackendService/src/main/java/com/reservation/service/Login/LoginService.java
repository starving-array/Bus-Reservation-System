package com.reservation.service.Login;

import com.reservation.exceptions.LoginException;
import com.reservation.exceptions.UserException;
import com.reservation.model.User.User;
import com.reservation.model.login.LoginDTO;

public interface LoginService {
	
	public String logIntoAccount(LoginDTO dto)throws LoginException;

	public String logOutFromAccount(String key)throws LoginException;
	
//	public String deleteUser(String key, Integer userId)throws LoginException, UserException;

	public User getUser(String key, Integer userId)throws LoginException, UserException;

	
}
