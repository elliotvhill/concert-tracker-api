package concert.tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import concert.tracker.dao.VenueDao;

@Service
public class VenueService {
	@Autowired
	private VenueDao venueDao;
}
