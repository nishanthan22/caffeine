package com.caffeine.manager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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
	
	public static String[][] convertCSVToStringArray(String filePath) {
		List<String[]> dataList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				dataList.add(values);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Convert List<String[]> to String[][]
		String[][] dataArray = new String[dataList.size()][];
		dataList.toArray(dataArray);

		return dataArray;
	}
    
    

}
