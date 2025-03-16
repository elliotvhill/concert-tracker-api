package concert.tracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import concert.tracker.entity.Concert;

public interface ConcertDao extends JpaRepository<Concert, Long> {

}
