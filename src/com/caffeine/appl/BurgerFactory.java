package com.caffeine.appl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BurgerFactory {
	public static void main(String[] args) {
	      System.setProperty("webdriver.chrome.driver", "D:\\my\\study software\\chromeDriver\\chromedriver-win64\\chromedriver.exe");

	      
			String filePath = "D:\\MAC WINDSOR\\Olena\\caffine-webCrawl\\Caffine\\BurgerFactory.txt";

		// Create a new instance of the Chrome driver
		WebDriver driver = new ChromeDriver();

		// Open the URL
		String url = "https://burgerfactory.ca/our-menu/";
		driver.get(url);

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
		    // Extracting and printing the h2 text content
		    WebElement h2Element = driver.findElement(By.cssSelector("h2.elementor-heading-title.elementor-size-default"));
		    String h2Text = h2Element.getText();
		    bw.write(h2Text);
		    bw.newLine();
		    bw.write("--------------------------------------------------------------------------------");
		    bw.newLine();

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
		        String burgerName = "Burger Name: " + burgerNameElement.getText();
		        String burgerDetails = "Burger Details: " + burgerDetailsElement.getText();

		        // Writing to the file
		        bw.write(burgerName);
		        bw.newLine();
		        bw.write(burgerDetails);
		        bw.newLine();
		        bw.write("--------------");
		        bw.newLine();
		    }

		    WebElement nestedH2Element = driver.findElement(By.cssSelector("div[data-id='57c69a8e']"));

		    // Write the text content of the nested <h2> element to the file
		    bw.write(nestedH2Element.getText());
		    bw.newLine();
		    bw.write("--------------------------------------------------------------------------------");
		    bw.newLine();

		    List<WebElement> additionalSections1 = driver.findElements(By.cssSelector("section[data-id='3ea899a3']"));

		    List<Map<String, String>> menuItems1 = new ArrayList<>();

		    for (WebElement section : additionalSections1) {
		        Map<String, String> menuItem = new HashMap<>();

		        List<WebElement> headingElements = section.findElements(By.cssSelector("h3.elementor-heading-title"));
		        List<WebElement> priceElements = section.findElements(By.cssSelector("p.elementor-heading-title"));

		        // Assuming headingElements and priceElements have the same size
		        for (int i = 0; i < headingElements.size(); i++) {
		            String headingText = headingElements.get(i).getText();
		            String priceText = priceElements.get(i).getText();

		            // Put title and price in a string array
		            String[] titleAndPrice = { headingText, priceText };

		            // Map the string array to the map
		            menuItem.put("title", titleAndPrice[0]);
		            menuItem.put("price", titleAndPrice[1]);

		            // Write to the file
		            bw.write("Title: " + titleAndPrice[0]);
		            bw.newLine();
		            bw.write("Price: " + titleAndPrice[1]);
		            bw.newLine();
		            bw.write("----------");
		            bw.newLine();
		        }

		        // Add the map to the list
		        menuItems1.add(menuItem);
		    }
		    
		    
		    List<WebElement> additionalSections2 = driver.findElements(By.cssSelector("section[data-id='5f3b92ad']"));

			List<Map<String, String>> menuItems2 = new ArrayList<>();
		    
		    for (WebElement section : additionalSections2) {
		        Map<String, String> menuItem = new HashMap<>();

		        List<WebElement> headingElements = section.findElements(By.cssSelector("h3.elementor-heading-title"));
		        List<WebElement> priceElements = section.findElements(By.cssSelector("p.elementor-heading-title"));

		        for (int i = 0; i < headingElements.size(); i++) {
		            String headingText = headingElements.get(i).getText();
		            String priceText = priceElements.get(i).getText();

		            // Write data to the file
		            bw.write("Title: " + headingText);
		            bw.newLine();
		            bw.write("Price: " + priceText);
		            bw.newLine();
		            bw.write("----------");
		            bw.newLine();

		            // Put title and price in a string array
		            String[] titleAndPrice = { headingText, priceText };

		            // Map the string array to the map
		            menuItem.put("title", titleAndPrice[0]);
		            menuItem.put("price", titleAndPrice[1]);
		        }

		        // Add the map to the list
		        menuItems2.add(menuItem);
		    }

		    
		    List<WebElement> additionalSections3 = driver.findElements(By.cssSelector("section[data-id='17a6956c']"));

			List<Map<String, String>> menuItems3 = new ArrayList<>();
			
		    for (WebElement section : additionalSections3) {
		        Map<String, String> menuItem = new HashMap<>();

		        List<WebElement> headingElements = section.findElements(By.cssSelector("h3.elementor-heading-title"));
		        List<WebElement> priceElements = section.findElements(By.cssSelector("p.elementor-heading-title"));

		        for (int i = 0; i < headingElements.size(); i++) {
		            String headingText = headingElements.get(i).getText();
		            String priceText = priceElements.get(i).getText();

		            // Write data to the file
		            bw.write("Title: " + headingText);
		            bw.newLine();
		            bw.write("Price: " + priceText);
		            bw.newLine();
		            bw.write("----------");
		            bw.newLine();

		            // Put title and price in a string array
		            String[] titleAndPrice = { headingText, priceText };

		            // Map the string array to the map
		            menuItem.put("title", titleAndPrice[0]);
		            menuItem.put("price", titleAndPrice[1]);
		        }

		        // Add the map to the list
		        menuItems3.add(menuItem);
		    }
		    
		    
		    WebElement FunnelCakeSection = driver.findElement(By.cssSelector("section[data-id='6b1a6db3']"));

		    WebElement FunnelCakeDiv = FunnelCakeSection.findElement(By.cssSelector(".elementor-section .elementor-heading-title"));
		    bw.write("Title: " + FunnelCakeDiv.getText());
		    bw.newLine();

		    WebElement FunnelCakeDescriptionDiv = FunnelCakeSection.findElement(By.tagName("p"));
		    bw.write("Description: " + FunnelCakeDescriptionDiv.getText());
		    bw.newLine();

		    WebElement FunnelCakePriceDiv = FunnelCakeSection.findElement(By.cssSelector("[data-id='13641a41'] h2"));
		    bw.write("Price: " + FunnelCakePriceDiv.getText());
		    bw.newLine();

		    bw.write("-----------------------------------------------------------");
		    bw.newLine();
		    
		    
		    
		    WebElement SidesDiv = driver.findElement(By.cssSelector("div[data-id='7ff01fb1']"));
		    bw.write(SidesDiv.findElement(By.tagName("h2")).getText());
		    bw.newLine();

		    WebElement SidesSec = driver.findElement(By.cssSelector("section[data-id='5d257a2d']"));

		    List<WebElement> h3Elements = SidesSec.findElements(By.cssSelector("section.elementor-section h3.elementor-heading-title.elementor-size-default"));
		    List<WebElement> pElements = SidesSec.findElements(By.cssSelector("section.elementor-section p.elementor-heading-title.elementor-size-default"));

		    if (h3Elements.size() == pElements.size()) {
		        for (int i = 0; i < h3Elements.size(); i++) {
		            WebElement h3Element = h3Elements.get(i);
		            WebElement pElement = pElements.get(i);

		            String h3Text = h3Element.getText();
		            String pText = pElement.getText();

		            bw.write("Title: " + h3Text);
		            bw.newLine();
		            bw.write("Description: " + pText);
		            bw.newLine();
		            bw.write("----------");
		            bw.newLine();
		        }
		    } else {
		        bw.write("Mismatch in the number of h3 and p elements.");
		    }
		    
		    
		    
		    WebElement MilkshakesDiv = driver.findElement(By.cssSelector("div[data-id='bfb945d']"));
		    bw.write(MilkshakesDiv.findElement(By.tagName("h2")).getText());
		    bw.newLine();
		    bw.write("-----------------------------------------------------------------------");
		    bw.newLine();

		    WebElement MilkshakesPriceDiv = driver.findElement(By.cssSelector("div[data-id='213c5c53']"));
		    bw.write("Price for any flavored milkshake: " + MilkshakesPriceDiv.findElement(By.tagName("h2")).getText());
		    bw.newLine();

		    List<WebElement> MilkshakesFlavoursDiv = driver.findElements(By.cssSelector("section[data-id='23411114'] h3"));
		    bw.write("Flavors available for milkshakes are:");
		    bw.newLine();
		    for (WebElement flavour : MilkshakesFlavoursDiv) {
		        bw.write(flavour.getText());
		        bw.newLine();
		    }
		    
		    System.out.println("Data saved to file: " + filePath); // Confirm the data was saved to the file
		} catch (IOException e) {
		    e.printStackTrace();
		}

		// Close the browser
		driver.quit();

	}
}
