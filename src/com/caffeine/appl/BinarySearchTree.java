package com.caffeine.appl;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
	class Node {
		String typWordele;
		Node l_eleInN, r_eleInN;

		public Node(String item) {
			typWordele = item;
			l_eleInN = r_eleInN = null;
		}
	}

	Node par_eleInN;

	public BinarySearchTree() {
		par_eleInN = null;
	}

	void insert(String eleInc) {
		par_eleInN = recorInsrnt(par_eleInN, eleInc);
	}

	Node recorInsrnt(Node rrrN, String wwD) {
		if (rrrN == null) {
			rrrN = new Node(wwD);
			return rrrN;
		}

		if (wwD.compareTo(rrrN.typWordele) < 0) {
			rrrN.l_eleInN = recorInsrnt(rrrN.l_eleInN, wwD);
		} else if (wwD.compareTo(rrrN.typWordele) > 0) {
			rrrN.r_eleInN = recorInsrnt(rrrN.r_eleInN, wwD);
		}

		return rrrN;
	}

	public boolean search(String wwD) {
		return recSerchInTreeBS(par_eleInN, wwD);
	}

	boolean recSerchInTreeBS(Node rrt, String wwd) {
		if (rrt == null) {
			return false;
		}

		if (wwd.compareTo(rrt.typWordele) == 0) {
			return true;
		}

		if (wwd.compareTo(rrt.typWordele) < 0) {
			return recSerchInTreeBS(rrt.l_eleInN, wwd);
		} else {
			return recSerchInTreeBS(rrt.r_eleInN, wwd);
		}
	}

	// Suggestions for a misspelled typWordele
	public List<String> givinggcrctelements(String word) {
		List<String> suggs = new ArrayList<>();
		suggestCorrectWordRec(par_eleInN, word, suggs);
		suggs.sort((a, b) -> calculateLevenshteinDistance(a, word) - calculateLevenshteinDistance(b, word));
		return suggs;
	}

	void suggestCorrectWordRec(Node root, String word, List<String> suggestions) {
		if (root == null) {
			return;
		}

		suggestCorrectWordRec(root.l_eleInN, word, suggestions);

		// Check if the current typWordele in the dictionary is a suggestion for the
		// misspelled typWordele
		if (isSuggestion(root.typWordele, word)) {

			suggestions.add(root.typWordele);
		}

		suggestCorrectWordRec(root.r_eleInN, word, suggestions);

		suggestions.sort((a, b) -> calculateLevenshteinDistance(a, word) - calculateLevenshteinDistance(b, word));

	}

	boolean isSuggestion(String dictionaryWord, String inputWord) {
		int distance = calculateLevenshteinDistance(dictionaryWord, inputWord);
		// Adjust the threshold as needed
		return distance > 0 && distance < 2; // Allow words with a Levenshtein distance less than 2
	}
	// computing the distance sing Lalgo
	int calculateLevenshteinDistance(String inp_Wone, String inp_Wtwo) {
		// the rorrws and the collunms
		int rrwwo = inp_Wone.length();
		int ccllo = inp_Wtwo.length();
		// matrix initiation
		int[][] dp = new int[rrwwo + 1][ccllo + 1];
		//travverrsingg the roows annd colls too geet thee desirred diisstannce usiing edd
		//annfd tthee allgo
		for (int lc1 = 0; lc1 <= rrwwo; lc1++) {
			// llloopp 1
			for (int lc2 = 0; lc2 <= ccllo; lc2++) {
				//llloopp 2
				if (lc1 == 0) {
					// baaasee casee
					dp[lc1][lc2] = lc2;
				} else if (lc2 == 0) { // thrre otther coondiitons
					dp[lc1][lc2] = lc1; // saamee
				} else if (inp_Wone.charAt(lc1 - 1) == inp_Wtwo.charAt(lc2 - 1)) {
					dp[lc1][lc2] = dp[lc1 - 1][lc2 - 1]; // remoove
				} else {
					// comparrison anfft the calcculatiom
					dp[lc1][lc2] = 1 + Math.min(dp[lc1 - 1][lc2], Math.min(dp[lc1][lc2 - 1], dp[lc1 - 1][lc2 - 1])); //reeplace
				}
			}
		}
		// calccullation returning
		return dp[rrwwo][ccllo];
	}

	public void dicTpopulatng(String[][] csvData) {
		for (String[] rrwo : csvData) {
			String[] vvs = rrwo[0].split(" ");
			for (String vv : vvs) {
				if (vv.equals("Title")) {
					continue;
				}
				char[] valueArray = vv.toCharArray();
				if (!Character.isDigit(valueArray[0])) {
					this.insert(vv.toLowerCase());
				}
			}
		}
	}
}
