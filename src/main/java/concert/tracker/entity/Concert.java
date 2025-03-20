package concert.tracker.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Concert {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long concertId;

	private String name;
	private String date;

	// Many Concerts -> one Venue
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "venue_id")
	private Venue venue;

	// Many Concerts -> many Artists
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	// @formatter:off
	@JoinTable(
		name = "concert_artist", 
		joinColumns = @JoinColumn(name = "concert_id"), 
		inverseJoinColumns = @JoinColumn(name = "artist_id")
	)
	// @formatter:on
	private Set<Artist> artists = new HashSet<>();

}
