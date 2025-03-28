package concert.tracker.service;

import java.util.LinkedList;
import java.util.List;
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
	 * Save a Venue object, whether a new (insert) or an update.
	 * 
	 * @param venueData
	 * @return VenueData
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
	 * @return Venue
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
	 * @return Venue
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

	/**
	 * Retrieve a list of all the venues as VenueData objects.
	 * 
	 * @return List of all Venues as VenueData objects.
	 */
	public List<VenueData> retrieveAllVenues() {
		/**
		 * Retrieve list of venues as Venue objects.
		 */
		List<Venue> venues = venueDao.findAll();

		/**
		 * Create empty List of VenueData (because retrieveAllVenues method in the
		 * controller returns a VenueData object.
		 */
		List<VenueData> venueDataResults = new LinkedList<>();

		/**
		 * Loop through Venues List and add entities to VenueData List.
		 */
		for (Venue venue : venues) {
			VenueData venueData = new VenueData(venue);
			venueDataResults.add(venueData);
		}

		return venueDataResults;
	}

	/**
	 * Delete a Venue by ID.
	 * 
	 * @param venueId The ID of the Venue to delete.
	 */
	@Transactional(readOnly = false)
	public void deleteVenueById(Long venueId) {
		Venue venue = findVenueById(venueId);
		venueDao.delete(venue);
	}
}
