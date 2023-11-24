package com.caffeine.appl;


import java.io.BufferedWriter;
import java.io.FileWriter;
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

		String filePath = Utilities.getFilePath(Constants.FILE_NAME_PATH_PREFIX, "AutoCity.txt", true);
	   
	   try {
           Thread.sleep(5000); // waiting for the output to come based on the input and also change the time according to your requirement
      
	   
	   
		BufferedWriter bw = new BufferedWriter(new FileWriter(filePath)) ;

			bw.write("----------\n");
            bw.write("Original Menu\n");
            bw.write("----------\n");

            List<WebElement> productList = driver.findElements(By.cssSelector(".kv-ee-row > .kv-ee-col-12.kv-ee-col-sm-6.kv-ee-py-3.kv-ee-pricelist-item"));

            for (WebElement product : productList) {
                WebElement titleElement = product.findElement(By.cssSelector(".kv-ee-item-header .kv-ee-title"));
                String title = titleElement.getText();

                WebElement priceElement = product.findElement(By.cssSelector(".kv-ee-item-header .kv-ee-price"));
                String price = priceElement.getText();

                WebElement descriptionElement = product.findElement(By.cssSelector(".kv-ee-accent-border .kv-ee-body--sm"));
                String description = descriptionElement.getText();

                bw.write(Constants.TITLE + title + "\n");
                bw.write(Constants.PRICE + price + "\n");
                bw.write(Constants.DESCRIPTION + description + "\n");
                bw.write("----------\n");
            }

            bw.write("----------\n");
            bw.write("Autocity Speciality\n");
            bw.write("----------\n");

            List<WebElement> products = driver.findElements(By.cssSelector(".section-1647754130899 .kv-ee-pricelist-item"));

            for (WebElement product : products) {
                WebElement titleElement = product.findElement(By.cssSelector(".kv-ee-title"));
                WebElement priceElement = product.findElement(By.cssSelector(".kv-ee-price"));
                WebElement descriptionElement = product.findElement(By.cssSelector(".kv-ee-text-content .kv-ee-description.kv-ee-body--sm"));

                // Extract text from title, price, and description elements
                String title = titleElement.getText();
                String price = priceElement.getText();
                String description = descriptionElement.getText();

                // Write the extracted details to the file
                bw.write(Constants.TITLE + title + "\n");
                bw.write(Constants.PRICE + price + "\n");
                bw.write(Constants.DESCRIPTION + description + "\n");
                bw.write("-------------------------------------------------\n");
            }
       
            bw.write("----------\n");
            bw.write("AutoCity Menu\n");
            bw.write("----------\n");

            List<WebElement> poutine = driver.findElements(By.cssSelector(".section-1647754347082 .kv-ee-pricelist-item"));

            for (WebElement product : poutine) {
                WebElement titleElement = product.findElement(By.cssSelector(".kv-ee-title"));
                WebElement priceElement = product.findElement(By.cssSelector(".kv-ee-price"));
                WebElement descriptionElement = product.findElement(By.cssSelector(".kv-ee-text-content .kv-ee-description.kv-ee-body--sm"));

                // Extract text from title, price, and description elements
                String title = titleElement.getText();
                String price = priceElement.getText();
                String description = descriptionElement.getText();

                // Write the extracted details to the file
                bw.write(Constants.TITLE + title + "\n");
                bw.write(Constants.PRICE + price + "\n");
                bw.write(Constants.DESCRIPTION + description + "\n");
                bw.write("-------------------------------------------------\n");
            }
       
            bw.write("----------\n");
            bw.write("Chicken & veg burgers\n");
            bw.write("----------\n");

            List<WebElement> chicken = driver.findElements(By.cssSelector(".section-1647755439469 .kv-ee-pricelist-item"));

            for (WebElement product : chicken) {
                WebElement titleElement = product.findElement(By.cssSelector(".kv-ee-title"));
                WebElement priceElement = product.findElement(By.cssSelector(".kv-ee-price"));
                WebElement descriptionElement = product.findElement(By.cssSelector(".kv-ee-text-content .kv-ee-description.kv-ee-body--sm"));

                // Extract text from title, price, and description elements
                String title = titleElement.getText();
                String price = priceElement.getText();
                String description = descriptionElement.getText();

                // Write the extracted details to the file
                bw.write(Constants.TITLE + title + "\n");
                bw.write(Constants.PRICE + price + "\n");
                bw.write(Constants.DESCRIPTION + description + "\n");
                bw.write("-------------------------------------------------\n");
            }

            bw.write("----------\n");
            bw.write("Philly cheese Steak sub & HotDog,Chicken\n");
            bw.write("----------\n");

            List<WebElement> hotDog = driver.findElements(By.cssSelector(".section-1647755494684 .kv-ee-pricelist-item"));

            for (WebElement product : hotDog) {
                WebElement titleElement = product.findElement(By.cssSelector(".kv-ee-title"));
                WebElement priceElement = product.findElement(By.cssSelector(".kv-ee-price"));
                WebElement descriptionElement = product.findElement(By.cssSelector(".kv-ee-text-content .kv-ee-description.kv-ee-body--sm"));

                // Extract text from title, price, and description elements
                String title = titleElement.getText();
                String price = priceElement.getText();
                String description = descriptionElement.getText();

                // Write the extracted details to the file
                bw.write(Constants.TITLE + title + "\n");
                bw.write(Constants.PRICE + price + "\n");
                bw.write(Constants.DESCRIPTION + description + "\n");
                bw.write("-------------------------------------------------\n");
            }

            // Append Extras & Combo section
            bw.write("----------\n");
            bw.write("Extras & Combo\n");
            bw.write("----------\n");

            List<WebElement> combo = driver.findElements(By.cssSelector(".section-1663538912113  .kv-ee-pricelist-item"));

            for (WebElement product : combo) {
                WebElement titleElement = product.findElement(By.cssSelector(".kv-ee-title"));
                WebElement priceElement = product.findElement(By.cssSelector(".kv-ee-price"));
                WebElement descriptionElement = product.findElement(By.cssSelector(".kv-ee-text-content .kv-ee-description.kv-ee-body--sm"));

                // Extract text from title, price, and description elements
                String title = titleElement.getText();
                String price = priceElement.getText();
                String description = descriptionElement.getText();

                // Write the extracted details to the file
                bw.write(Constants.TITLE + title + "\n");
                bw.write(Constants.PRICE + price + "\n");
                bw.write(Constants.DESCRIPTION + description + "\n");
                bw.write("-------------------------------------------------\n");
            }
       System.out.println("Data saved to file: " + filePath); // Confirm the data was saved to the file
		} catch (Exception e) {
		    e.printStackTrace();
		}

//	    		
	   driver.quit();

	            

	       
		// Close the browser
	}
}


