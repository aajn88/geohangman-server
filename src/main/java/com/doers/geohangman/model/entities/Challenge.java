package com.doers.geohangman.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.doers.geohangman.constants.ModelConstants;

/**
 * The Geohangman challenge. This class stores the challenger and the opponent
 * as well as the word to be guessed, map point (including zoom) and the image
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
@Entity
@Table(name = ModelConstants.CHALLENGES_TABLE, schema = ModelConstants.DEFAULT_SCHEMA)
public class Challenge {

	/** Challenge Id **/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	/** Challenger Id **/
	@NotNull
	private String challengerId;

	/** Opponent Id **/
	@NotNull
	private String opponentId;

	/** The word to be guessed **/
	@NotNull
	private String word;

	/** Map point Latitude **/
	@NotNull
	private Double lat;

	/** Map point Longitude **/
	@NotNull
	private Double lng;

	/** Map point zoom **/
	@NotNull
	private Float zoom;

	/** Indicates if challenge has been already played **/
	private boolean played;

	/** The image Id **/
	private Integer imageId;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the challengerId
	 */
	public String getChallengerId() {
		return challengerId;
	}

	/**
	 * @param challengerId
	 *            the challengerId to set
	 */
	public void setChallengerId(String challengerId) {
		this.challengerId = challengerId;
	}

	/**
	 * @return the opponentId
	 */
	public String getOpponentId() {
		return opponentId;
	}

	/**
	 * @param opponentId
	 *            the opponentId to set
	 */
	public void setOpponentId(String opponentId) {
		this.opponentId = opponentId;
	}

	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param word
	 *            the word to set
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * @return the lat
	 */
	public Double getLat() {
		return lat;
	}

	/**
	 * @param lat
	 *            the lat to set
	 */
	public void setLat(Double lat) {
		this.lat = lat;
	}

	/**
	 * @return the lng
	 */
	public Double getLng() {
		return lng;
	}

	/**
	 * @param lng
	 *            the lng to set
	 */
	public void setLng(Double lng) {
		this.lng = lng;
	}

	/**
	 * @return the zoom
	 */
	public Float getZoom() {
		return zoom;
	}

	/**
	 * @param zoom
	 *            the zoom to set
	 */
	public void setZoom(Float zoom) {
		this.zoom = zoom;
	}

	/**
	 * @return the played
	 */
	public boolean getPlayed() {
		return played;
	}

	/**
	 * @param played
	 *            the played to set
	 */
	public void setPlayed(boolean played) {
		this.played = played;
	}

	/**
	 * @return the imageId
	 */
	public Integer getImageId() {
		return imageId;
	}

	/**
	 * @param imageId
	 *            the imageId to set
	 */
	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

}
