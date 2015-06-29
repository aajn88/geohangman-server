package com.doers.geohangman.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * This class offers basic utils for storage process, as save a given file into
 * the server.
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
public final class StorageUtils {
	
	private static final String DATE_FORMAT = "ddMMyyyy-HHmm";

	/** Private Default Constructor **/
	public StorageUtils() {
	}

	/**
	 * This method stores a given file into the server
	 * 
	 * @param fileName
	 *            The file name
	 * @param ext
	 *            The file extension
	 * @param content
	 *            File content
	 * @throws IOException
	 */
	public static final void storeFile(String fileName, String ext,
			byte[] content) throws IOException {
		StringBuilder sb = new StringBuilder(fileName).append(ext);
		FileOutputStream fos = new FileOutputStream(sb.toString());
		fos.write(content);
		fos.close();
	}

	/**
	 * This method deletes a given file into the server by its path. A directory
	 * will not be deleted.
	 * 
	 * @param filePath
	 *            Path of the file to be deleted
	 */
	public static final void deleteFile(String filePath) {
		File fileToDelete = new File(filePath);
		if (fileToDelete.exists() && fileToDelete.isFile()) {
			fileToDelete.delete();
		}
	}
	
	/**
	 * This method generates a FileName with the following format:
	 * 
	 * ddMMyyyy-HHmm-RandomNumber
	 * 
	 * @return
	 */
	public static String generateFileName() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		String date = dateFormat.format(new Date());
		
		StringBuilder sb = new StringBuilder(date).append("-");
		Random ran = new Random();
		sb.append(Math.abs(ran.nextInt()));
		
		return sb.toString();
	}

}
