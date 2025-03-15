package concert.tracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Venue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long venueId;
	private String name;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
}
