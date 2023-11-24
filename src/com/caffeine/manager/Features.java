package com.caffeine.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


import com.caffeine.appl.BinarySearchTree;
import com.caffeine.appl.Constants;



public class Features{
	// In this class we can start implementing the feautes
	
	public static void spellChecker() {
        BinarySearchTree dictionary = new BinarySearchTree();
        Scanner input = new Scanner(System.in);
        List<String[]> csvDataList = new ArrayList<>();

        // Insert words into the dictionary
        String AutoCityPath = Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX, "AutoCity.csv");
        csvDataList.addAll(Arrays.asList(Utilities.convertCSVToStringArray(AutoCityPath)));

        String BurgerFactoryPath = Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX, "BurgerFactory.csv");
        csvDataList.addAll(Arrays.asList(Utilities.convertCSVToStringArray(BurgerFactoryPath)));

        String WhamburgPath = Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX, "Whamburg.csv");;
        csvDataList.addAll(Arrays.asList(Utilities.convertCSVToStringArray(WhamburgPath)));

        String[][] csvData = csvDataList.toArray(new String[0][]);

        System.out.println("Enter the item to be searched : ");
        String inputWord = input.nextLine();

        dictionary.populateDictionary(csvData);

        inputWord = inputWord.toLowerCase();
        if (dictionary.search(inputWord)) {
			System.out.println("The word is spelled correctly.");
		} else {
			System.out.println("The word is misspelled. \n Suggestions:");

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
			for (String word : inputWordList) {
				System.out.println("Did you mean " + word);
			}
			
			for (String word : suggestionList) {
				System.out.println("Did you mean " + word);
			}

        }
        input.close();
    }
	
}