package com.doers.geohangman.services.impl;

import java.io.File;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.doers.geohangman.constants.Constants;
import com.doers.geohangman.services.api.IAWSService;

/**
 * 
 * This class implements {@link IAWSService} interface
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
@Service
public class AWSService implements IAWSService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.doers.geohangman.services.api.IAWSService#uploadFile(java.lang.String
	 * , java.lang.String, java.lang.String)
	 */
	@Override
	public String uploadFile(String awsFileName, String bucket, String filePath) {
		return uploadFile(awsFileName, bucket, filePath, Boolean.TRUE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.doers.geohangman.services.api.IAWSService#uploadFile(java.lang.String
	 * , java.lang.String, java.lang.String, java.lang.Boolean)
	 */
	@Override
	public String uploadFile(String awsFileName, String bucket,
			String filePath, Boolean isPublic) {
		AmazonS3 s3Client = getAmazonS3Client();

		PutObjectRequest request = new PutObjectRequest(
				Constants.AWS_GEOHANGMAN_BUCKET, awsFileName,
				new File(filePath));

		if (isPublic) {
			request = request.withCannedAcl(CannedAccessControlList.PublicRead);
		}

		s3Client.putObject(request);

		StringBuilder sb = new StringBuilder(Constants.AWS_URL_PREFIX);
		sb.append(bucket);
		sb.append("/");
		sb.append(awsFileName);

		return sb.toString();
	}

	/**
	 * This method creates the AmazonS3 client
	 * 
	 * @return AmazonS3 client
	 */
	private AmazonS3 getAmazonS3Client() {
		AWSCredentials credentials = new BasicAWSCredentials(
				System.getenv(Constants.AWS_ACCESS_KEY_ID),
				System.getenv(Constants.AWS_SECRET_ACCESS_KEY));
		AmazonS3 s3Client = new AmazonS3Client(credentials);

		return s3Client;
	}

}
