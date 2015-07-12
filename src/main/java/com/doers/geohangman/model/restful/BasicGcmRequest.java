package com.doers.geohangman.model.restful;

/**
 * 
 * This class represents a basic GCM Request
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class BasicGcmRequest<T> {

	/** Target device **/
	private String to;

	/** GCM Notification **/
	private Notification notification;

	/** GCM Data **/
	private T data;

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return the notification
	 */
	public Notification getNotification() {
		return notification;
	}

	/**
	 * @param notification
	 *            the notification to set
	 */
	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

}
