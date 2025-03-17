package concert.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import concert.tracker.controller.model.ArtistData;
import concert.tracker.controller.model.ConcertData;
import concert.tracker.controller.model.VenueData;
import concert.tracker.service.ArtistService;
import concert.tracker.service.ConcertService;
import concert.tracker.service.VenueService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/concert_tracker")
@Slf4j
public class VenueController {
	@Autowired
	private VenueService venueService;
	@Autowired
	private ConcertService concertService;
	@Autowired
	private ArtistService artistService;
	
	/**
	 * Create and insert a new Venue to the database.
	 * 
	 * @param venueData
	 * @return
	 */
	@PostMapping("/venue")
	@ResponseStatus(code = HttpStatus.CREATED)
	public VenueData insertVenue(@RequestBody VenueData venueData) {
		log.info("Creating venue {}", venueData);
		return venueService.saveVenue(venueData);
	}
	
	/**
	 * Get a list of all Venues.
	 * 
	 * @param concertData
	 * @return
	 */
	@GetMapping("/venue")
	public List<VenueData> retrieveAllVenues() {
		log.info("Retrieving all venues...");
		return venueService.retrieveAllVenues();
	}
	
	@PostMapping("/concert")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ConcertData insertConcert(@RequestBody ConcertData concertData) {
		log.info("Creating concert {}", concertData);
		return concertService.saveConcert(concertData);
	}
	
	// retrieve all Concerts
	@GetMapping("/concert")
	public List<ConcertData> retrieveAllConcerts() {
		log.info("Retrieving all concerts...");
		return concertService.retrieveAllConcerts();
	}
	
	@PostMapping("/artist")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ArtistData insertArtist(@RequestBody ArtistData artistData) {
		log.info("Creating artist {}", artistData);
		return artistService.saveArtist(artistData);
	}
	
	// TODO: retrieve all Artists
	@GetMapping("/artist")
	public List<ArtistData> retrieveAllArtists() {
		log.info("Retrieving all artists...");
		return artistService.retrieveAllArtists();
	}
}
