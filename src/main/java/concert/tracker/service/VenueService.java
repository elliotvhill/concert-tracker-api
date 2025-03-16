package concert.tracker.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import concert.tracker.controller.model.VenueData;
import concert.tracker.dao.VenueDao;
import concert.tracker.entity.Venue;

@Service
public class VenueService {
	@Autowired
	private VenueDao venueDao;

	/**
	 * @param venueData
	 * @return
	 */

	@Transactional(readOnly = false)
	public VenueData saveVenue(VenueData venueData) {
		Long venueId = venueData.getVenueId();
		Venue venue = findOrCreateVenue(venueId);
		copyVenueFields(venue, venueData);

		return new VenueData(venueDao.save(venue));
	}

	/**
	 * If there is no venue ID, create a new Venue. Otherwise, call findVenueById
	 * and return the Venue that was found.
	 * 
	 * @param venueId
	 * @return
	 */
	private Venue findOrCreateVenue(Long venueId) {
		if (Objects.isNull(venueId)) {
			return new Venue();
		} else {
			return findVenueById(venueId);
		}
	}

	/**
	 * Find a Venue by ID or throw a NoSuchElementException if it doesn't exist.
	 * 
	 * @param venueId
	 * @return
	 */
	private Venue findVenueById(Long venueId) {
		return venueDao.findById(venueId)
				.orElseThrow(() -> new NoSuchElementException("Venue with ID=" + venueId + " does not exist."));
	}

	/**
	 * Called by saveVenue method. Sets Venue object fields to match VenueData
	 * object fields.
	 * 
	 * @param venue
	 * @param venueData
	 */
	private void copyVenueFields(Venue venue, VenueData venueData) {
		venue.setVenueId(venueData.getVenueId());
		venue.setName(venueData.getName());
		venue.setStreetAddress(venueData.getStreetAddress());
		venue.setCity(venueData.getCity());
		venue.setState(venueData.getState());
		venue.setZip(venueData.getZip());
	}
}
