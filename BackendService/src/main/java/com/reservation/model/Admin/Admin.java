package com.reservation.model.Admin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminId;
	private String adminUsername;
	private String adminPassword;
	
}
