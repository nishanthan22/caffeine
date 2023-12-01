package com.caffeine.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.management.InvalidAttributeValueException;

import com.caffeine.appl.Constants;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

/**
 * This class consists of all the necessary and common non-featural methods that
 * are used in this project
 */
public class Utilities {

	public static String getFilePath(String fileNamePathPrefix, String fileName, boolean inPreviousFolder) {
		String path = fileNamePathPrefix;
		if (inPreviousFolder) {
			int lastOccurrence = fileNamePathPrefix.lastIndexOf('\\');

			if (lastOccurrence != -1)
				path = fileNamePathPrefix.substring(0, lastOccurrence);
		}

		String finalFileName = path.concat(File.separator).concat(fileName);
		return finalFileName;
	}

	public static List<String[]> readFromFile(String fileName, String word) throws Exception {
		List<String[]> csvRecords = new ArrayList<>();
		if (!word.isBlank() && !fileName.isBlank() && fileName.endsWith(Constants.CSV_EXTENSION)) {
			String absolutePath = getFilePath(Constants.FILE_NAME_PATH_PREFIX, fileName, false);

			try {
				CSVReader csvFileReader = new CSVReader(new FileReader(absolutePath));
				csvRecords = csvFileReader.readAll();

			} catch (Exception exc) {
				System.err.println("Error reading the CSV file: " + exc.getMessage());
			}
			return csvRecords;

		} else
			throw new InvalidAttributeValueException();
	}

	public static void addWordToFile(String wordCountsFile, String word) {
		List<String[]> fileRecord = new ArrayList<>();
		String[] recordContent = { word, "0" };
		fileRecord.add(recordContent);
		try {
			String fileName = getFilePath(Constants.FILE_NAME_PATH_PREFIX, wordCountsFile, false);
			FileWriter file = new FileWriter(fileName, true);
			CSVWriter csvWriter = new CSVWriter(file);
			csvWriter.writeAll(fileRecord);
			csvWriter.close();
		} catch (Exception e) {
			System.err.println("Error in writing the content in CSV file: " + e.getMessage());
		}

	}

	public static void addCount(List<String[]> csvRecords, String word, int row, int column) {
		try {
			String fileName = getFilePath(Constants.FILE_NAME_PATH_PREFIX, Constants.WORD_COUNTS_FILE, false);
			CSVWriter writer = new CSVWriter(new FileWriter(fileName));
			for (int i = 0; i < csvRecords.size(); i++) {
				String[] record = csvRecords.get(i);
				if (i == row && record.length > column) {
					int value = Integer.parseInt(record[column]);
					record[column] = String.valueOf(++value);
				}
				writer.writeNext(record);
			}
			writer.close();

		} catch (Exception exc) {
			System.err.println("Error reading the CSV file: " + exc.getMessage());
		}

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

	public static <K, V extends Comparable<? super V>> Map<K, V> sortData(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());

		// Sorting the list by values using a Comparator
		list.sort(Map.Entry.comparingByValue());

		// Storing the sorted entries in a LinkedHashMap
		Map<K, V> sortedByValue = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : list)
			sortedByValue.put(entry.getKey(), (V) ("$" + entry.getValue()));

		return sortedByValue;
	}

}
