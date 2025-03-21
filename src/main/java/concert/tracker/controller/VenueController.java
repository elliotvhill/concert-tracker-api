package concert.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	 * Create and insert a new Venue into the database.
	 * 
	 * @param venueData The data for the new Venue to be created.
	 * @return VenueData The data of the newly created Venue.
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
	 * @return List<VenueData>
	 */
	@GetMapping("/venue")
	public List<VenueData> retrieveAllVenues() {
		log.info("Retrieving all venues...");
		return venueService.retrieveAllVenues();
	}

	/**
	 * Update an existing Venue object.
	 * 
	 * @param venueId   The ID of the venue to be updated.
	 * @param venueData The updated venue data to send in the request.
	 * @return VenueData The updated VenueData after the save.
	 */
	@PutMapping("/venue/{venueId}")
	public VenueData updateVenue(@PathVariable Long venueId, @RequestBody VenueData venueData) {
		venueData.setVenueId(venueId);
		log.info("Updating venue with ID={}", venueId);
		return venueService.saveVenue(venueData);
	}

	/**
	 * Create and insert a new Concert to the database.
	 * 
	 * @param concertData The data for the new Concert.
	 * @return ConcertData The data of the newly created concert.
	 */
	@PostMapping("/concert")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ConcertData insertConcert(@RequestBody ConcertData concertData) {
		log.info("Creating concert {}", concertData);
		return concertService.saveConcert(concertData);
	}

	/**
	 * Get a list of all Concerts.
	 * 
	 * @return List<ConcertData> A list of ConcertData objects representing all
	 *         Concerts.
	 */
	@GetMapping("/concert")
	public List<ConcertData> retrieveAllConcerts() {
		log.info("Retrieving all concerts...");
		return concertService.retrieveAllConcerts();
	}

	/**
	 * Update an existing Concert object.
	 * 
	 * @param concertId
	 * @param concertData
	 * @return concertData
	 */
	@PutMapping("/concert/{concertId}")
	public ConcertData updateConcert(@PathVariable Long concertId, @RequestBody ConcertData concertData) {
		concertData.setConcertId(concertId);
		log.info("Updating concert with ID={}", concertId);
		return concertService.saveConcert(concertData);
	}

	/**
	 * Create and insert a new Artist into the database.
	 * 
	 * @param artistData The data for the new Artist to be created.
	 * @return ArtistData The data of the newly created Artist.
	 */
	@PostMapping("/artist")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ArtistData insertArtist(@RequestBody ArtistData artistData) {
		log.info("Creating artist {}", artistData);
		return artistService.saveArtist(artistData);
	}

	/**
	 * Get a list of all Artists.
	 * 
	 * @return List<ArtistData> A list of ArtistData objects representing all
	 *         artists.
	 */
	@GetMapping("/artist")
	public List<ArtistData> retrieveAllArtists() {
		log.info("Retrieving all artists...");
		return artistService.retrieveAllArtists();
	}

	/**
	 * Update an existing Artist object.
	 * 
	 * @param artistId   The ID of the Artist to update.
	 * @param artistData The updated data passed in the request.
	 * @return ArtistData The updated Artist data after the object is saved.
	 */
	@PutMapping("/artist/{artistId}")
	public ArtistData updateArtist(@PathVariable Long artistId, ArtistData artistData) {
		artistData.setArtistId(artistId);
		log.info("Updating artist with ID={}", artistId);
		return artistService.saveArtist(artistData);
	}
}
