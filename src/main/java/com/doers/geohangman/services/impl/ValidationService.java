package com.doers.geohangman.services.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.doers.geohangman.constants.Messages;
import com.doers.geohangman.model.AbstractRequest;
import com.doers.geohangman.services.api.IValidationService;

/**
 * 
 * Validation Service implementation for {@link IValidationService}
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
@Service
public class ValidationService implements IValidationService {

	/** Secure Key **/
	@Value("${secure.key}")
	private String secureKey;

	/** Secure Param 1 **/
	@Value("${secure.param1}")
	private String param1;

	/** Secure Param 2 **/
	@Value("${secure.param2}")
	private String param2;

	/** Separator used for codec **/
	private static final String SEPARATOR = ":";
	
	/** Algorithm **/
	private static final String ALGORITHM = "SHA-1";
	
	/** Coded Security Key **/
	private String codedSecurityKey = null;
	
	/** Logger **/
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationService.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.doers.geohangman.services.IValidationService#authenticate(com.doers
	 * .geohangman.model.AbstractRequest)
	 */
	@Override
	public void authenticate(AbstractRequest request) throws SecurityException {
		try {
			String securityKey = getCodedSecurityKey();
			if(!securityKey.equals(request.getSecurityKey())) {
				throw new SecurityException(Messages.AUTHENTICATION_FAILED);
			}
		} catch (NoSuchAlgorithmException e) {
			// If getting securityKey process fail, then program flow continues
			LOGGER.error("Error getting coded security key", e);
		}
		
	}
	
	/**
	 * Returns coded Security Key
	 * 
	 * @return Coded Security Key
	 * @throws NoSuchAlgorithmException
	 */
	private String getCodedSecurityKey() throws NoSuchAlgorithmException {
		if(codedSecurityKey == null) {
			codedSecurityKey = buildCodedSecurityKey();
		}
		return codedSecurityKey;
	}
	
	/**
	 * Builds Security key
	 * 
	 * @return Build security key
	 * @throws NoSuchAlgorithmException
	 */
	private String buildCodedSecurityKey() throws NoSuchAlgorithmException {
		StringBuilder sb = new StringBuilder();
		sb.append(secureKey).append(SEPARATOR).append(param1).append(SEPARATOR)
				.append(param2);
		
		MessageDigest cript = MessageDigest.getInstance(ALGORITHM);
		cript.reset();
		cript.update(sb.toString().getBytes());
		
		String codec = new String(Hex.encodeHex(cript.digest()));
		return codec;
	}

}
