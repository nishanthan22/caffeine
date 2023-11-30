package com.caffeine.appl;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
	class Node {
		String word;
		Node left, right;

		public Node(String item) {
			word = item;
			left = right = null;
		}
	}

	Node root;

	public BinarySearchTree() {
		root = null;
	}

	void insert(String word) {
		root = insertRec(root, word);
	}

	Node insertRec(Node root, String word) {
		if (root == null) {
			root = new Node(word);
			return root;
		}

		if (word.compareTo(root.word) < 0) {
			root.left = insertRec(root.left, word);
		} else if (word.compareTo(root.word) > 0) {
			root.right = insertRec(root.right, word);
		}

		return root;
	}

	public boolean search(String word) {
		return searchRec(root, word);
	}

	boolean searchRec(Node root, String word) {
		if (root == null) {
			return false;
		}

		if (word.compareTo(root.word) == 0) {
			return true;
		}

		if (word.compareTo(root.word) < 0) {
			return searchRec(root.left, word);
		} else {
			return searchRec(root.right, word);
		}
	}

	// Suggestions for a misspelled word
	public List<String> suggestCorrectWord(String word) {
		List<String> suggestions = new ArrayList<>();
		suggestCorrectWordRec(root, word, suggestions);
		suggestions.sort((a, b) -> calculateLevenshteinDistance(a, word) - calculateLevenshteinDistance(b, word));
		return suggestions;
	}

	void suggestCorrectWordRec(Node root, String word, List<String> suggestions) {
		if (root == null) {
			return;
		}

		suggestCorrectWordRec(root.left, word, suggestions);

		// Check if the current word in the dictionary is a suggestion for the
		// misspelled word
		if (isSuggestion(root.word, word)) {

			suggestions.add(root.word);
		}

		suggestCorrectWordRec(root.right, word, suggestions);

		suggestions.sort((a, b) -> calculateLevenshteinDistance(a, word) - calculateLevenshteinDistance(b, word));

	}

	boolean isSuggestion(String dictionaryWord, String inputWord) {
		int distance = calculateLevenshteinDistance(dictionaryWord, inputWord);
		// Adjust the threshold as needed
		return distance > 0 && distance < 2; // Allow words with a Levenshtein distance less than 2
	}

	int calculateLevenshteinDistance(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();

		int[][] dp = new int[m + 1][n + 1];

		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0) {
					dp[i][j] = j;
				} else if (j == 0) {
					dp[i][j] = i;
				} else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
				}
			}
		}

		return dp[m][n];
	}

	public void populateDictionary(String[][] csvData) {
		for (String[] row : csvData) {
			String[] values = row[0].split(" ");
			for (String value : values) {
				if (value.equals("Title")) {
					continue;
				}
				char[] valueArray = value.toCharArray();
				if (!Character.isDigit(valueArray[0])) {
					this.insert(value.toLowerCase());
				}
			}
		}
	}
}
