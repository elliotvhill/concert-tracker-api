package concert.tracker.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import concert.tracker.entity.Artist;

public interface ArtistDao extends JpaRepository<Artist, Long> {
	Set<Artist> findAllByArtistIdIn(Set<Long> artistIds);
}
