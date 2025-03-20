package concert.tracker.controller.model;

import java.util.HashSet;
import java.util.Set;

import concert.tracker.entity.Artist;
import concert.tracker.entity.Concert;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArtistData {
	private Long artistId;
	private String name;
	private String genre;
	private Set<ConcertData> concerts = new HashSet<>();
	
	/**
	 * Construct an ArtistData object from an Artist object
	 * 
	 * @param artist
	 * @return ArtistData
	 */
	public ArtistData(Artist artist) {
		this.artistId = artist.getArtistId();
		this.name = artist.getName();
		this.genre = artist.getGenre();
		
		for (Concert concert : artist.getConcerts()) {
			this.concerts.add(new ConcertData(concert));
		}
	}
	
	/**
	 * Construct an Artist object from an ArtistData object
	 * 
	 * @return Artist
	 */
	public Artist toArtist() {
		Artist artist = new Artist();
		
		artist.setArtistId(artistId);
		artist.setName(name);
		artist.setGenre(genre);
		
		return artist;
	}
}
