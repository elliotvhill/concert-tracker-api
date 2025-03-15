package concert.tracker.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	// One Venue -> many Concerts
	@OneToMany(mappedBy = "venue", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Concert> concerts = new HashSet<>();
}
