package com.caffeine.manager;

import java.io.File;

/**
 * This class consists of all the necessary and common non-featural methods that
 * are used in this project
 */
public class Utilities {

	public static String getFilePath(String fileNamePathPrefix, String fileName) {
		String path = "";
		int lastOccurrence = fileNamePathPrefix.lastIndexOf('\\');

		if (lastOccurrence != -1)
			path = fileNamePathPrefix.substring(0, lastOccurrence);

		String finalFileName = path.concat(File.separator).concat(fileName);
		return finalFileName;
	}

}
