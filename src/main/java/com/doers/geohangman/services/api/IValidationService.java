package com.doers.geohangman.services.api;

import com.doers.geohangman.model.restful.AbstractRequest;

/**
 * Interface for Validation Service
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
public interface IValidationService {

	/**
	 * This method authenticate request. If authentication has failed, then an
	 * exception is thrown. Otherwise, program flow continues
	 * 
	 * @param request to be authenticated
	 * @throws SecurityException when authentication has failed
	 */
	void authenticate(AbstractRequest request) throws SecurityException;

}
