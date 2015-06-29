package com.doers.geohangman.model;

/**
 * Create Challenge Image Response that will return Create Challenge Image
 * result as image URL
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
public class CreateChallengeImageResponse extends AbstractResponse {

	/** The image URL **/
	private String imageUrl;

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl
	 *            the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
