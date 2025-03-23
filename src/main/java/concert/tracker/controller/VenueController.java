package concert.tracker.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	 * @return The VenueData of the newly created Venue.
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
	 * @return List of all Venues.
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
	 * @return The updated VenueData after the save.
	 */
	@PutMapping("/venue/{venueId}")
	public VenueData updateVenue(@PathVariable Long venueId, @RequestBody VenueData venueData) {
		venueData.setVenueId(venueId);
		log.info("Updating venue with ID={}", venueId);
		return venueService.saveVenue(venueData);
	}

	// TODO: Delete a Venue by ID
	@DeleteMapping("/venue/{venueId}")
	public Map<String, String> deleteVenueById(@PathVariable Long venueId) {
		log.info("Attempting to delete venue with ID={}", venueId);
		venueService.deleteVenueById(venueId);
		
		return Map.of("message", "Deleted venue with ID=" + venueId + ".");
	}

	/**
	 * Throw an exception for a request to delete all venues.
	 */
	@DeleteMapping("/venue")
	public void deleteAllVenues() {
		log.info("Attempting to delete all venues...");
		throw new UnsupportedOperationException("Deleting all venues is not allowed.");
	}

	/**
	 * Create and insert a new Concert to the database.
	 * 
	 * @param concertData The data for the new Concert.
	 * @return The ConcertData of the newly created Concert.
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
	 * @return List of ConcertData objects representing all Concerts.
	 */
	@GetMapping("/concert")
	public List<ConcertData> retrieveAllConcerts() {
		log.info("Retrieving all concerts...");
		return concertService.retrieveAllConcerts();
	}

	/**
	 * Update an existing Concert object.
	 * 
	 * @param concertId   The ID of the Concert to update.
	 * @param concertData The Concert information to be updated.
	 * @return The updated ConcertData after the object is saved.
	 */
	@PutMapping("/concert/{concertId}")
	public ConcertData updateConcert(@PathVariable Long concertId, @RequestBody ConcertData concertData) {
		concertData.setConcertId(concertId);
		log.info("Updating concert with ID={}", concertId);
		return concertService.saveConcert(concertData);
	}

	/**
	 * Delete a Concert by ID.
	 * 
	 * @param concertId
	 * @return Map of a JSON response message confirming the deletion.
	 */
	@DeleteMapping("/concert/{concertId}")
	public Map<String, String> deleteConcertById(@PathVariable Long concertId) {
		log.info("Attempting to delete concert with ID={}", concertId);
		concertService.deleteConcertById(concertId);

		return Map.of("message", "Deleted concert with ID=" + concertId + ".");
	}

	/**
	 * Throw an exception for a request to delete all concerts.
	 */
	@DeleteMapping("/concert")
	public void deleteAllConcerts() {
		log.info("Attempting to delete all concerts...");
		throw new UnsupportedOperationException("Deleting all concerts is not allowed.");
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
	 * @return List of ArtistData objects representing all artists.
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
	 * @return The updated Artist Data after the object is saved.
	 */
	@PutMapping("/artist/{artistId}")
	public ArtistData updateArtist(@PathVariable Long artistId, ArtistData artistData) {
		artistData.setArtistId(artistId);
		log.info("Updating artist with ID={}", artistId);
		return artistService.saveArtist(artistData);
	}

	/**
	 * Delete an Artist by ID.
	 * 
	 * @param artistId
	 * @return Map of a JSON response message confirming the deletion.
	 */
	@DeleteMapping("/artist/{artistId}")
	public Map<String, String> deleteArtistById(@PathVariable Long artistId) {
		log.info("Attempting to delete artist with ID={}", artistId);
		artistService.deleteArtistById(artistId);

		return Map.of("message", "Deleted artist with ID=" + artistId + ".");
	}

	/**
	 * Throw an exception for a request to delete all artists.
	 */
	@DeleteMapping("/artist")
	public void deleteAllArtists() {
		log.info("Attempting to delete all artists...");
		throw new UnsupportedOperationException("Deleting all artists is not allowed.");
	}
}
