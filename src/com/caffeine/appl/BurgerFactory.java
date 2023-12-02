package com.caffeine.appl;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.caffeine.manager.Features;
import com.caffeine.manager.Utilities;

public class BurgerFactory {
	public static void main(String[] args) {

		String txtFilePath = Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX, "BurgerFactory.txt", true); // true
																												// value
																												// is
																												// passed
																												// to
																												// save
																												// the
																												// file
																												// in
																												// the
																												// parent
																												// directory
		String csvFilePath = Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX, "BurgerFactory.csv", true);

		// Create a new instance of the Chrome driver
		WebDriver driver = new ChromeDriver();

		driver.get(Constants.BURGERFACTORY_URL);

		StringBuilder consoleOutput = new StringBuilder();
		StringBuilder csvOutput = new StringBuilder();
		csvOutput.append("Title,Price,Description\n");

		// Extracting and printing the h2 text content
		WebElement h2Element = driver.findElement(By.cssSelector("h2.elementor-heading-title.elementor-size-default"));
		String h2Text = h2Element.getText();

		consoleOutput.append(h2Text + "\n-------------\n");

		// Find the desired section
		WebElement sectionElement = driver.findElement(By.cssSelector("section[data-id='6fbf9391']"));

		// Find all columns within the section
		List<WebElement> columns = sectionElement.findElements(By.cssSelector(".elementor-column"));

		// Iterate over each column
		for (WebElement column : columns) {
			// Find the <h3> and <p> elements within the column
			WebElement burgerNameElement = column.findElement(By.cssSelector("h3.elementor-heading-title"));
			WebElement burgerDetailsElement = column.findElement(By.cssSelector("p.elementor-heading-title"));

			// Extracting information
			String title = burgerNameElement.getText();
			String description = burgerDetailsElement.getText();
			String[] price = description.split("\\$");

			String descriptionCSV = price[0].replaceAll("\n", " ").replace(",", ";");
			csvOutput.append(String.format("%s,%s,%s\n", title, "$" + price[1], descriptionCSV));
			// Writing to the file
			consoleOutput.append(Constants.TITLE).append(title).append("\n").append(Constants.PRICE)
					.append("$" + price[1]).append("\n").append(Constants.DESCRIPTION).append(price[0]).append("\n")
					.append("----------\n");
		}

		WebElement nestedH2Element = driver.findElement(By.cssSelector("div[data-id='57c69a8e']"));
		String data = nestedH2Element.getText();

		consoleOutput.append(data + "\n-------------\n");

		List<WebElement> additionalSections1 = driver.findElements(By.cssSelector("section[data-id='3ea899a3']"));

		for (WebElement section : additionalSections1) {

			List<WebElement> headingElements = section.findElements(By.cssSelector("h3.elementor-heading-title"));
			List<WebElement> priceElements = section.findElements(By.cssSelector("p.elementor-heading-title"));

			// Assuming headingElements and priceElements have the same size
			for (int i = 0; i < headingElements.size(); i++) {
				String headingText = headingElements.get(i).getText();
				String priceText = priceElements.get(i).getText();
				String[] price = priceText.split("(\\$)");

				String descriptionCSV = price[0].replaceAll("\n", " ").replace(",", ";");
				csvOutput.append(String.format("%s,%s,%s\n", headingText, "$" + price[1], descriptionCSV));

				consoleOutput.append(Constants.TITLE).append(headingText).append("\n").append(Constants.PRICE)
						.append("$" + price[1]).append("\n").append(Constants.DESCRIPTION).append(price[0]).append("\n")
						.append("----------\n");
			}

		}

		List<WebElement> additionalSections2 = driver.findElements(By.cssSelector("section[data-id='5f3b92ad']"));

		for (WebElement section : additionalSections2) {

			List<WebElement> headingElements = section.findElements(By.cssSelector("h3.elementor-heading-title"));
			List<WebElement> priceElements = section.findElements(By.cssSelector("p.elementor-heading-title"));

			for (int i = 0; i < headingElements.size(); i++) {
				String headingText = headingElements.get(i).getText();
				String priceText = priceElements.get(i).getText();
				String[] price = priceText.split("\\$");

				String descriptionCSV = price[0].replaceAll("\n", " ").replace(",", ";");
				csvOutput.append(String.format("%s,%s,%s\n", headingText, "$" + price[1], descriptionCSV));

				consoleOutput.append(Constants.TITLE).append(headingText).append("\n").append(Constants.PRICE)
						.append("$" + price[1]).append("\n").append(Constants.DESCRIPTION).append(price[0]).append("\n")
						.append("----------\n");
			}

		}


		List<WebElement> additionalSections3 = driver.findElements(By.cssSelector("section[data-id='17a6956c']"));

		for (WebElement section : additionalSections3) {

			List<WebElement> headingElements = section.findElements(By.cssSelector("h3.elementor-heading-title"));
			List<WebElement> priceElements = section.findElements(By.cssSelector("p.elementor-heading-title"));

			for (int i = 0; i < headingElements.size(); i++) {
				String headingText = headingElements.get(i).getText();
				String priceText = priceElements.get(i).getText();
				String[] price = priceText.split("\\$");

				String descriptionCSV = price[0].replaceAll("\n", " ").replace(",", ";");
				csvOutput.append(String.format("%s,%s,%s\n", headingText, "$" + price[1], descriptionCSV));

				consoleOutput.append(Constants.TITLE).append(headingText).append("\n").append(Constants.PRICE)
						.append("$" + price[1]).append("\n").append(Constants.DESCRIPTION).append(price[0]).append("\n")
						.append("----------\n");

			}

		}

		WebElement FunnelCakeSection = driver.findElement(By.cssSelector("section[data-id='6b1a6db3']"));

		WebElement FunnelCakeDiv = FunnelCakeSection
				.findElement(By.cssSelector(".elementor-section .elementor-heading-title"));
		String heading = FunnelCakeDiv.getText();
		consoleOutput.append(Constants.TITLE).append(heading).append("\n");

		WebElement FunnelCakeDescriptionDiv = FunnelCakeSection.findElement(By.tagName("p"));
		String description = FunnelCakeDescriptionDiv.getText();

		consoleOutput.append(Constants.DESCRIPTION).append(description).append("\n");

		WebElement FunnelCakePriceDiv = FunnelCakeSection.findElement(By.cssSelector("[data-id='13641a41'] h2"));
		String price = FunnelCakePriceDiv.getText();
		consoleOutput.append(Constants.PRICE).append(price).append("\n").append("---------------------").append("\n");

		String descriptionCSV = description.replace(",", ";");
		csvOutput.append(String.format("%s,%s,%s\n", heading, price, descriptionCSV));

		WebElement SidesDiv = driver.findElement(By.cssSelector("div[data-id='7ff01fb1']"));
		String headingSide = SidesDiv.getText();
		consoleOutput.append(headingSide + "\n-------------\n");

		WebElement SidesSec = driver.findElement(By.cssSelector("section[data-id='5d257a2d']"));

		List<WebElement> h3Elements = SidesSec.findElements(
				By.cssSelector("section.elementor-section h3.elementor-heading-title.elementor-size-default"));
		List<WebElement> pElements = SidesSec.findElements(
				By.cssSelector("section.elementor-section p.elementor-heading-title.elementor-size-default"));

		if (h3Elements.size() == pElements.size()) {
			for (int i = 0; i < h3Elements.size(); i++) {
				WebElement h3Element = h3Elements.get(i);
				WebElement pElement = pElements.get(i);

				String h3Text = h3Element.getText();
				String pText = pElement.getText();
				String[] Sideprice = pText.split("\\$");

				String SidesDescriptionCSV = Sideprice[0].replaceAll("\n", " ").replace(",", ";");
				csvOutput.append(String.format("%s,%s,%s\n", h3Text, "$" + Sideprice[1], SidesDescriptionCSV));

				consoleOutput.append(Constants.TITLE).append(h3Text).append("\n").append(Constants.PRICE)
						.append("$" + Sideprice[1]).append("\n").append(Constants.DESCRIPTION).append(Sideprice[0])
						.append("\n").append("----------\n");

			}
		} else {
			System.out.println("error");
		}
