package wordCompl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RevisedWordCompl {
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
        List<String> variantSuggestions = trie.searchWordsWithPrefix(userEnteredVariant);
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
        while (true) {
            System.out.print("Enter a variant: ");
            String userEnteredVariant = getUserInput().toLowerCase();

            if (userEnteredVariant.equals(EXIT_COMMAND)) {
                return EXIT_COMMAND; // Exit the program
            }

            List<String> variantSuggestions = trie.searchWordsWithPrefix(userEnteredVariant);
            if (variantSuggestions.size() > 1) {
                // Suggest variants if there are multiple matches
                System.out.println("Multiple Variant options found for '" + userEnteredVariant + "'. Did you mean:");
                for (String suggestion : variantSuggestions) {
                    System.out.println("- " + suggestion);
                }
            } else if (variantSuggestions.size() == 1) {
                // Only one suggestion, directly return it
                return variantSuggestions.get(0);
            }

            if (isValidVariant(trie, userEnteredVariant, category)) {
                return userEnteredVariant; // Valid variant selected
            } else {
                System.out.println("Invalid variant. Please choose a valid variant.");
            }
        }
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
        return suggestions.contains(input.toLowerCase());
    }

    // Helper method to check if the input is a valid variant in the Trie for the given category
    private static boolean isValidVariant(Trie trie, String input, String category) {
        // Assuming each variant is prefixed with the category for simplicity
        String fullVariant = category + " " + input;
        List<String> suggestions = trie.searchWordsWithPrefix(fullVariant);
        return suggestions.contains(fullVariant.toLowerCase());
    }

    // Helper method to handle multiple options
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
