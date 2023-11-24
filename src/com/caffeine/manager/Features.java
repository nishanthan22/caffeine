package com.caffeine.manager;

import java.util.ArrayList;
import java.util.List;

import javax.management.InvalidAttributeValueException;

import com.caffeine.appl.Constants;

public class Features {
	public static void main(String[] args) {
		retrieveOrStoreFrequency("finalTest");
	}
	// Search frequency
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