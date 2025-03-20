package concert.tracker.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import concert.tracker.controller.model.ConcertData;
import concert.tracker.entity.Concert;

public interface ConcertDao extends JpaRepository<Concert, Long> {
	Set<Concert> findAllByConcertIdIn(Set<ConcertData> concerts);
}
