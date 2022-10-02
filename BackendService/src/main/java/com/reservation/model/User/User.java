package com.reservation.model.User;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	private Integer userLoginId;

	private String UserName;
	private String password;
	private String firstName;
	private String LastName;
	private Long contact;
	@JsonIgnore
	private Double wallet;

	private String email;

//	@JsonIgnore
//	@OneToOne(mappedBy = "wallet_user")
//	private Wallet user_wallet;

	@JsonIgnore
	@OneToMany(mappedBy = "reservation_User")
	private List<Reservation> user_reservation;

}
