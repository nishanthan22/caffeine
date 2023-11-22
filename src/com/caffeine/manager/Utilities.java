package com.caffeine.manager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
	
	
	public static void writeConsoleToFile(String filePath, String consoleOutput) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(consoleOutput);
            System.out.println("Console output saved to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    

}
