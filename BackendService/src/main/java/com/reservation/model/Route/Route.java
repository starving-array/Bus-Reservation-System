package com.reservation.model.Route;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reservation.model.Bus.Bus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer routeId;

	private String routeFrom;
	private String routeTo;
	private Integer distance;
	
//	@JsonIgnore
	private Boolean activeStatus;
	
	

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bus_route")
	private Set<Bus> routeOfBuses = new HashSet<>();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		return Objects.equals(routeFrom, other.routeFrom) && Objects.equals(routeTo, other.routeTo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(routeFrom, routeTo);
	} 

}
