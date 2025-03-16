package concert.tracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import concert.tracker.entity.Artist;

public interface ArtistDao extends JpaRepository<Artist, Long> {

}
