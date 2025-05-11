package org.testing;


import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TS_039 {
	 WebDriver driver;

	    @BeforeClass
	    public void setUp() {
	    	
	    	 System.out.println("BeforeClass - TS_039: Testing Add to Cart Button functionality");
	    	 System.out.println("Test case execution started at: " + new Date());

	    	
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("http://localhost:3000/user#product"); // Update URL if needed
	    }
	    
	    @Test(priority = 13)
	    public void TC_013_AddToCartButton_AddsProductSuccessfully() throws InterruptedException {
//	        driver.get("http://localhost:3000/user#product");
//	        Thread.sleep(2000);

	        WebElement addToCartButton = driver.findElements(By.xpath("//button[@name='add-to-cart']")).get(0);
	        addToCartButton.click();
	        Thread.sleep(1000);

	        WebElement cartIcon = driver.findElement(By.id("cart-icon"));
	        cartIcon.click();
	        Thread.sleep(1000);

	        WebElement cartItem = driver.findElement(By.xpath("//div[contains(@class,'cart-item')]")); // Adjust as per your cart UI
	        Assert.assertTrue(cartItem.isDisplayed(), "TC_013 Failed: Product not found in cart.");
	        System.out.println("TC_013 Passed: Product added to cart successfully.");
	    }


}
