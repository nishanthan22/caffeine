package com.caffeine.appl;

import java.util.Scanner;

import com.caffeine.manager.Features2;

/**
 * This class is the Main application class of this project
 */
public class Caffeine {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome Everyone, ACC Project Codebase");
		// Features.spellChecker();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a dish: ");
		String dish=sc.nextLine();
		
		Features2.freqCount(dish);
	}

}
