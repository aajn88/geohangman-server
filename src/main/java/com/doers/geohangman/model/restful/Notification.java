package com.doers.geohangman.model.restful;

/**
 * 
 * This class represents notification part of GCM Request
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class Notification {

	/** Notification title **/
	private String title;

	/** Notification text **/
	private String text;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

}
