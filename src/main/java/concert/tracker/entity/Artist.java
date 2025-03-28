package concert.tracker.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long artistId;
	private String name;
	private String genre;

	// Many Artists -> many Concerts
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "artists", cascade = CascadeType.PERSIST)
	private Set<Concert> concerts = new HashSet<>();
}
