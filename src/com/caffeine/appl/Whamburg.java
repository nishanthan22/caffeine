package com.caffeine.appl;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.caffeine.manager.Features;
import com.caffeine.manager.Utilities;

public class Whamburg {
	public Whamburg() {

		WebDriver driver = new ChromeDriver();

		// Navigate to the desired webpage
		driver.get(Constants.WHAMBURG_URL);

		String txtFilePath = Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX, "Whamburg.txt", true); // true value is passed to save the file in the parent directory
		String csvFilePath = Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX, "Whamburg.csv", true);

		StringBuilder consoleOutput = new StringBuilder();
		StringBuilder csvOutput = new StringBuilder();
		csvOutput.append("Title,Price,Description\n");

		consoleOutput.append("BURGER & SANDWICH COMBOS \n--------------------\n");

		WebElement title = driver.findElement(By.cssSelector("[data-id='0ee3640'] .elementor-widget-container"));

		List<WebElement> firstDiv = title
				.findElements(By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='bdbe0f6'] "));
		List<WebElement> secondDiv = title
				.findElements(By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='a471070'] "));
		List<WebElement> thirdDiv = title
				.findElements(By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='46dc225'] "));

//			Map<String, String> dataMap = new HashMap<>();

		for (int i = 0; i < firstDiv.size(); i++) {
			WebElement titleElement = firstDiv.get(i).findElement(By.cssSelector("h3 a"));
			String titleText = titleElement.getText();

			WebElement descElement = secondDiv.get(i).findElement(By.cssSelector("p"));
			String descText = descElement.getText();

			WebElement priceElement = thirdDiv.get(i).findElement(By.cssSelector("span"));
			String priceText = priceElement.getText();

			String descriptionCSV = descElement.getText().replace(",", ";");
			csvOutput.append(String.format("%s,%s,%s\n", titleText, priceText, descriptionCSV));

			consoleOutput.append(Constants.TITLE).append(titleText).append("\n").append(Constants.PRICE)
					.append(priceText).append("\n").append(Constants.DESCRIPTION).append(descText).append("\n")
					.append("----------\n");
		}

		consoleOutput.append("BURGER & SANDWICH \n--------------------\n");

