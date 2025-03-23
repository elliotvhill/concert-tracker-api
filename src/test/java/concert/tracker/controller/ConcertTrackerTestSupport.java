package concert.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import concert.tracker.controller.model.VenueData;
import concert.tracker.entity.Venue;

public class ConcertTrackerTestSupport {

	// Set table names from database as constants to use in the test SQL queries.
	private static final String VENUE_TABLE = "venue";
	private static final String CONCERT_TABLE = "concert";
	private static final String ARTIST_TABLE = "artist";
	private static final String CONCERT_ARTIST_TABLE = "concert_artist";

	// Test data to insert.
	private static final String INSERT_ARTIST_1_SQL = """
			INSERT INTO artist
			(name, genre)
			VALUES ("Coldplay", "garbage")
			""";

	private static final String INSERT_ARTIST_2_SQL = """
			INSERT INTO artist
			(name, genre)
			VALUES ("Taylor Swift", "trash")
			""";

	private static final String INSERT_CONCERT_1_SQL = """
			INSERT INTO concert
			(name, date, venue_id)
			VALUES ("Eras Tour", "12/12/2021", 1)
			""";

	private static final String INSERT_CONCERT_2_SQL = """
			INSERT INTO concert
			(name, date, venue_id)
			VALUES ("Eras Tour 2", "12/13/2021", 2)
			""";

	// Define test VenueData objects to be inserted
	// @formatter:off
	private VenueData insertVenue1 = new VenueData(
			1L,
			"Madison Square Garden",
			"500 Madison Ave",
			"New York",
			"NY",
			"10000"
	);

	private VenueData insertVenue2 = new VenueData(
			2L,
			"Radio City Music Hall",
			"1 Lexington Ave",
			"New York",
			"NY",
			"10001"
	);
	// @formatter:on
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private VenueController venueController;

	/**
	 * @param which The ID of the test venue to be inserted.
	 * @return The VenueData of the inserted venue.
	 */
	protected VenueData buildInsertVenue(int which) {
		return which == 1 ? insertVenue1 : insertVenue2;
	}

	/**
	 * @param venueData The data for the venue to be inserted.
	 * @return The VenueData object.
	 */
	protected VenueData insertVenue(VenueData venueData) {
		Venue venue = venueData.toVenue();
		VenueData clone = new VenueData(venue);
		
		clone.setVenueId(null);
		
		return venueController.insertVenue(clone);
	}

	/**
	 * @return The number of rows in the venue table.
	 */
	protected int rowsInVenueTable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, VENUE_TABLE);
	}
}