//
		WebElement MilkshakesDiv = driver.findElement(By.cssSelector("div[data-id='bfb945d']"));
		String milkshakeTitle = MilkshakesDiv.findElement(By.tagName("h2")).getText();
		consoleOutput.append(milkshakeTitle + "\n-------------\n");

		WebElement MilkshakesPriceDiv = driver.findElement(By.cssSelector("div[data-id='213c5c53']"));
		String milkshakePrice = MilkshakesPriceDiv.findElement(By.tagName("h2")).getText();
//		System.out.print("-----------------------"+consoleOutput);
		consoleOutput.append("Price for any flavored milkshake: ").append(milkshakePrice).append("\n");

		List<WebElement> MilkshakesFlavoursDiv = driver.findElements(By.cssSelector("section[data-id='23411114'] h3"));
		consoleOutput.append("Flavors available for milkshakes are:").append("\n");

//			String SidesDescriptionCSV = price.replace(",", ";");
		csvOutput.append(String.format("%s,%s\n", milkshakeTitle, milkshakePrice));

		for (WebElement flavour : MilkshakesFlavoursDiv) {
			String flavoursOfMilkShake = flavour.getText();
			consoleOutput.append(flavoursOfMilkShake).append("\n");

		}

//		System.out.print(csvOutput);

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

		// Close the browser
		driver.quit();

	}
}
