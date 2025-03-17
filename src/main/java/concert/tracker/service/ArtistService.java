package concert.tracker.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import concert.tracker.controller.model.ArtistData;
import concert.tracker.dao.ArtistDao;
import concert.tracker.entity.Artist;

@Service
public class ArtistService {
	@Autowired
	private ArtistDao artistDao;

	/**
	 * Save an Artist object, whether a new (insert) or an update.
	 */
	@Transactional(readOnly = false)
	public ArtistData saveArtist(ArtistData artistData) {
		Long artistId = artistData.getArtistId();
		Artist artist = findOrCreateArtist(artistId);
		copyArtistFields(artist, artistData);

		return new ArtistData(artistDao.save(artist));
	}

	/**
	 * If there is no artist ID, create a new Artist object. Otherwise, call
	 * findArtistById and return the Artist that was found.
	 * 
	 * @param artistId
	 * @return
	 */
	private Artist findOrCreateArtist(Long artistId) {
		if (Objects.isNull(artistId)) {
			return new Artist();
		} else {
			return findArtistById(artistId);
		}
	}

	/**
	 * Find an Artist by ID or throw a NoSuchElementException if the Artist isn't
	 * found, i.e. doesn't exist.
	 * 
	 * @param artistId
	 * @return
	 */
	private Artist findArtistById(Long artistId) {
		return artistDao.findById(artistId)
				.orElseThrow(() -> new NoSuchElementException("Artist with ID=" + artistId + " does not exist."));
	}

	/**
	 * Called by the saveArtist method. Sets Artist object fields to match
	 * ArtistData object fields.
	 * 
	 * @param artist
	 * @param artistData
	 */
	private void copyArtistFields(Artist artist, ArtistData artistData) {
		artist.setArtistId(artistData.getArtistId());
		artist.setName(artistData.getName());
		artist.setGenre(artistData.getGenre());
		// concerts
	}
}
