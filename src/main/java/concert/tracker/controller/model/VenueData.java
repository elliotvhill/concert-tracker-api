package concert.tracker.controller.model;

import java.util.HashSet;
import java.util.Set;

import concert.tracker.entity.Concert;
import concert.tracker.entity.Venue;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VenueData {
	private Long venueId;
	private String name;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private Set<ConcertData> concerts = new HashSet<>();
	
	/**
	 * Construct a VenueData object from a Venue object
	 * 
	 * @param venue
	 */
	public VenueData(Venue venue) {
		this.venueId = venue.getVenueId();
		this.name = venue.getName();
		this.streetAddress = venue.getStreetAddress();
		this.city = venue.getCity();
		this.state = venue.getState();
		this.zip = venue.getZip();
		
		for (Concert concert : venue.getConcerts()) {
			this.concerts.add(new ConcertData(concert));
		}
	}
	
	public Venue toVenue() {
		Venue venue = new Venue();
		
		venue.setVenueId(venueId);
		venue.setName(name);
		venue.setStreetAddress(streetAddress);
		venue.setCity(city);
		venue.setState(state);
		venue.setZip(zip);
		
		return venue;
	}
}
