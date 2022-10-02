package com.reservation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.exceptions.UserException;
import com.reservation.model.User.User;
import com.reservation.service.UserService.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/user")
public class UserServiceController {

//	@Autowired
//	private UserService uDaoService;
//
//
//	@PostMapping("/")
//	public ResponseEntity<User> addNewUser(@RequestBody User user) throws UserException {
//		User newUser = uDaoService.createUser(user);
//		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
//	}
//
//	@PutMapping("/update")
//	public ResponseEntity<User> updateUserInfo(@RequestBody User user) throws UserException {
//		User updatedUser = uDaoService.updateUser(user);
//		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
//	}
//
//	@GetMapping("/all")
//	public ResponseEntity<List<User>> viewAllUser() throws UserException {
//		List<User> list = uDaoService.viewAllUser();
//		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
//	}
//
//	@DeleteMapping("/del/{userid}")
//	public ResponseEntity<User> deleteUser(@PathVariable("userId") Integer id) throws UserException {
//		User user = uDaoService.deleteUser(id);
//		return new ResponseEntity<User>(user, HttpStatus.OK);
//	}
//
//	@GetMapping("/{userId}")
//	public ResponseEntity<User> viewUser(@PathVariable("userId") Integer id) throws UserException {
//		User user = uDaoService.viewUser(id);
//		return new ResponseEntity<User>(user, HttpStatus.OK);
//	}

	@Autowired
	private UserService cService;

	@PostMapping("/user")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) throws UserException {

		User savedCustomer = cService.createUser(user);

		return new ResponseEntity<User>(savedCustomer, HttpStatus.CREATED); // keep original
	}

	@PutMapping("/user")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @RequestParam(required = false) String key)
			throws UserException {

		User updatedCustomer = cService.updateUser(user, key);

		return new ResponseEntity<User>(updatedCustomer, HttpStatus.OK); // change Main

	}
	
	

}
