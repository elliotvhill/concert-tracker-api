package concert.tracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Concert {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long concertId;
	private Long venueId;
	private String date;
}
