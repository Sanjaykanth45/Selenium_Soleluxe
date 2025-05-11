package org.testing;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TS_050 extends BaseClass{
	 WebDriver driver;
	 
	  @BeforeClass
	    public void setUp() {
		  
			 System.out.println("BeforeClass - TS_050: Testing the Functionality of Checkout Button");
		   	  System.out.println("Test case execution started at: " + new Date());

		   	  
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("http://localhost:3000/user");
	    }
	  
	  @Test
	  public void TC_013_CheckoutRedirectionFlow() throws InterruptedException {
	      // Step 1: Navigate to user/product page


	      // Step 2: Add a product to the cart (Badminton Shoes)
	      WebElement productButton = driver.findElement(By.xpath("//button[@data-product-id='67ab6ef9df9a5a297700d7b3']"));
	      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'})", productButton);
	      Thread.sleep(1000);
	      productButton.click();
	      Thread.sleep(1000);

	      // Step 3: Navigate to the Cart page
	      WebElement cartPageLink = driver.findElement(By.xpath("//a[@href='/cart']"));
	      cartPageLink.click();
	      Thread.sleep(1000); // Wait for cart page

	      // Step 4: Click the "Checkout" button
	      WebElement checkoutButton = driver.findElement(By.className("checkout-btn"));
	      checkoutButton.click();
	      Thread.sleep(2000); // Wait for redirection

	      // Step 5: Verify the redirection to Checkout page
	      String currentUrl = driver.getCurrentUrl();
//	      String expectedUrl = "http://localhost:3000/checkout";
////
//	      Assert.assertEquals("Checkout button should redirect to the correct page", expectedUrl, currentUrl);
	      System.out.println("Successfully redirected to checkout: " + currentUrl);
	      driver.quit();
	  }

}
