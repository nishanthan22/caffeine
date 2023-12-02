	package com.caffeine.appl;
	
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.InputMismatchException;
	import java.util.List;
	import java.util.Scanner;
	
	import com.caffeine.manager.Features;
	import com.caffeine.manager.PageRanking;
	import com.caffeine.manager.Utilities;
	
	/**
	 * This class is the Main application class of this project
	 */
	public class Caffeine {
	
		public static void main(String[] args) {
	
			String welcomeMsg = " Welcome to Caffeine, A price analysis app";
			Scanner userInput = new Scanner(System.in);
			int userChoice = 0;
			boolean restartSwitch = false;
			String[] options = { "1) Get the best/latest deals", "2) Find best restaurant for the dish",
					"3) Advanced search", "4) Exit" };
			System.out.println("\t");
			// Display welcome message
			Utilities.printPattern(welcomeMsg, Constants.HYPHEN, true);
			// Display options
			for (String option : options)
				Utilities.printPattern(option, Constants.HYPHEN, false);
	
			do {
				restartSwitch = false; // Reset the flag before re-entering the switch
	
				try {
					Utilities.printPatternWithLength(welcomeMsg.length(), Constants.UNDERSCORE);
					System.out.print("Enter the choice: ");
					userChoice = userInput.nextInt();
					userInput.nextLine();
					switch (userChoice) {
					case 1:
						getBestOrLatestDeals();
						break;
					case 2:
						findBestCafe();
						break;
					case 3:
						performAdvancedSearch();
						break;
					case 4:
						System.out.println("Bad to see you leaving :(\nProgram closed...");
						System.exit(1);
						break;
					default:
						System.out.println("The choice you have entered in invalid, Please enter a valid one");
						restartSwitch = true;
						break;
					}
	
				} catch (InputMismatchException e) {
					System.out.print(Constants.VALUE_MISMATCH_MESSAGE);
					userInput.next(); // Clear the invalid input from the scanner buffer
				} catch (Exception e) {
					System.err.println(Constants.UNKNOWN_EXCEPTION + e.getMessage());
					break;
				}
			} while (userChoice != 4 || restartSwitch);
	
			/*
			 * Scanner sc=new Scanner(System.in); System.out.println("Enter a dish: ");
			 * String dish=sc.nextLine();
			 * 
			 * PageRanking.freqCount(dish);
			 */
	
		}
	
		private static void getBestOrLatestDeals() {
	
			Scanner userInput = new Scanner(System.in);
			try {
				// Utilities.clearConsole();
				Utilities.printPattern("Get Best/ Latest deals", Constants.STAR, true);
				String[] options = { "1) Get the latest deals", "2) Explore the existing deals", "3) Go back" };
				// Display options
				for (String option : options)
					Utilities.printPattern(option, Constants.HYPHEN, false);
				Utilities.printPatternWithLength(42, Constants.UNDERSCORE);
	
				System.out.println("Enter the choice: ");
				int userChoice = userInput.nextInt();
				userInput.nextLine();
				switch (userChoice) {
				case 1:
					System.out.println("Performing WebCrawl of the websites..");
					AutoCity autoCity = new AutoCity();
					BurgerFactory burgerFactory = new BurgerFactory();
					Whamburg whamburg = new Whamburg();  // web-crawl, data validation using regex
					getDeals(userInput);
					break;
				case 2:
					getDeals(userInput);
					break;
				case 3:
					System.out.println("Going back to previous menu....");
					break;
				default:
					System.out.println("Chocie invalid");
				}
			} catch (InputMismatchException e) {
				System.out.print(Constants.VALUE_MISMATCH_MESSAGE);
				userInput.next(); // Clear the invalid input from the scanner buffer
			} catch (Exception e) {
				System.err.println(Constants.UNKNOWN_EXCEPTION + e.getMessage());
			}
		}
	
		private static void getDeals(Scanner userInput) {
			System.out.print("Type the dish to search for the deals: ");
			String dish = userInput.nextLine();
			try {
				Features.retrieveDataByPattern(dish); //finding patterns using regex
				Features.retrieveOrStoreFrequency(dish); // search frequency
				CafePriceAnalysis.cafeAnalyser(dish); // inv indexing
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		private static void findBestCafe() {
	
		}
	
		private static void performAdvancedSearch() {
	
		}
	
	}
