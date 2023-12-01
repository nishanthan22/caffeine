package com.caffeine.appl;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.caffeine.manager.Utilities;

public class AutoCity {
	public static void main(String[] args) {
		// Set the path to your ChromeDriver

		WebDriver driver = new ChromeDriver();

		driver.get(Constants.AUTOCITY_URL);

		String txtFilePath = Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX, "AutoCity.txt", true); // true value is passed to save the file in the parent directory
		String csvFilePath = Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX, "AutoCity.csv", true);

		StringBuilder consoleOutput = new StringBuilder();
		StringBuilder csvOutput = new StringBuilder();
		csvOutput.append("Title,Price,Description\n");

		try {
			Thread.sleep(5000); // waiting for the output to come based on the input and also change the time
								// according to your requirement

			consoleOutput.append("\n---------\n Original Menu \n----------\n");

			List<WebElement> productList = driver.findElements(
					By.cssSelector(".kv-ee-row > .kv-ee-col-12.kv-ee-col-sm-6.kv-ee-py-3.kv-ee-pricelist-item"));

			for (WebElement product : productList) {
				WebElement titleElement = product.findElement(By.cssSelector(".kv-ee-item-header .kv-ee-title"));
				String title = titleElement.getText();

				WebElement priceElement = product.findElement(By.cssSelector(".kv-ee-item-header .kv-ee-price"));
				String price = priceElement.getText();

				WebElement descriptionElement = product
						.findElement(By.cssSelector(".kv-ee-accent-border .kv-ee-body--sm"));
				String description = descriptionElement.getText();
				String descriptionCSV = descriptionElement.getText().replace(",", "|");

				csvOutput.append(String.format("%s,%s,%s\n", title, price, descriptionCSV));

				consoleOutput.append(Constants.TITLE).append(title).append("\n").append(Constants.PRICE).append(price)
						.append("\n").append(Constants.DESCRIPTION).append(description).append("\n")
						.append("----------\n");
			}

			consoleOutput.append("\n---------\n Autocity Speciality \n----------\n");

			List<WebElement> products = driver
					.findElements(By.cssSelector(".section-1647754130899 .kv-ee-pricelist-item"));

			for (WebElement product : products) {
				WebElement titleElement = product.findElement(By.cssSelector(".kv-ee-title"));
				WebElement priceElement = product.findElement(By.cssSelector(".kv-ee-price"));
				WebElement descriptionElement = product
						.findElement(By.cssSelector(".kv-ee-text-content .kv-ee-description.kv-ee-body--sm"));

				// Extract text from title, price, and description elements
				String title = titleElement.getText();
				String price = priceElement.getText();
				String description = descriptionElement.getText();

				String descriptionCSV = descriptionElement.getText().replace(",", "|");
				csvOutput.append(String.format("%s,%s,%s\n", title, price, descriptionCSV));

				// Write the extracted details to the file
				consoleOutput.append(Constants.TITLE).append(title).append("\n").append(Constants.PRICE).append(price)
						.append("\n").append(Constants.DESCRIPTION).append(description).append("\n")
						.append("----------\n");
			}

			consoleOutput.append("\n---------\n Autocity Poutine Menu \n----------\n");

			List<WebElement> poutine = driver
					.findElements(By.cssSelector(".section-1647754347082 .kv-ee-pricelist-item"));

			for (WebElement product : poutine) {
				WebElement titleElement = product.findElement(By.cssSelector(".kv-ee-title"));
				WebElement priceElement = product.findElement(By.cssSelector(".kv-ee-price"));
				WebElement descriptionElement = product
						.findElement(By.cssSelector(".kv-ee-text-content .kv-ee-description.kv-ee-body--sm"));

				// Extract text from title, price, and description elements
				String title = titleElement.getText();
				String price = priceElement.getText();
				String description = descriptionElement.getText();

				String descriptionCSV = descriptionElement.getText().replace(",", "|");
				csvOutput.append(String.format("%s,%s,%s\n", title, price, descriptionCSV));

				// Write the extracted details to the file
				consoleOutput.append(Constants.TITLE).append(title).append("\n").append(Constants.PRICE).append(price)
						.append("\n").append(Constants.DESCRIPTION).append(description).append("\n")
						.append("----------\n");
			}

			consoleOutput.append("\n---------\n Chicken & veg Burger \n----------\n");

			List<WebElement> chicken = driver
					.findElements(By.cssSelector(".section-1647755439469 .kv-ee-pricelist-item"));

			for (WebElement product : chicken) {
				WebElement titleElement = product.findElement(By.cssSelector(".kv-ee-title"));
				WebElement priceElement = product.findElement(By.cssSelector(".kv-ee-price"));
				WebElement descriptionElement = product
						.findElement(By.cssSelector(".kv-ee-text-content .kv-ee-description.kv-ee-body--sm"));

				// Extract text from title, price, and description elements
				String title = titleElement.getText();
				String price = priceElement.getText();
				String description = descriptionElement.getText();

				String descriptionCSV = descriptionElement.getText().replace(",", "|");
				csvOutput.append(String.format("%s,%s,%s\n", title, price, descriptionCSV));

				// Write the extracted details to the file
				consoleOutput.append(Constants.TITLE).append(title).append("\n").append(Constants.PRICE).append(price)
						.append("\n").append(Constants.DESCRIPTION).append(description).append("\n")
						.append("----------\n");
			}

			consoleOutput.append("\n---------\n Philly cheese Steak sub & HotDog,Chicken \n----------\n");

			List<WebElement> hotDog = driver
					.findElements(By.cssSelector(".section-1647755494684 .kv-ee-pricelist-item"));

			for (WebElement product : hotDog) {
				WebElement titleElement = product.findElement(By.cssSelector(".kv-ee-title"));
				WebElement priceElement = product.findElement(By.cssSelector(".kv-ee-price"));
				WebElement descriptionElement = product
						.findElement(By.cssSelector(".kv-ee-text-content .kv-ee-description.kv-ee-body--sm"));

				// Extract text from title, price, and description elements
				String title = titleElement.getText();
				String price = priceElement.getText();
				String description = descriptionElement.getText();

				String descriptionCSV = description.replaceAll("\n", " ").replace(",", "|");
				csvOutput.append(String.format("%s,%s,%s\n", title, price, descriptionCSV));

				// Write the extracted details to the file
				consoleOutput.append(Constants.TITLE).append(title).append("\n").append(Constants.PRICE).append(price)
						.append("\n").append(Constants.DESCRIPTION).append(description).append("\n")
						.append("----------\n");
			}

			// Append Extras & Combo section
			consoleOutput.append("\n---------\n Extras & Combos \n----------\n");

			List<WebElement> combo = driver
					.findElements(By.cssSelector(".section-1663538912113  .kv-ee-pricelist-item"));

			for (WebElement product : combo) {
				WebElement titleElement = product.findElement(By.cssSelector(".kv-ee-title"));
				WebElement priceElement = product.findElement(By.cssSelector(".kv-ee-price"));
				WebElement descriptionElement = product
						.findElement(By.cssSelector(".kv-ee-text-content .kv-ee-description.kv-ee-body--sm"));

				// Extract text from title, price, and description elements
				String title = titleElement.getText();
				String price = priceElement.getText();
				String description = descriptionElement.getText();

				String descriptionCSV = descriptionElement.getText().replace(",", "|");
				csvOutput.append(String.format("%s,%s,%s\n", title, price, descriptionCSV));

				// Write the extracted details to the file
				consoleOutput.append(Constants.TITLE).append(title).append("\n").append(Constants.PRICE).append(price)
						.append("\n").append(Constants.DESCRIPTION).append(description).append("\n")
						.append("----------\n");
			}
//       System.out.println("Data saved to file: " + filePath); // Confirm the data was saved to the file
		} catch (Exception e) {
			e.printStackTrace();
		}

		Utilities.writeConsoleToFile(csvFilePath, csvOutput.toString());

		Utilities.writeConsoleToFile(txtFilePath, consoleOutput.toString());

//	    		
		driver.quit();

	}
}
