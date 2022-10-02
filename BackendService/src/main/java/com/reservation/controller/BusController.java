package com.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.exceptions.BusException;
import com.reservation.model.Bus.Bus;
import com.reservation.service.BusService.BusService;

@RestController
@RequestMapping("/buses")
public class BusController {

	@Autowired
	private BusService busService;

	@PostMapping("/")
	public ResponseEntity<Bus> addNewBusController(@RequestBody Bus bus) throws BusException {
		Bus newBus = busService.addBus(bus);

		return new ResponseEntity<Bus>(newBus, HttpStatus.CREATED);
	}

	@PutMapping("/")
	public ResponseEntity<Bus> updateBus(@RequestBody Bus bus) throws BusException {
		Bus newBus = busService.updateBus(bus);
		return new ResponseEntity<Bus>(newBus, HttpStatus.OK);
	}

	@DeleteMapping("/{busId}")
	public ResponseEntity<Bus> deleteBus(@PathVariable("busId") Integer busId) throws BusException {
		Bus bus = busService.deleteBus(busId);
		return new ResponseEntity<Bus>(bus, HttpStatus.OK);
	}

	@GetMapping("/getType/{busId}")
	public ResponseEntity<String> getBusType(@PathVariable("busId") Integer busId) throws BusException {
		String busType = busService.busTypeAc(busId);
		return new ResponseEntity<String>(busType, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Bus>> viewAllBus() throws BusException {
		List<Bus> listOfBus = busService.viewAllBus();
		return new ResponseEntity<List<Bus>>(listOfBus, HttpStatus.OK);
	}

}
