package com.caffeine.manager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.InvalidAttributeValueException;

import com.caffeine.appl.BinarySearchTree;
import com.caffeine.appl.Caffeine;
import com.caffeine.appl.Constants;
import com.opencsv.CSVReader;

public class Features {
	// In this class we can start implementing the features

	/* SPELL CHECKER */
	public static void spellChecker(String dishName) {
		BinarySearchTree dictionary = new BinarySearchTree();
		// Scanner input = new Scanner(System.in);
		List<String[]> csvDataList = new ArrayList<>();

		// Insert words into the dictionary
		String AutoCityPath = Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX,
				Constants.AUTO_CITY_FILE.concat(Constants.CSV_EXTENSION), true);
		csvDataList.addAll(Arrays.asList(Utilities.convertCSVToStringArray(AutoCityPath)));

		String BurgerFactoryPath = Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX,
				Constants.BURGER_FACTORY_FILE.concat(Constants.CSV_EXTENSION), true);
		csvDataList.addAll(Arrays.asList(Utilities.convertCSVToStringArray(BurgerFactoryPath)));

		String WhamburgPath = Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX,
				Constants.WHAMBURG_FILE.concat(Constants.CSV_EXTENSION), true);
		csvDataList.addAll(Arrays.asList(Utilities.convertCSVToStringArray(WhamburgPath)));

		String[][] csvData = csvDataList.toArray(new String[0][]);

		dictionary.dicTpopulatng(csvData);

		dishName = dishName.toLowerCase();
		if (dictionary.search(dishName)) {
			System.out.println("The word is spelled correctly.");
		} else {

			// Get the array of suggested words
			List<String> suggestedWords = dictionary.givinggcrctelements(dishName);

			List<String> inputWordList = new ArrayList<>();
			List<String> suggestionList = new ArrayList<>();

			for (String suggestion : suggestedWords) {
				for (String[] row : csvData) {
					if (row[0].toLowerCase().contains(suggestion)) {
						suggestionList.add(row[0]);
					}
					if (row[0].toLowerCase().contains(dishName)) {
						inputWordList.add(row[0]);
					}
				}
			}
			// Print the suggested words
			if (!inputWordList.isEmpty() || !suggestionList.isEmpty()) {
				System.out.println("*WORD MISSPELLED*\nDid you mean");
				int prntIdx = 1;
				for (List<String> list : List.of(inputWordList, suggestionList))
					for (int i = 0; i < list.size(); i++) {
						System.out.println(prntIdx + ") " + list.get(i));
						prntIdx++;
					}
			} else
				System.out.println("No menus found :(");
		}
	}

	/* SEARCH FREQUENCY */
	public static int retrieveOrStoreFrequency(String word) {
		int searchFrequency = 0;
		boolean valueFound = false;
		List<String[]> csvRecords = new ArrayList<>();

		try {
			csvRecords = Utilities.readFromFile(Constants.WORD_COUNTS_FILE, word);

			for (int row = 0; row < csvRecords.size(); row++) {
				String[] record = csvRecords.get(row);
				for (int column = 0; column < record.length; column++)
					if (record[column].equalsIgnoreCase(word)) {
						searchFrequency = Integer.parseInt(record[++column]);
						valueFound = true;
						Utilities.addCount(csvRecords, word, row, column);
						return searchFrequency;
					}
			}
			if (!valueFound)
				Utilities.addWordToFile(Constants.WORD_COUNTS_FILE, word);
		} catch (Exception e) {
			if (e.getClass().equals(InvalidAttributeValueException.class))
				System.err.println("Proper file name or word is not passed...Please check it out !!");
			else
				System.err.println(Constants.ERROR_MESSAGE + e.getLocalizedMessage());

		}
		return searchFrequency;

	}

	/* VALIDATING THE PRICE FORMAT WITH REGEX */

	public static List<String> validatePrices(String data) {
		List<String> results = new ArrayList<>();
		String[] lines = data.split("\n");

		for (String line : lines) {
			// Use a regex pattern to find the value starting with $ until whitespace or
			// comma
			Pattern pricePattern = Pattern.compile("\\\\$\\\\d{1,2}(\\\\.\\\\d{1,2})?");
			Matcher matcher = pricePattern.matcher(line);

			// Check if a match is found
			while (matcher.find()) {
				String matchedPrice = matcher.group();

				// Trim the $ sign and validate the price
				String price = matchedPrice.substring(1).replaceAll("[^\\d.]", "").trim();

				if (isValidPrice(price)) {
					results.add("Matched Price: " + matchedPrice + ", Valid");
				} else {
					results.add("Matched Price: " + matchedPrice + ", Invalid");
				}
			}
		}
		return results;
	}

	public static boolean isValidPrice(String price) {
		// Regex pattern for a valid price format (dollars and cents)
		String pricePattern = "^\\d+\\.\\d{2}$";

		// Compile the regex pattern
		Pattern pattern = Pattern.compile(pricePattern);

		// Create a matcher object
		Matcher matcher = pattern.matcher(price);

		// Check if the price matches the pattern
		return matcher.matches();
	}

	public static boolean areAllValid(List<String> results) {
		for (String result : results) {
			if (!result.equals("Valid")) {
				return false;
			}
		}
		return true;
	}

	/* FINDING PATTERNS USING REGEX */

	public static Map<String, Double> retrieveDataByPattern(String word) throws Exception {
		Map<String, Double> productData = new HashMap<>();

		List<String> filesList = Arrays.asList(Constants.AUTO_CITY_FILE, Constants.BURGER_FACTORY_FILE,
				Constants.WHAMBURG_FILE);
		String[] line = null;
		boolean isFileRead = false;
		for (String file : filesList) {
			try {
				String filePath = Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX,
						file.concat(Constants.CSV_EXTENSION), true);
				CSVReader reader = new CSVReader(new FileReader(filePath));
				isFileRead = true;
				// Escape user input to handle special regex characters
				String escapedInput = Pattern.quote(word);
				String regexPattern = "\\b(" + escapedInput + "\\w*)\\b";
				Pattern pattern = Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);

				while ((line = reader.readNext()) != null) {
					line.toString().replace(";", "|");
					for (String item : line) {
						Matcher matcher = pattern.matcher(item);
						if (matcher.find() && line.length > 1) {
							String data = line[1];
							if (data.contains("$"))
								data = data.replace("$", "").substring(0, 3).trim();
							productData.put(file + ":" + item, Double.parseDouble(data));
							break;
						}
					}
				}
			} catch (FileNotFoundException e) {
				if(!isFileRead) {
					System.out.println("Hold on...we are fetching the data using Webcrawl");
					Caffeine.performWebCrawl();
					retrieveDataByPattern(word);
				}
			} catch (Exception e) {
				e.getMessage();
			}
		}
		if (!productData.isEmpty())
			Utilities.generateFinalCSVFile(productData);
		return productData;

	}

	public static void displayFrequentlySearched() {
		Map<String, Integer> wordFrequencyMap = new HashMap<>();

		String[][] words = Utilities.convertCSVToStringArray(
				Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX, Constants.WORD_COUNTS_FILE, false));
		for (int i = 1; i < words.length; i++) {
			List<String> firstArrayElements = Arrays.asList(words[i]);
			String countStr = firstArrayElements.get(1).replace("\"", "").trim();
			int count = Integer.parseInt(countStr);
			wordFrequencyMap.put(firstArrayElements.get(0), count);
		}
		// Using a PriorityQueue to store top 5 searched words based on their
		// frequencies
		if (!wordFrequencyMap.isEmpty() && wordFrequencyMap != null) {
			PriorityQueue<Map.Entry<String, Integer>> mostSearchedWords = new PriorityQueue<>(
					Comparator.comparingInt(Map.Entry::getValue));

			for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
				mostSearchedWords.offer(entry);
				if (mostSearchedWords.size() > 6)
					mostSearchedWords.poll();
			}

			List<String> top5List = new ArrayList<>();
			while (!mostSearchedWords.isEmpty()) {
				top5List.add(0, mostSearchedWords.poll().getKey());
			}

			StringBuilder output = new StringBuilder("Frequently searched words: [ ");
			output.append(String.join(", ", top5List));
			output.append(" ]");

			System.out.println(output);
		}

	}

	public static boolean validateInput(String userInput) {
		String pattern = "^[a-zA-Z ]+$";
		return userInput.matches(pattern);
	}
}