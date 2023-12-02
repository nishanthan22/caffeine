package com.caffeine.appl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.caffeine.manager.Utilities;

public class CafeList {
    private Map<String, CafeData> cafes;

    public class CafeData {
        private Map<String, Double> menu;

        public CafeData() {
            this.menu = new HashMap<>();
        }

        public void addCategory(String name, double price) {
            menu.put(name.toLowerCase(), price);
        }

        public Map<String, Double> getMenu() {
            return menu;
        }
    }

    public CafeList() {
        this.cafes = new HashMap<>();
        initializeDataFromCSV(Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX, Constants.CAFE_DATA_FILE, true));
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

                        // Add data to cafes
                        cafes.computeIfAbsent(cafeName, k -> new CafeData()).addCategory(foodOption, price);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid price format for " + foodOption + " in cafe " + cafeName);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading data from CSV file.");
        }
    }

    public void exploreCafe(String userInput) {
        String lowerCaseInput = userInput.toLowerCase();

        for (String cafeName : cafes.keySet()) {
            if (cafeName.toLowerCase().contains(lowerCaseInput)) {
                CafeData cafeData = cafes.get(cafeName);
                System.out.println("Menu for " + cafeName + ":");
                cafeData.getMenu().forEach((category, price) ->
                        System.out.println("Category: " + category + ", Price: " + price));
                return;
            }
        }

        System.out.println("Cafe not found: " + userInput);
    }

    // New method to explore cafe with a provided name
    public void exploreCafeWithName(String cafeName) {
        if (cafeName != null && !cafeName.isEmpty()) {
            exploreCafe(cafeName);
        } else {
            System.out.println("Cafe name cannot be empty.");
        }
    }
}
