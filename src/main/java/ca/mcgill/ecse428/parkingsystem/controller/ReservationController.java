package ca.mcgill.ecse428.parkingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.parkingsystem.model.Reservation;
import ca.mcgill.ecse428.parkingsystem.repository.ReservationRepository;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	ReservationRepository repository;

	@GetMapping(path = "/id/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Reservation> getReservation(@PathVariable String id) {

		Reservation ReservationFound = repository.getReservation(id);
		return new ResponseEntity<>(ReservationFound, null, HttpStatus.OK);
	}

	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Reservation>> getReservations() {
		List<Reservation> Reservations = repository.getReservations();
		return new ResponseEntity<>(Reservations, null, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public Reservation addReservation(@RequestBody Reservation rsv) {
		return repository.addReservation(rsv);
	}
	
	@DeleteMapping(path = "/delete/{pKey}")
	public ResponseEntity<String> deleteReservation(@PathVariable String pKey) {
		boolean isDeleted = repository.deleteReservation(pKey);
		if(isDeleted) {
			return new ResponseEntity<String>("Reservation successfully deleted.", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<String>("Reservation could not be found.", HttpStatus.BAD_REQUEST);
		}
	}
	
}
