package concert.tracker.controller.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import concert.tracker.dao.ArtistDao;
import concert.tracker.dao.VenueDao;
import concert.tracker.entity.Artist;
import concert.tracker.entity.Concert;
import concert.tracker.entity.Venue;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConcertData {

	private Long concertId;
	private String name;
	private String date;
	private Long venueId;
	private Set<Long> artistIds = new HashSet<>();

	/**
	 * Construct a ConcertData object from a Concert object
	 * 
	 * @param concert
	 */
	public ConcertData(Concert concert) {
		this.concertId = concert.getConcertId();
		this.name = concert.getName();
		this.date = concert.getDate();
		this.venueId = (concert.getVenue() != null) ? concert.getVenue().getVenueId() : null;

		this.artistIds = concert.getArtists().stream().map(Artist::getArtistId).collect(Collectors.toSet());
	}

	/**
	 * Construct a new Concert object from a Concert Data object. VenueDao and
	 * ArtistDao are passed as parameters to prevent dependency and recursion
	 * issues.
	 * 
	 * @param venueDao  The VenueDao instance used to look up venues.
	 * @param artistDao The ArtistDao instance used to look up artists.
	 * @return A new Concert object populated with the values from this ConcertData.
	 */
	public Concert toConcert(VenueDao venueDao, ArtistDao artistDao) {
		Concert concert = new Concert();

		concert.setConcertId(concertId);
		concert.setName(name);
		concert.setDate(date);

		// Lookup venue using venueId if present
		if (this.venueId != null) {
			Venue venue = venueDao.findById(this.venueId).orElse(null);
			concert.setVenue(venue);
		}

		// Lookup artists using artistDao
		Set<Artist> artists = artistIds.stream().map(artistId -> artistDao.findById(artistId).orElse(null))
				.filter(Objects::nonNull).collect(Collectors.toSet());

		concert.setArtists(artists);

		return concert;
	}

}
