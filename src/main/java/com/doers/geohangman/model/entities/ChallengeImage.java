package com.doers.geohangman.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.doers.geohangman.constants.ModelConstants;

/**
 * 
 * This is the challenge image
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
@Entity
@Table(name = ModelConstants.CHALLENGES_IMAGES_TABLE, schema = ModelConstants.DEFAULT_SCHEMA)
public class ChallengeImage {

	/** The image Id **/
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;

	/** The associated challenge **/
	@NotNull
	private Integer challengeId;

	/** The image **/
	@NotNull
	private byte[] image;

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
	 * @return the challengeId
	 */
	public Integer getChallengeId() {
		return challengeId;
	}

	/**
	 * @param challengeId
	 *            the challengeId to set
	 */
	public void setChallengeId(Integer challengeId) {
		this.challengeId = challengeId;
	}

	/**
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}

}
