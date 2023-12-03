package com.caffeine.appl;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.caffeine.manager.Features;
import com.caffeine.manager.Utilities;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

class TrieNode {
	Map<Character, TrieNode> children;
	boolean isEndOfWord;

	TrieNode() {
		this.children = new HashMap<>();
		this.isEndOfWord = false;
	}
}

class Trie {
	TrieNode root;

	Trie() {
		this.root = new TrieNode();
	}

	void insert(String word) {
		TrieNode node = root;
		for (char ch : word.toCharArray()) {
			node.children.putIfAbsent(ch, new TrieNode());
			node = node.children.get(ch);
		}
		node.isEndOfWord = true;
	}

	List<String> searchCompletions(String partialWord) {
		List<String> completions = new ArrayList<>();
		TrieNode node = root;

		for (char ch : partialWord.toCharArray()) {
			if (node.children.containsKey(ch)) {
				node = node.children.get(ch);
			} else {
				return completions;
			}
		}

		collectCompletions(node, partialWord, completions);
		return completions;
	}

	private void collectCompletions(TrieNode node, String prefix, List<String> completions) {
		if (node.isEndOfWord) {
			completions.add(prefix);
		}

		for (char ch : node.children.keySet()) {
			collectCompletions(node.children.get(ch), prefix + ch, completions);
		}
	}
}

class CafeCategory {
	private String name;
	private double price;

	public CafeCategory(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
}

class Cafe {
	private Map<String, List<CafeCategory>> menu;

	public Cafe() {
		this.menu = new HashMap<>();
	}

	public void addCategory(String name, double price) {
		menu.computeIfAbsent(name.toLowerCase(), k -> new ArrayList<>()).add(new CafeCategory(name, price));
	}

	public List<CafeCategory> getCategories(String name) {
		return menu.getOrDefault(name.toLowerCase(), Collections.emptyList());
	}
}

public class CafePriceAnalysis {
	private Trie wordCompletionTrie;
	private Map<String, CafeCategory> invertedIndex;
	private Map<String, Cafe> cafes;

	public CafePriceAnalysis() {
		wordCompletionTrie = new Trie();
		invertedIndex = new HashMap<>();
		cafes = new HashMap<>();
		initializeDataFromCSV(Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX,
				Constants.COMPARABLE_FILE.concat(Constants.CSV_EXTENSION), true));
	}

	private void initializeDataFromCSV(String fileName) {
		try {
			CSVReader reader = new CSVReader(new FileReader(fileName));
			String[] line;
			while ((line = reader.readNext()) != null) {
				String cafeName = line[0].trim();
				String foodOption = line[1].trim();
				try {
					double price = Double.parseDouble(line[2].trim());

					wordCompletionTrie.insert(foodOption);
					invertedIndex.put(foodOption.toLowerCase(), new CafeCategory(foodOption, price));

					cafes.computeIfAbsent(cafeName, k -> new Cafe()).addCategory(foodOption, price);
				} catch (NumberFormatException e) {
					System.err.println("Invalid price format for " + foodOption + " in cafe " + cafeName);
				}

			}
		} catch (IOException | CsvValidationException e) {
			e.printStackTrace();
		}
	}

	public List<String> wordCompletion(String partialWord) {
		return wordCompletionTrie.searchCompletions(partialWord.toLowerCase());
	}

	public void displayCategoriesWithPrices(String userInput) {
		String lowerCaseInput = userInput.toLowerCase();

		List<CafeCategory> matchingCategories = invertedIndex.values().stream()
				.filter(cat -> cat.getName().toLowerCase().contains(lowerCaseInput)).collect(Collectors.toList());

		if (!matchingCategories.isEmpty()) {
			System.out.println("\nDid you mean: "
					+ matchingCategories.stream().map(CafeCategory::getName).collect(Collectors.toList()) + "\n");

			matchingCategories.forEach(cat -> {
				System.out.println(cat.getName() + " priced " + cat.getPrice() + " from "
						+ findCafeNameForCategory(cat.getName()));
			});

			double lowestPrice = matchingCategories.stream().mapToDouble(CafeCategory::getPrice).min()
					.orElse(Double.MAX_VALUE);

			List<String> cafesWithLowestPrice = matchingCategories.stream().filter(cat -> cat.getPrice() == lowestPrice)
					.map(cat -> {
						String cafeName = findCafeNameForCategory(cat.getName());
						return "Here's the Best Deal for you: " + cat.getName() + " priced " + lowestPrice + " from "
								+ cafeName;
					}).collect(Collectors.toList());

			if (!cafesWithLowestPrice.isEmpty()) {
				//cafesWithLowestPrice.forEach(System.out::println);
				for(String msg: cafesWithLowestPrice)
					Utilities.printStyledPattern(msg, "'", true, Constants.ANSI_PURPLE, Constants.ANSI_BOLD);
			} else {
				System.out.println("No prices found for " + userInput);
			}
		} else {
			Features.spellChecker(userInput);
		}
	}

	private String findCafeNameForCategory(String categoryName) {
		return cafes.entrySet().stream()
				.filter(entry -> entry.getValue().getCategories(categoryName).stream()
						.anyMatch(category -> category.getName().equals(categoryName)))
				.map(Map.Entry::getKey).findFirst().orElse("Unknown Cafe");
	}

	public void processUserInput(String userInput) {
		if (userInput != null && !userInput.isEmpty()) {
			if (Features.validateInput(userInput)) {
				displayCategoriesWithPrices(userInput);
			} else {
				System.out.println("Invalid input. Please enter letters and spaces only.");
			}
		} else {
			System.out.println("Input cannot be empty.");
		}
	}
}