package com.reservation.model.User;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	
	@NotBlank
	private String name;
	
	@Size(min=10,max=10,message = "Mobile number is invalid")
	@NotBlank
	private String contact;
	
	
//	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,100}$",message = "Please enter a valid password which include upperCase lowerCase specialCharector number and character between 6 to 12")
	@NotBlank
	private String password;
	
	@Email(message = "Please enter valid Email address")
	@NotBlank
	private String email;

	@JsonIgnore
	private Double wallet;
	
	
//	@JsonIgnore
//	@OneToOne(mappedBy = "wallet_user")
//	private Wallet user_wallet;

//	@JsonIgnore
//	@OneToMany(mappedBy = "reservation_User")
//	private List<Reservation> user_reservation;

}
