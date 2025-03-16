package concert.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import concert.tracker.controller.model.VenueData;
import concert.tracker.service.VenueService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/concert_tracker")
@Slf4j
public class VenueController {
	@Autowired
	private VenueService venueService;
	
	@PostMapping("/venue")
	@ResponseStatus(code = HttpStatus.CREATED)
	public VenueData insertVenue(@RequestBody VenueData venueData) {
		log.info("Creating venue {}", venueData);
		return venueService.saveVenue(venueData);
	}
}
