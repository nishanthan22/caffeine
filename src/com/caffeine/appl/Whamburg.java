package com.caffeine.appl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.caffeine.manager.Utilities;

public class Whamburg {
	public static void main(String[] args) {

		String filePath = Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX, "Whamburg.txt", true);

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {

			// Heading: BURGER & SANDWICH COMBOS
//	            bw.write("Heading: BURGER & SANDWICH COMBOS");
//	            bw.newLine();

			WebDriver driver = new ChromeDriver();

			// Navigate to the desired webpage
			driver.get(Constants.WHAMBURG_URL);

//	    		System.out.println("Heading : BURGER & SANDWICH COMBOS");
			bw.write("Heading: BURGER & SANDWICH COMBOS");
			bw.newLine();
			bw.newLine();

			WebElement title = driver.findElement(By.cssSelector("[data-id='0ee3640'] .elementor-widget-container"));

			List<WebElement> firstDiv = title.findElements(
					By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='bdbe0f6'] "));
			List<WebElement> secondDiv = title.findElements(
					By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='a471070'] "));
			List<WebElement> thirdDiv = title.findElements(
					By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='46dc225'] "));

			Map<String, String> dataMap = new HashMap<>();

			for (int i = 0; i < firstDiv.size(); i++) {
				WebElement titleElement = firstDiv.get(i).findElement(By.cssSelector("h3 a"));
				String titleText = titleElement.getText();

				WebElement descElement = secondDiv.get(i).findElement(By.cssSelector("p"));
				String descText = descElement.getText();

				WebElement priceElement = thirdDiv.get(i).findElement(By.cssSelector("span"));
				String priceText = priceElement.getText();

				// Combine description and price into a single string
				String descAndPrice = descText + ",  \nPrice: " + priceText;

				// Add to the map with title as key and description & price as value
				dataMap.put(titleText, descAndPrice);
			}

			// ... Your existing code that fetches data and prints to console ...
			// Example: Print to console and write to file within the loop

			for (Map.Entry<String, String> entry : dataMap.entrySet()) {
				bw.write(Constants.TITLE + entry.getKey());
				bw.newLine();
				bw.write(Constants.DESCRIPTION + entry.getValue());
				bw.newLine();
				bw.write("----------------------------");
				bw.newLine();
			}

			bw.write("Heading : BURGER & SANDWICH ");
			bw.newLine();
			bw.newLine();

			WebElement burgAndSand = driver
					.findElement(By.cssSelector("[data-id='633d720'] .elementor-widget-container"));

			List<WebElement> bsFirst = burgAndSand.findElements(
					By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='bdbe0f6'] "));
			List<WebElement> bsSecondDiv = burgAndSand.findElements(
					By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='a471070'] "));
			List<WebElement> bsThirdDiv = burgAndSand.findElements(
					By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='46dc225'] "));

			Map<String, String[]> bsDataMap = new HashMap<>();

			for (int i = 0; i < bsFirst.size(); i++) {
				WebElement bsTitle = bsFirst.get(i).findElement(By.cssSelector("h3 a"));
				String bsTitleText = bsTitle.getText();

				WebElement bsDesc = bsSecondDiv.get(i).findElement(By.cssSelector("p"));
				String bsDescText = bsDesc.getText();

				WebElement bsProductPrice = bsThirdDiv.get(i).findElement(By.cssSelector("span"));
				String bsPriceText = bsProductPrice.getText();

				// Create an array to hold description and price
				String[] bsDescAndPrice = { bsDescText, bsPriceText };

				// Add to the map with title as key and description & price array as value
				bsDataMap.put(bsTitleText, bsDescAndPrice);
			}

			// Printing the map content
			for (Map.Entry<String, String[]> entry : bsDataMap.entrySet()) {
				bw.write(Constants.TITLE + entry.getKey());
				bw.newLine();
				bw.write(Constants.DESCRIPTION + entry.getValue()[0]);
				bw.newLine();
				bw.write(Constants.PRICE + entry.getValue()[1]);
				bw.newLine();
				bw.write("----------------------------");
				bw.newLine();
			}

			bw.write("Heading : FRIES ");
			bw.newLine();
			bw.newLine();

			WebElement fries = driver.findElement(By.cssSelector("[data-id='825e047'] .elementor-widget-container"));

			List<WebElement> friesFirst = fries.findElements(
					By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='bdbe0f6'] "));
			List<WebElement> friesSecondDiv = fries.findElements(
					By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='a471070'] "));
			List<WebElement> friesThirdDiv = fries.findElements(
					By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='46dc225'] "));

			Map<String, String[]> friesDataMap = new HashMap<>();

			for (int i = 0; i < friesFirst.size(); i++) {
				WebElement friesTitle = friesFirst.get(i).findElement(By.cssSelector("h3 a"));
				String friesTitleText = friesTitle.getText();

				WebElement friesDesc = friesSecondDiv.get(i).findElement(By.cssSelector("p"));
				String friesDescText = friesDesc.getText();

				WebElement friesProductPrice = friesThirdDiv.get(i).findElement(By.cssSelector("span"));
				String friesPriceText = friesProductPrice.getText();

				// Create an array to hold description and price
				String[] friesDescAndPrice = { friesDescText, friesPriceText };

				// Add to the map with title as key and description & price array as value
				friesDataMap.put(friesTitleText, friesDescAndPrice);
			}

			// Printing the map content
			for (Map.Entry<String, String[]> entry : friesDataMap.entrySet()) {
				bw.write(Constants.TITLE + entry.getKey());
				bw.newLine();
				bw.write(Constants.DESCRIPTION + entry.getValue()[0]);
				bw.newLine();
				bw.write(Constants.PRICE + entry.getValue()[1]);
				bw.newLine();
				bw.write("----------------------------");
				bw.newLine();
//	    		    System.out.println("----------------------------");
			}

			bw.write("Heading : DESSERT ");
			bw.newLine();
			bw.newLine();

			WebElement dessert = driver.findElement(By.cssSelector("[data-id='95452fb'] .elementor-widget-container"));

			List<WebElement> dessertFirst = dessert.findElements(
					By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='bdbe0f6'] "));
			List<WebElement> dessertSecondDiv = dessert.findElements(
					By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='a471070'] "));
			List<WebElement> dessertThirdDiv = dessert.findElements(
					By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='46dc225'] "));

			Map<String, String[]> dessertDataMap = new HashMap<>();

			for (int i = 0; i < dessertFirst.size(); i++) {
				WebElement dessertTitle = dessertFirst.get(i).findElement(By.cssSelector("h3 a"));
				String dessertTitleText = dessertTitle.getText();

				WebElement dessertDesc = dessertSecondDiv.get(i).findElement(By.cssSelector("p"));
				String dessertDescText = dessertDesc.getText();

				WebElement dessertProductPrice = dessertThirdDiv.get(i).findElement(By.cssSelector("span"));
				String dessertPriceText = dessertProductPrice.getText();

				// Create an array to hold description and price
				String[] dessertDescAndPrice = { dessertDescText, dessertPriceText };

				// Add to the map with title as key and description & price array as value
				dessertDataMap.put(dessertTitleText, dessertDescAndPrice);
			}

			// Printing the map content
			for (Map.Entry<String, String[]> entry : dessertDataMap.entrySet()) {
				bw.write(Constants.TITLE + entry.getKey());
				bw.newLine();
				bw.write(Constants.DESCRIPTION + entry.getValue()[0]);
				bw.newLine();
				bw.write(Constants.PRICE + entry.getValue()[1]);
				bw.newLine();
				bw.write("----------------------------");
				bw.newLine();
			}

			bw.write("Heading : BEVERAGES ");
			bw.newLine();
			bw.newLine();

			WebElement beverages = driver
					.findElement(By.cssSelector("[data-id='03aee40'] .elementor-widget-container"));

			List<WebElement> beveragesFirst = beverages.findElements(
					By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='bdbe0f6'] "));
			List<WebElement> beveragesSecondDiv = beverages.findElements(
					By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='a471070'] "));
			List<WebElement> beveragesThirdDiv = beverages.findElements(
					By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='46dc225'] "));

			Map<String, String[]> beveragesDataMap = new HashMap<>();

			for (int i = 0; i < beveragesFirst.size(); i++) {
				WebElement beveragesTitle = beveragesFirst.get(i).findElement(By.cssSelector("h3 a"));
				String beveragesTitleText = beveragesTitle.getText();

				// Retrieve description if exists, otherwise assign null
				WebElement beveragesDesc = null;
				String beveragesDescText = null;
				if (beveragesSecondDiv.size() > i) {
					beveragesDesc = beveragesSecondDiv.get(i).findElement(By.cssSelector("p"));
					beveragesDescText = beveragesDesc.getText();
				}

				WebElement beveragesProductPrice = beveragesThirdDiv.get(i).findElement(By.cssSelector("span"));
				String beveragesPriceText = beveragesProductPrice.getText();

				// Create an array to hold description and price
				String[] beveragesDescAndPrice = { beveragesDescText, beveragesPriceText };

				// Add to the map with title as key and description & price array as value
				beveragesDataMap.put(beveragesTitleText, beveragesDescAndPrice);
			}

			// Printing the map content
			for (Map.Entry<String, String[]> entry : beveragesDataMap.entrySet()) {
				bw.write(Constants.TITLE + entry.getKey());
				bw.newLine();
				bw.write(Constants.DESCRIPTION + entry.getValue()[0]);
				bw.newLine();
				bw.write(Constants.PRICE + entry.getValue()[1]);
				bw.newLine();
				bw.write("----------------------------");
				bw.newLine();
			}

			bw.write("Heading : MILKSHAKES ");
			bw.newLine();
			bw.newLine();

			WebElement shakes = driver.findElement(By.cssSelector("[data-id='00d6329'] .elementor-widget-container"));

			List<WebElement> shakesFirst = shakes.findElements(
					By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='bdbe0f6'] "));
			List<WebElement> shakesSecondDiv = shakes.findElements(
					By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='a471070'] "));
			List<WebElement> shakesThirdDiv = shakes.findElements(
					By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='46dc225'] "));

			Map<String, String[]> shakesDataMap = new HashMap<>();

			for (int i = 0; i < shakesFirst.size(); i++) {
				WebElement shakesTitle = shakesFirst.get(i).findElement(By.cssSelector("h3 a"));
				String shakesTitleText = shakesTitle.getText();

				WebElement shakesDesc = shakesSecondDiv.get(i).findElement(By.cssSelector("p"));
				String shakesDescText = shakesDesc.getText();

				WebElement shakesProductPrice = shakesThirdDiv.get(i).findElement(By.cssSelector("span"));
				String shakesPriceText = shakesProductPrice.getText();

				// Create an array to hold description and price
				String[] shakesDescAndPrice = { shakesDescText, shakesPriceText };

				// Add to the map with title as key and description & price array as value
				shakesDataMap.put(shakesTitleText, shakesDescAndPrice);
			}

			// Printing the map content
			for (Map.Entry<String, String[]> entry : shakesDataMap.entrySet()) {
				bw.write(Constants.TITLE + entry.getKey());
				bw.newLine();
				bw.write(Constants.DESCRIPTION + entry.getValue()[0]);
				bw.newLine();
				bw.write(Constants.PRICE + entry.getValue()[1]);
				bw.newLine();
				bw.write("----------------------------");
				bw.newLine();
			}

			bw.write("Heading : SIDES ");
			bw.newLine();
			bw.newLine();

			WebElement sides = driver.findElement(By.cssSelector("[data-id='e9082b7'] .elementor-widget-container"));

			List<WebElement> sidesFirst = sides.findElements(
					By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='bdbe0f6'] "));
			List<WebElement> sidesThirdDiv = sides.findElements(
					By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='46dc225'] "));

			Map<String, String> sidesDataMap = new HashMap<>();

			for (int i = 0; i < sidesFirst.size(); i++) {
				WebElement sidesTitle = sidesFirst.get(i).findElement(By.cssSelector("h3 a"));
				String sidesTitleText = sidesTitle.getText();

				WebElement sidesProductPrice = sidesThirdDiv.get(i).findElement(By.cssSelector("span"));
				String sidesPriceText = sidesProductPrice.getText();

				// Add to the map with title as key and price as value
				sidesDataMap.put(sidesTitleText, sidesPriceText);
			}

			// Printing the map content
			for (Map.Entry<String, String> entry : sidesDataMap.entrySet()) {
				bw.write(Constants.TITLE + entry.getKey());
				bw.newLine();
				bw.write(Constants.PRICE + entry.getValue());
				bw.newLine();
				bw.write("----------------------------");
				bw.newLine();

			}

			bw.write("Heading : BEER ");
			bw.newLine();
			bw.newLine();

			WebElement beer = driver.findElement(By.cssSelector("[data-id='6fce461'] .elementor-widget-container"));

			List<WebElement> beerFirst = beer.findElements(
					By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='bdbe0f6'] "));
			List<WebElement> beerThirdDiv = beer.findElements(
					By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='46dc225'] "));

			Map<String, String> beerDataMap = new HashMap<>();

			for (int i = 0; i < beerFirst.size(); i++) {
				WebElement beerTitle = beerFirst.get(i).findElement(By.cssSelector("h3 a"));
				String beerTitleText = beerTitle.getText();

				WebElement beerProductPrice = beerThirdDiv.get(i).findElement(By.cssSelector("span"));
				String beerPriceText = beerProductPrice.getText();

				// Add to the map with title as key and price as value
				beerDataMap.put(beerTitleText, beerPriceText);
			}

			// Printing the map content
			for (Map.Entry<String, String> entry : beerDataMap.entrySet()) {
				bw.write(Constants.TITLE + entry.getKey());
				bw.newLine();
				bw.write(Constants.PRICE + entry.getValue());
				bw.newLine();
				bw.write("----------------------------");
				bw.newLine();
			}

			System.out.println("Data saved to file: " + filePath); 
			driver.quit();

		} catch (Exception e) {
			e.printStackTrace();

			// Close the browser
		}
	}
}
