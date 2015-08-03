/**
 * 
 */
package com.doers.geohangman.model.restful;

import java.util.Arrays;

/**
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
public class BasicGcmResponse {

	private Integer multicastId;

	private Integer success;

	private Integer failure;

	private Integer canonicalIds;

	private GcmResult[] results;

	/**
	 * @return the multicastId
	 */
	public Integer getMulticastId() {
		return multicastId;
	}

	/**
	 * @param multicastId
	 *            the multicastId to set
	 */
	public void setMulticastId(Integer multicastId) {
		this.multicastId = multicastId;
	}

	/**
	 * @return the success
	 */
	public Integer getSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(Integer success) {
		this.success = success;
	}

	/**
	 * @return the failure
	 */
	public Integer getFailure() {
		return failure;
	}

	/**
	 * @param failure
	 *            the failure to set
	 */
	public void setFailure(Integer failure) {
		this.failure = failure;
	}

	/**
	 * @return the canonicalIds
	 */
	public Integer getCanonicalIds() {
		return canonicalIds;
	}

	/**
	 * @param canonicalIds
	 *            the canonicalIds to set
	 */
	public void setCanonicalIds(Integer canonicalIds) {
		this.canonicalIds = canonicalIds;
	}

	/**
	 * @return the results
	 */
	public GcmResult[] getResults() {
		return results;
	}

	/**
	 * @param results
	 *            the results to set
	 */
	public void setResults(GcmResult[] results) {
		this.results = results;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BasicGcmResponse [multicastId=" + multicastId + ", success="
				+ success + ", failure=" + failure + ", canonicalIds="
				+ canonicalIds + ", results=" + Arrays.toString(results) + "]";
	}

}
