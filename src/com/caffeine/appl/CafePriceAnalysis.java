package com.caffeine.appl;

import java.io.BufferedReader;
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
import com.opencsv.exceptions.CsvException;
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
        menu.computeIfAbsent(name.toLowerCase(), k -> new ArrayList<>())
            .add(new CafeCategory(name, price));
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
        initializeDataFromCSV(Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX, Constants.COMPARABLE_FILE.concat(Constants.CSV_EXTENSION), true)); 
    }

    private void initializeDataFromCSV(String fileName) {
        try (CSVReader reader = new CSVReader(new FileReader("D:\\UWindsor\\SEM 1_Fall23\\ACC_Section3\\EclipseStuff\\PROJECT\\codebase\\caffeine-app\\comparable_file.csv"))) {
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

        List<CafeCategory> categories = invertedIndex.values().stream()
                .filter(cat -> cat.getName().toLowerCase().contains(lowerCaseInput))
                .collect(Collectors.toList());

        if (!categories.isEmpty()) {
            System.out.println("\nDid you mean: " + categories.stream().map(CafeCategory::getName).collect(Collectors.toList())+"\n");
            categories.forEach(category -> {
                findLowestPrice(category.getName());
            });
        } else {
            System.out.println("No results found for " + userInput);
        }
    }


    public void findLowestPrice(String dishName) {
        List<String> cafesWithCategory = new ArrayList<>();

        cafes.forEach((cafeName, cafe) -> {
            cafe.getCategories(dishName).forEach(cafeCategory -> {
                System.out.println("Category: " + cafeCategory.getName() +
                        ", Price: " + cafeCategory.getPrice() +
                        " from " + cafeName);
                cafesWithCategory.add(cafeName);
            });
        });


        if (!cafesWithCategory.isEmpty()) {
            double lowestPrice = cafesWithCategory.stream()
                    .map(cafeKey -> cafes.get(cafeKey).getCategories(dishName))
                    .flatMap(List::stream)
                    .mapToDouble(CafeCategory::getPrice)
                    .min()
                    .orElse(Double.MAX_VALUE);

            List<String> cafesWithLowestPrice = cafesWithCategory.stream()
                    .filter(cafeKey ->
                            cafes.get(cafeKey).getCategories(dishName).stream()
                                    .anyMatch(category -> category.getPrice() == lowestPrice)
                    )
                    .toList();

            System.out.println("\nHere's the Best Deal for you for " + dishName + ": " + lowestPrice + " from " + cafesWithLowestPrice + "\n");
        } else {
            System.out.println("No prices found for " + dishName);
        }

    }

    public void processUserInput(String userInput) {
        if (userInput != null && !userInput.isEmpty()) {
            if (Features.validateInput(userInput)) {
                List<String> completions = wordCompletion(userInput);
                if (!completions.isEmpty()) {
                    System.out.println("\nDid you mean: " + completions);
                }
                displayCategoriesWithPrices(userInput);
            } else {
                System.out.println("Invalid input. Please enter letters and spaces only.");
            }
        } else {
            System.out.println("Input cannot be empty.");
        }
    }
}