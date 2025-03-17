package concert.tracker.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import concert.tracker.controller.model.ConcertData;
import concert.tracker.dao.ConcertDao;
import concert.tracker.entity.Concert;

@Service
public class ConcertService {
	@Autowired
	private ConcertDao concertDao;

	/**
	 * Save a Concert object, whether a new (insert) or an update.
	 * 
	 * @param concertData
	 * @return ConcertData
	 */

	@Transactional(readOnly = false)
	public ConcertData saveConcert(ConcertData concertData) {
		Long concertId = concertData.getConcertId();
		Concert concert = findOrCreateConcert(concertId);
		copyConcertFields(concert, concertData);

		return new ConcertData(concertDao.save(concert));
	}

	/**
	 * If there is no concert ID, create a new Concert. Otherwise, call
	 * findConcertById and return the Concert that was found.
	 * 
	 * @param concertId
	 * @return Concert
	 */
	private Concert findOrCreateConcert(Long concertId) {
		if (Objects.isNull(concertId)) {
			return new Concert();
		} else {
			return findConcertById(concertId);
		}
	}

	/**
	 * Find a Concert by ID or throw a NoSuchElementException if the Concert doesn't
	 * exist.
	 * 
	 * @param concertId
	 * @return
	 */
	private Concert findConcertById(Long concertId) {
		return concertDao.findById(concertId)
				.orElseThrow(() -> new NoSuchElementException("Concert with ID=" + concertId + " does not exist."));
	}

	/**
	 * Called by saveConcert method. Sets Concert object fields to match ConcertData
	 * object fields.
	 * 
	 * @param concert
	 * @param concertData
	 */
	private void copyConcertFields(Concert concert, ConcertData concertData) {
		concert.setConcertId(concertData.getConcertId());
		concert.setName(concertData.getName());
		concert.setDate(concertData.getDate());
		// artists
	}

	/**
	 * Get a list of all Concerts. First get a list of all the Concert objects, then
	 * create a new List of ConcertData, loop through the Concert objects and add
	 * them to the List of ConcertData. Return the List of ConcertData to the
	 * VenueController class' retrieveAllConcerts method.
	 * 
	 * @return List<ConcertData>
	 */
	public List<ConcertData> retrieveAllConcerts() {
		List<Concert> concerts = concertDao.findAll();
		List<ConcertData> concertDataResults = new LinkedList<>();

		for (Concert concert : concerts) {
			ConcertData concertData = new ConcertData(concert);
			concertDataResults.add(concertData);
		}
		return concertDataResults;
	}
}