//
		WebElement burgAndSand = driver.findElement(By.cssSelector("[data-id='633d720'] .elementor-widget-container"));

		List<WebElement> bsFirst = burgAndSand
				.findElements(By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='bdbe0f6'] "));
		List<WebElement> bsSecondDiv = burgAndSand
				.findElements(By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='a471070'] "));
		List<WebElement> bsThirdDiv = burgAndSand
				.findElements(By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='46dc225'] "));

		for (int i = 0; i < bsFirst.size(); i++) {
			WebElement bsTitle = bsFirst.get(i).findElement(By.cssSelector("h3 a"));
			String bsTitleText = bsTitle.getText();

			WebElement bsDesc = bsSecondDiv.get(i).findElement(By.cssSelector("p"));
			String bsDescText = bsDesc.getText();

			WebElement bsProductPrice = bsThirdDiv.get(i).findElement(By.cssSelector("span"));
			String bsPriceText = bsProductPrice.getText();

			String descriptionCSV = bsDesc.getText().replace(",", ";");
			csvOutput.append(String.format("%s,%s,%s\n", bsTitleText, bsPriceText, descriptionCSV));

			// Create an array to hold description and price
			consoleOutput.append(Constants.TITLE).append(bsTitleText).append("\n").append(Constants.PRICE)
					.append(bsPriceText).append("\n").append(Constants.DESCRIPTION).append(bsDescText).append("\n")
					.append("----------\n");
		}

		consoleOutput.append("FRIES\n--------------------\n");

		WebElement fries = driver.findElement(By.cssSelector("[data-id='825e047'] .elementor-widget-container"));

		List<WebElement> friesFirst = fries
				.findElements(By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='bdbe0f6'] "));
		List<WebElement> friesSecondDiv = fries
				.findElements(By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='a471070'] "));
		List<WebElement> friesThirdDiv = fries
				.findElements(By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='46dc225'] "));

		for (int i = 0; i < friesFirst.size(); i++) {
			WebElement friesTitle = friesFirst.get(i).findElement(By.cssSelector("h3 a"));
			String friesTitleText = friesTitle.getText();

			WebElement friesDesc = friesSecondDiv.get(i).findElement(By.cssSelector("p"));
			String friesDescText = friesDesc.getText();

			WebElement friesProductPrice = friesThirdDiv.get(i).findElement(By.cssSelector("span"));
			String friesPriceText = friesProductPrice.getText();

			String descriptionCSV = friesDesc.getText().replace(",", ";");
			csvOutput.append(String.format("%s,%s,%s\n", friesTitleText, friesPriceText, descriptionCSV));

			consoleOutput.append(Constants.TITLE).append(friesTitleText).append("\n").append(Constants.PRICE)
					.append(friesPriceText).append("\n").append(Constants.DESCRIPTION).append(friesDescText)
					.append("\n").append("----------\n");

		}

		consoleOutput.append("DESSERT\n--------------------\n");

		WebElement dessert = driver.findElement(By.cssSelector("[data-id='95452fb'] .elementor-widget-container"));

		List<WebElement> dessertFirst = dessert
				.findElements(By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='bdbe0f6'] "));
		List<WebElement> dessertSecondDiv = dessert
				.findElements(By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='a471070'] "));
		List<WebElement> dessertThirdDiv = dessert
				.findElements(By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='46dc225'] "));

		for (int i = 0; i < dessertFirst.size(); i++) {
			WebElement dessertTitle = dessertFirst.get(i).findElement(By.cssSelector("h3 a"));
			String dessertTitleText = dessertTitle.getText();

			WebElement dessertDesc = dessertSecondDiv.get(i).findElement(By.cssSelector("p"));
			String dessertDescText = dessertDesc.getText();

			WebElement dessertProductPrice = dessertThirdDiv.get(i).findElement(By.cssSelector("span"));
			String dessertPriceText = dessertProductPrice.getText();

			String descriptionCSV = dessertDesc.getText().replace(",", ";");
			csvOutput.append(String.format("%s,%s,%s\n", dessertTitleText, dessertPriceText, descriptionCSV));

			// Create an array to hold description and price
			consoleOutput.append(Constants.TITLE).append(dessertTitleText).append("\n").append(Constants.PRICE)
					.append(dessertPriceText).append("\n").append(Constants.DESCRIPTION).append(dessertDescText)
					.append("\n").append("----------\n");
		}

		consoleOutput.append("BEVERAGES\n--------------------\n");

		WebElement beverages = driver.findElement(By.cssSelector("[data-id='03aee40'] .elementor-widget-container"));

		List<WebElement> beveragesFirst = beverages
				.findElements(By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='bdbe0f6'] "));
		List<WebElement> beveragesSecondDiv = beverages
				.findElements(By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='a471070'] "));
		List<WebElement> beveragesThirdDiv = beverages
				.findElements(By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='46dc225'] "));

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

