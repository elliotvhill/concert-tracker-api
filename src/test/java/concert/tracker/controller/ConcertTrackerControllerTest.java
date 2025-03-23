package concert.tracker.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import concert.tracker.ConcertTrackerApplication;
import concert.tracker.controller.model.VenueData;

@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = ConcertTrackerApplication.class)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:data.sql"})
@SqlConfig(encoding = "utf-8")
public class ConcertTrackerControllerTest extends ConcertTrackerTestSupport {
	
	@Test
	void testInsertVenue() {
		// Given: A request to create a Venue
		VenueData request = buildInsertVenue(1);
		VenueData expected = buildInsertVenue(1);
		System.out.println(request);
		
		// When: the venue is added to the venue table
		VenueData actual = insertVenue(request);
		
		// Then: the venue returned is what is expected
		assertThat(actual).isEqualTo(expected);
		
		// And: there is one row in the venue table.
		assertThat(rowsInVenueTable()).isOne();
	}

}
