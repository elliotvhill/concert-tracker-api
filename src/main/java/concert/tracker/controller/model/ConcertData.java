package concert.tracker.controller.model;

import java.util.HashSet;
import java.util.Set;

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
	private Venue venue;
	private Set<ArtistData> artists = new HashSet<>();
	
	/**
	 * Construct a ConcertData object from a Concert object
	 * 
	 * @param concert
	 */
	public ConcertData(Concert concert) {
		this.concertId = concert.getConcertId();
		this.name = concert.getName();
		this.date = concert.getDate();
		this.venue = concert.getVenue();
		
		for (Artist artist : concert.getArtists()) {
			this.artists.add(new ArtistData(artist));
		}
	}
	
	/**
	 * Construct a new Concert object from a Concert Data object
	 * 
	 * @return
	 */
	public Concert toConcert() {
		Concert concert = new Concert();
		
		concert.setConcertId(concertId);
		concert.setName(name);
		concert.setDate(date);
		concert.setVenue(venue);
		
		for (ArtistData artistData : artists) {
			concert.getArtists().add(artistData.toArtist());
		}
		
		return concert;
	}
	
}
