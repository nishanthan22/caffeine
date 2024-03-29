package com.caffeine.manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import com.caffeine.appl.Constants;

//frequency count and page ranking feature

public class PageRanking {

	// frequency count feature

	private static HashSet<DishInfo> AutoCityDishes = new HashSet<>();
	private static HashSet<DishInfo> BurgerFactoryDishes = new HashSet<>();
	private static HashSet<DishInfo> WhamburgDishes = new HashSet<>();

	public static void freqCount(String dish) {

		String AutoCityPath = Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX, "AutoCity.csv", true);
		String BurgerFactoryPath = Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX, "BurgerFactory.csv", true);
		String WhamburgPath = Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX, "Whamburg.csv", true);

		// Process each CSV file and add dish names and descriptions to the respective
		// HashSet
		process(AutoCityPath, AutoCityDishes);
		process(BurgerFactoryPath, BurgerFactoryDishes);
		process(WhamburgPath, WhamburgDishes);

		// Display the frequency count of the dish in each file
		count(dish, AutoCityDishes, BurgerFactoryDishes, WhamburgDishes);
	}

	private static void process(String filePath, HashSet<DishInfo> dishSet) {
		try (BufferedReader rd = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = rd.readLine()) != null) {
				// Split the CSV line into title, price, and description
				String[] parts = line.split(",");
				if (parts.length == 3) {
					// Extract the title (dish name) and description
					String dish = parts[0].trim().toLowerCase(); // Convert to lowercase for consistency
					String description = parts[2].trim().toLowerCase();

					// Create a DishInfo object and add it to the HashSet
					DishInfo dishInfo = new DishInfo(dish, description);
					dishSet.add(dishInfo);
				}
			}
		} catch (IOException e) {
			System.out.println("Sorry! Can not read file");
		}
	}

	@SafeVarargs
	private static void count(String dish, HashSet<DishInfo>... dishSets) {
		// Declaring statement
		
		// Normalize the input dish by converting to lowercase and removing whitespace
		String formattedDish = dish.toLowerCase().replaceAll("\\s", "");

		// Initialize a LinkedList to store the frequency count for each file
		LinkedList<Integer> frequencyCounts = new LinkedList<>();
		boolean isFreshEntry = true;
		if (formattedDish.equals("burger")) {
			for (HashSet<DishInfo> dishSet : dishSets) {
				int fileFrequencyCount = 0;

				// Check each dish in the HashSet using a case-insensitive and
				// whitespace-insensitive comparison
				for (DishInfo dishInSet : dishSet) {
					// Normalize the dish name and description in the HashSet before comparison
					String normalizedDishInSet = dishInSet.getDish().replaceAll("\\s", "").toLowerCase();
					String normalizedDescriptionInSet = dishInSet.getDescription().replaceAll("\\s", "").toLowerCase();

					// Check if the dish is present in the title or description
					if (normalizedDishInSet.contains(formattedDish)
							|| normalizedDescriptionInSet.contains(formattedDish)
							|| normalizedDescriptionInSet.contains("patt")
							|| normalizedDescriptionInSet.contains("stuffed")) {
						fileFrequencyCount++;
					}
				}

				frequencyCounts.add(fileFrequencyCount);
				if (fileFrequencyCount != 0) {
					if (isFreshEntry) {
						Utilities.printStyledPattern(Constants.FREQ_COUNT, "'", true, Constants.ANSI_YELLOW,
								Constants.ANSI_BOLD);
						isFreshEntry = false;
					}
					System.out.println("We found " + fileFrequencyCount + " options at " + getWebsiteName(dishSet)
							+ " for " + dish);

				}
				
			}
			if(!isFreshEntry)
				rankWebsites(frequencyCounts, dishSets);
			else
				System.out.println("No menu found matching your input:(");
		}

		else {
			for (HashSet<DishInfo> dishSet : dishSets) {
				int fileFrequencyCount = 0;

				for (DishInfo dishInSet : dishSet) {

					String normalizedDishInSet = dishInSet.getDish().replaceAll("\\s", "").toLowerCase();
					String normalizedDescriptionInSet = dishInSet.getDescription().replaceAll("\\s", "").toLowerCase();

					if (normalizedDishInSet.contains(formattedDish)
							|| normalizedDescriptionInSet.contains(formattedDish)) {
						fileFrequencyCount++;
					}
				}

				frequencyCounts.add(fileFrequencyCount); 
				if(fileFrequencyCount != 0) {
					if(isFreshEntry) {
						Utilities.printStyledPattern(Constants.FREQ_COUNT, "'", true, Constants.ANSI_YELLOW, Constants.ANSI_BOLD);
						isFreshEntry = false;
					}
					System.out.println(getWebsiteName(dishSet) + ": " + fileFrequencyCount + " results");
				}
				
			}
			if(!isFreshEntry)
				rankWebsites(frequencyCounts, dishSets);
			else
				System.out.println("No menu found matching your input:(");
		}
	}

	// Class to represent dish information (title and description)
	private static class DishInfo {
		private String dish;
		private String description;

		public DishInfo(String dish, String description) {
			this.dish = dish;
			this.description = description;
		}

		public String getDish() {
			return dish;
		}

		public String getDescription() {
			return description;
		}
	}

	private static String getWebsiteName(HashSet<DishInfo> dishSet) {
		// Extract the website name from the HashSet
		if (dishSet.equals(AutoCityDishes)) {
			return "AutoCity Cafe";
		} else if (dishSet.equals(BurgerFactoryDishes)) {
			return "BurgerFactory Cafe";
		} else if (dishSet.equals(WhamburgDishes)) {
			return "Whamburg Cafe";
		} else {
			return "Unknown";
		}
	}

	// page ranking feature

	private static void rankWebsites(LinkedList<Integer> frequencyCounts, HashSet<DishInfo>... dishSets) {
		int rank = 1;
		Utilities.printStyledPattern(Constants.PAGE_RANK, "'", true, Constants.ANSI_GREEN, Constants.ANSI_BOLD);

		// Create a list of WebsiteInfo objects
		List<WebsiteInfo> websites = new ArrayList<>();

		// Convert the frequencyCounts to a list of WebsiteInfo objects
		for (int i = 0; i < frequencyCounts.size(); i++) {
			websites.add(new WebsiteInfo(getWebsiteName(dishSets[i]), frequencyCounts.get(i)));
		}

		// Convert the list to a max-priority queue
		PriorityQueue<WebsiteInfo> priorityQueue = new PriorityQueue<>(websites.size(), Collections.reverseOrder());
		priorityQueue.addAll(websites);

		// Print the ranking
		while (!priorityQueue.isEmpty()) {
			WebsiteInfo websiteInfo = priorityQueue.poll();
			System.out.println(websiteInfo.getName() + " ranked: " + rank + " having " + websiteInfo.getFrequencyCount()
					+ " delectable options available");
			rank++;
		}

	}

	private static class WebsiteInfo implements Comparable<WebsiteInfo> {
		private String name;
		private int frequencyCount;

		public WebsiteInfo(String name, int frequencyCount) {
			this.name = name;
			this.frequencyCount = frequencyCount;
		}

		public String getName() {
			return name;
		}

		public int getFrequencyCount() {
			return frequencyCount;
		}

		@Override
		public int compareTo(WebsiteInfo other) {
			// Compare WebsiteInfo objects based on frequencyCount
			return Integer.compare(this.frequencyCount, other.frequencyCount);
		}
	}

}