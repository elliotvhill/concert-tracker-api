package concert.tracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import concert.tracker.entity.Venue;

public interface VenueDao extends JpaRepository<Venue, Long> {

}