//				String descriptionCSV = beveragesDescText.replace(",", ";");
			csvOutput.append(String.format("%s,%s\n", beveragesTitleText, beveragesPriceText));

			consoleOutput.append(Constants.TITLE).append(beveragesTitleText).append("\n").append(Constants.PRICE)
					.append(beveragesPriceText).append("\n").append(Constants.DESCRIPTION).append(beveragesDescText)
					.append("\n").append("----------\n");
		}

		// Printing the map content

		consoleOutput.append("MILKSHAKES\n--------------------\n");

		WebElement shakes = driver.findElement(By.cssSelector("[data-id='00d6329'] .elementor-widget-container"));

		List<WebElement> shakesFirst = shakes
				.findElements(By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='bdbe0f6'] "));
		List<WebElement> shakesSecondDiv = shakes
				.findElements(By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='a471070'] "));
		List<WebElement> shakesThirdDiv = shakes
				.findElements(By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='46dc225'] "));

		for (int i = 0; i < shakesFirst.size(); i++) {
			WebElement shakesTitle = shakesFirst.get(i).findElement(By.cssSelector("h3 a"));
			String shakesTitleText = shakesTitle.getText();

			WebElement shakesDesc = shakesSecondDiv.get(i).findElement(By.cssSelector("p"));
			String shakesDescText = shakesDesc.getText();

			WebElement shakesProductPrice = shakesThirdDiv.get(i).findElement(By.cssSelector("span"));
			String shakesPriceText = shakesProductPrice.getText();

			String descriptionCSV = shakesDesc.getText().replace(",", ";");
			csvOutput.append(String.format("%s,%s\n", shakesTitleText, shakesPriceText, descriptionCSV));

			consoleOutput.append(Constants.TITLE).append(shakesTitleText).append("\n").append(Constants.PRICE)
					.append(shakesPriceText).append("\n").append(Constants.DESCRIPTION).append(shakesDescText)
					.append("\n").append("----------\n");

		}

		consoleOutput.append("SIDES\n--------------------\n");

		WebElement sides = driver.findElement(By.cssSelector("[data-id='e9082b7'] .elementor-widget-container"));

		List<WebElement> sidesFirst = sides
				.findElements(By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='bdbe0f6'] "));
		List<WebElement> sidesThirdDiv = sides
				.findElements(By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='46dc225'] "));

		for (int i = 0; i < sidesFirst.size(); i++) {
			WebElement sidesTitle = sidesFirst.get(i).findElement(By.cssSelector("h3 a"));
			String sidesTitleText = sidesTitle.getText();

			WebElement sidesProductPrice = sidesThirdDiv.get(i).findElement(By.cssSelector("span"));
			String sidesPriceText = sidesProductPrice.getText();

			csvOutput.append(String.format("%s,%s\n", sidesTitleText, sidesPriceText));

			consoleOutput.append(Constants.TITLE).append(sidesTitleText).append("\n").append(Constants.PRICE)
					.append(sidesPriceText).append("\n").append("----------\n");
		}

		// Printing the map content

		consoleOutput.append("BEER\n--------------------\n");

		WebElement beer = driver.findElement(By.cssSelector("[data-id='6fce461'] .elementor-widget-container"));

		List<WebElement> beerFirst = beer
				.findElements(By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='bdbe0f6'] "));
		List<WebElement> beerThirdDiv = beer
				.findElements(By.cssSelector("div[data-id='793348c'] div[data-id='57c4447'] div[data-id='46dc225'] "));

		for (int i = 0; i < beerFirst.size(); i++) {
			WebElement beerTitle = beerFirst.get(i).findElement(By.cssSelector("h3 a"));
			String beerTitleText = beerTitle.getText();

			WebElement beerProductPrice = beerThirdDiv.get(i).findElement(By.cssSelector("span"));
			String beerPriceText = beerProductPrice.getText();

			csvOutput.append(String.format("%s,%s\n", beerTitleText, beerPriceText));

			consoleOutput.append(Constants.TITLE).append(beerTitleText).append("\n").append(Constants.PRICE)
					.append(beerPriceText).append("\n").append("----------\n");
		}

		driver.quit();
		
		List<String> consoleResult = Features.validatePrices(consoleOutput.toString());

		for (String result : consoleResult) {
			System.out.println(result);
		}
		
		if (Features.areAllValid(consoleResult)) {
            System.out.println("All values are valid. in text");
        } else {
            System.out.println("Some values are invalid. text");
        }
		
		List<String> csvResult = Features.validatePrices(csvOutput.toString());

		for (String result : csvResult) {
			System.out.println(result);
		}
		
		if (Features.areAllValid(csvResult)) {
            System.out.println("All values are valid. in CSV");
        } else {
            System.out.println("Some values are invalid. CSV");
        }

		Utilities.writeConsoleToFile(csvFilePath, csvOutput.toString());

		Utilities.writeConsoleToFile(txtFilePath, consoleOutput.toString());
	}
}
