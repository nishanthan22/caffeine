package WordNSearch;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
                return completions; // Return empty list if the prefix is not in the Trie
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
    private Map<String, CafeCategory> menu;

    public Cafe() {
        this.menu = new HashMap<>();
    }

    public void addCategory(String name, double price) {
        menu.put(name.toLowerCase(), new CafeCategory(name, price));
    }

    public CafeCategory getCategory(String name) {
        return menu.get(name.toLowerCase());
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
        initializeDataFromCSV("cafe_data.csv"); // Specify your CSV file name
    }

    private void initializeDataFromCSV(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    String cafeName = data[0].trim();
                    String foodOption = data[1].trim();
                    try {
                        double price = Double.parseDouble(data[2].trim());

                        // Add data to word completion trie and inverted index
                        wordCompletionTrie.insert(foodOption);
                        invertedIndex.put(foodOption.toLowerCase(), new CafeCategory(foodOption, price));

                        // Add data to cafes
                        cafes.computeIfAbsent(cafeName, k -> new Cafe()).addCategory(foodOption, price);
                    } catch (NumberFormatException e) {
                        // Handle the case where the price is not a valid double
                        System.err.println("Invalid price format for " + foodOption + " in cafe " + cafeName);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading data from CSV file.");
        }
    }

    public boolean validateInput(String userInput) {
        // Example: Only allow letters and spaces
        String pattern = "^[a-zA-Z ]+$";
        return userInput.matches(pattern);
    }

    public List<String> wordCompletion(String partialWord) {
        return wordCompletionTrie.searchCompletions(partialWord);
    }

    public void displayCategoriesWithPrices(String userInput) {
        String lowerCaseInput = userInput.toLowerCase();

        // Try to find a partial match
        CafeCategory category = invertedIndex.values().stream()
                .filter(cat -> cat.getName().toLowerCase().contains(lowerCaseInput))
                .findFirst()
                .orElse(null);

        if (category != null) {
            System.out.println("Category: " + category.getName() + ", Price: " + category.getPrice());
            findLowestPrice(category.getName());
        } else {
            System.out.println("No results found for " + userInput);
        }
    }

    public void findLowestPrice(String category) {
        double lowestPrice = Double.MAX_VALUE;
        String lowestPriceCafe = "";

        for (Cafe cafe : cafes.values()) {
            CafeCategory cafeCategory = cafe.getCategory(category);
            if (cafeCategory != null && cafeCategory.getPrice() < lowestPrice) {
                lowestPrice = cafeCategory.getPrice();
                lowestPriceCafe = cafeCategory.getName();
            }
        }

        if (!lowestPriceCafe.isEmpty()) {
            System.out.println("Lowest Price: " + lowestPrice + " from " + lowestPriceCafe);
        } else {
            System.out.println("No prices found for " + category);
        }
    }

    public static void main(String[] args) {
        CafePriceAnalysis cafePriceAnalysis = new CafePriceAnalysis();
        Scanner scanner = new Scanner(System.in);

        // Example usage:
        System.out.print("Enter your search term: ");
        String userInput = scanner.nextLine();

        if (userInput != null && !userInput.isEmpty()) {
            if (cafePriceAnalysis.validateInput(userInput)) {
                List<String> completions = cafePriceAnalysis.wordCompletion(userInput);
                if (!completions.isEmpty()) {
                    System.out.println("Did you mean: " + completions);
                }

                cafePriceAnalysis.displayCategoriesWithPrices(userInput);
            } else {
                System.out.println("Invalid input. Please enter letters and spaces only.");
            }
        } else {
            System.out.println("Input cannot be empty.");
        }

        scanner.close();
    }
}
