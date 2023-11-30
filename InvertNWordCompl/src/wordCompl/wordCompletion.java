package wordCompl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // Insert a word into the trie
    void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current.children.putIfAbsent(c, new TrieNode());
            current = current.children.get(c);
        }
        current.isEndOfWord = true;
    }

    // Search for words with a given prefix
    List<String> searchWordsWithPrefix(String prefix) {
        List<String> results = new ArrayList<>();
        TrieNode current = root;

        // Traverse the trie to the node representing the given prefix
        for (char c : prefix.toCharArray()) {
            if (current.children.containsKey(c)) {
                current = current.children.get(c);
            } else {
                return results; // Prefix not found, return an empty list
            }
        }

        // Collect all words with the given prefix
        collectWordsWithPrefix(current, prefix, results);
        return results;
    }

    // Recursively collect words with a given prefix
    private void collectWordsWithPrefix(TrieNode node, String currentPrefix, List<String> results) {
        if (node.isEndOfWord) {
            results.add(currentPrefix);
        }

        for (char nextChar : node.children.keySet()) {
            collectWordsWithPrefix(node.children.get(nextChar), currentPrefix + nextChar, results);
        }
    }
}

public class wordCompletion {
    private static final String EXIT_COMMAND = "exit";

    public static void main(String[] args) {
        // Initialize the trie
        Trie trie = new Trie();

        // Read data from a text file and insert into the trie
        try (BufferedReader reader = new BufferedReader(new FileReader("input_text1.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming each line in the file contains a word
                trie.insert(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Scenario 1: Selecting a Category
        String chosenCategory = selectCategory(trie);

        // Get word completion suggestions based on the chosen category
        List<String> categorySuggestions = trie.searchWordsWithPrefix(chosenCategory);
        handleSuggestions("Category", chosenCategory, categorySuggestions);

        // Additional Scenario: Changing Category
        String changedCategory = selectCategory(trie);
        if (!changedCategory.equals(chosenCategory)) {
            // User changed the category, update the suggestions
            chosenCategory = changedCategory;
            categorySuggestions = trie.searchWordsWithPrefix(chosenCategory);
            handleSuggestions("Category", chosenCategory, categorySuggestions);
        }

        // Scenario 2: Selecting a Variant
        String userEnteredVariant = selectVariant(trie, chosenCategory);

        // Get word completion suggestions based on the user-entered variant
        List<String> variantSuggestions = trie.searchWordsWithPrefix(chosenCategory + " " + userEnteredVariant);
        handleSuggestions("Variant", userEnteredVariant, variantSuggestions);
    }

    // Helper method to select a category
    private static String selectCategory(Trie trie) {
        while (true) {
            System.out.print("Enter a category (burger, pizza, coffee): ");
            String chosenCategory = getUserInput().toLowerCase();

            if (chosenCategory.equals(EXIT_COMMAND)) {
                return EXIT_COMMAND; // Exit the program
            }

            List<String> categorySuggestions = trie.searchWordsWithPrefix(chosenCategory);
            if (categorySuggestions.size() > 1) {
                // Suggest categories if there are multiple matches
                System.out.println("Multiple Category options found for '" + chosenCategory + "'. Did you mean:");
                for (String suggestion : categorySuggestions) {
                    System.out.println("- " + suggestion);
                }
            } else if (categorySuggestions.size() == 1) {
                // Only one suggestion, directly return it
                return categorySuggestions.get(0);
            }

            if (isValidCategory(trie, chosenCategory)) {
                return chosenCategory; // Valid category selected
            } else {
                System.out.println("Invalid category. Please choose a valid category.");
            }
        }
    }

 // Helper method to select a variant
    private static String selectVariant(Trie trie, String category) {
        String userEnteredVariant;
        boolean isFirstAttempt = true;

        do {
            System.out.print("Enter a variant: ");
            userEnteredVariant = getUserInput();

            if (!userEnteredVariant.trim().isEmpty() || isFirstAttempt) {
                // Display suggestions when the entered variant is not empty or it's the first attempt
                List<String> variantSuggestions = trie.searchWordsWithPrefix(category + " " + userEnteredVariant);
                handleSuggestions("Variant", userEnteredVariant, variantSuggestions);
            }

            isFirstAttempt = false;
        } while (!isValidVariant(trie, userEnteredVariant, category) && !userEnteredVariant.equalsIgnoreCase(EXIT_COMMAND));

        return userEnteredVariant;
    }

    // Helper method to get user input
    private static String getUserInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    // Helper method to check if the input is a valid category in the Trie
    private static boolean isValidCategory(Trie trie, String input) {
        List<String> suggestions = trie.searchWordsWithPrefix(input);
        return suggestions.stream().anyMatch(suggestion -> suggestion.equals(input));
    }

    // Helper method to check if the input is a valid variant in the Trie based on the selected category
    private static boolean isValidVariant(Trie trie, String input, String category) {
        List<String> suggestions = trie.searchWordsWithPrefix(category + " " + input);
        return suggestions.stream().anyMatch(suggestion -> suggestion.equals(category + " " + input));
    }

    // Helper method to display suggestions and handle multiple options
    private static void handleSuggestions(String category, String userInput, List<String> suggestions) {
        if (suggestions.isEmpty()) {
            System.out.println("No matching " + category + " found for '" + userInput + "'.");
        } else if (suggestions.size() == 1) {
            System.out.println(category + " suggestion: " + suggestions.get(0));
        } else {
            System.out.println("Multiple " + category + " options found for '" + userInput + "'. Did you mean:");
            for (String suggestion : suggestions) {
                System.out.println("- " + suggestion);
            }
        }
    }
}
