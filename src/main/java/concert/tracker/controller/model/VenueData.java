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
	 * Construct a VenueData object from a Venue object.
	 * 
	 * @param venue The Venue object that contains the data to initialize the
	 *              VenueData object.
	 */
	public VenueData(Venue venue) {
		this.venueId = venue.getVenueId();
		this.name = venue.getName();
		this.streetAddress = venue.getStreetAddress();
		this.city = venue.getCity();
		this.state = venue.getState();
		this.zip = venue.getZip();

		// Convert the concerts associated with the venue into ConcertData objects.
		for (Concert concert : venue.getConcerts()) {
			this.concerts.add(new ConcertData(concert));
		}
	}
	
	/**
	 * Construct a VenueData object from data passed as parameters.
	 * 
	 * @param venueId
	 * @param name
	 * @param streetAddress
	 * @param city
	 * @param state
	 * @param zip
	 */
	public VenueData(Long venueId, String name, String streetAddress, String city, String state, String zip) {
		this.venueId = venueId;
		this.name = name;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	/**
	 * Convert a VenueData object back to a Venue object.
	 * 
	 * @return Venue A new Venue object created using the data from the VenueData
	 *         object.
	 */
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
