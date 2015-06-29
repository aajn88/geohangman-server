package com.doers.geohangman.services.api;

/**
 * 
 * This Interface contains all exposed services for AWS
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
public interface IAWSService {

	/**
	 * This method uploads a given file to AWS bucket. By default, the uploaded
	 * file will be public
	 * 
	 * @param awsFileName
	 *            The file's name in AWS bucket
	 * @param bucket
	 *            The bucket's name where file will be stored
	 * @param filePath
	 *            Local file path
	 * 
	 * @return File URL in AWS
	 */
	String uploadFile(String awsFileName, String bucket, String filePath);

	/**
	 * This method uploads a given file to AWS bucket. This method defines
	 * whether the file is public or not
	 * 
	 * @param awsFileName
	 *            The file's name in AWS bucket
	 * @param bucket
	 *            The bucket's name where file will be stored
	 * @param filePath
	 *            Local file path
	 * @param isPublic
	 *            Defines if the uploaded file will be public or not
	 * 
	 * @return File URL in AWS
	 */
	String uploadFile(String awsFileName, String bucket, String filePath,
			Boolean isPublic);

}
