package com.reservation.model.User;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.reservation.model.reservation.Reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userLoginId;
	private String UserName;
	private String password;
	private String firstName;
	private String LastName;
	private long contact;
	private String email;

//	private Wallet wallet;
	private List<Reservation> reservation;
	
}
