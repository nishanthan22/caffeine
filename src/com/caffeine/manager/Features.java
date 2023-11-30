package com.caffeine.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.management.InvalidAttributeValueException;

import com.caffeine.appl.BinarySearchTree;
import com.caffeine.appl.Constants;

public class Features {
	// In this class we can start implementing the features

	/* SPELL CHECKER */
	public static void spellChecker() {
		BinarySearchTree dictionary = new BinarySearchTree();
		Scanner input = new Scanner(System.in);
		List<String[]> csvDataList = new ArrayList<>();

		// Insert words into the dictionary
		String AutoCityPath = Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX, "AutoCity.csv", true);
		csvDataList.addAll(Arrays.asList(Utilities.convertCSVToStringArray(AutoCityPath)));

		String BurgerFactoryPath = Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX, "BurgerFactory.csv", true);
		csvDataList.addAll(Arrays.asList(Utilities.convertCSVToStringArray(BurgerFactoryPath)));

		String WhamburgPath = Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX, "Whamburg.csv", true);
		csvDataList.addAll(Arrays.asList(Utilities.convertCSVToStringArray(WhamburgPath)));

		String[][] csvData = csvDataList.toArray(new String[0][]);

		System.out.println("Enter the item to be searched : ");
		String inputWord = input.nextLine();

		dictionary.populateDictionary(csvData);

		inputWord = inputWord.toLowerCase();
		if (dictionary.search(inputWord)) {
			System.out.println("The word is spelled correctly.");
		} else {
			System.out.println("The word is misspelled.");

			// Get the array of suggested words
			List<String> suggestedWords = dictionary.suggestCorrectWord(inputWord);

			List<String> inputWordList = new ArrayList<>();
			List<String> suggestionList = new ArrayList<>();

			for (String suggestion : suggestedWords) {
				for (String[] row : csvData) {
					if (row[0].toLowerCase().contains(suggestion)) {
						suggestionList.add(row[0]);
					}
					if (row[0].toLowerCase().contains(inputWord)) {
						inputWordList.add(row[0]);
					}
				}
			}
			// Print the suggested words
			if (!inputWordList.isEmpty() || !suggestionList.isEmpty()) {
				System.out.println("Suggestions:" + "Did you mean".toUpperCase());
				int prntIdx = 1;
				for (List<String> list : List.of(inputWordList, suggestionList)) 
					for (int i = 0; i < list.size(); i++) {
						System.out.println(prntIdx + ") " + list.get(i));
						prntIdx++;
					}  
			} else
				System.out.println("No words found matching the word you have entered");

		}
		input.close();
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
}