package org.testing;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Date;

public class TS_034 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
    	 System.out.println("BeforeClass - TS_034: Testing the Functionality of 'Shop Now' Button on Homepage");
         System.out.println("Test case execution started at: " + new Date());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/user#home"); 
    }

    @Test(priority = 1)
    public void TC_010_redirectsToProductsPage() throws InterruptedException {
        WebElement shopNowButton = driver.findElement(By.xpath("//button[@id='shopNowButton']"));
        shopNowButton.click();
        Thread.sleep(2000); // wait for redirection

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(
                currentUrl.contains("http://localhost:3000/user#products") || currentUrl.contains("/shop"),
                "❌ Shopnow button did not redirect to the products page. URL: " + currentUrl
        );
    }

    @Test(priority = 2)
    public void TC_011_shopNowButtonIsClickable() {
        WebElement shopNowButton = driver.findElement(By.xpath("//button[@id='shopNowButton']"));
        Assert.assertTrue(shopNowButton.isDisplayed() && shopNowButton.isEnabled(), "Shopnow button is not clickable.");
        shopNowButton.click(); // Optional click to confirm
    }


    
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
