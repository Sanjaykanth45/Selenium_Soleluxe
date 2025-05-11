package org.testing;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Date;

public class TS_036 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
    	System.out.println("BeforeClass - TS_036: Testing Product Card Navigation and Add to Cart Functionality");
        System.out.println("Test case execution started at: " + new Date());

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/user"); 
    }

    @Test(priority = 1)
    public void TC_004_productCardNavigation() throws InterruptedException {
        try {
        	JavascriptExecutor js = (JavascriptExecutor) driver;
        	js.executeScript("window.scrollTo(300, document.body.scrollHeight);");
            // Find first product card link
            WebElement productLink = driver.findElement(By.xpath("//button[@data-product-id ='67ab6ef9df9a5a297700d7b3']"));
            
            // Scroll to the element to ensure it's clickable     
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(productLink));

            productLink.click();
            Thread.sleep(2000);

            // Wait for detail section to appear
            WebElement detailSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("productDetail")));
            Assert.assertTrue(detailSection.isDisplayed(), "Product detail section not visible.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Product card navigation failed: " + e.getMessage());
        }
    }


    @Test
    public void TC_005_addToCartFunctionality() throws InterruptedException {
        // Locate the element
        WebElement productButton = driver.findElement(By.xpath("//button[@data-product-id ='67ab6ef9df9a5a297700d7b3']"));

        // Scroll the element into view
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'})", productButton);

        // Wait for scrolling to complete
        Thread.sleep(1000);

        // Click the button
        productButton.click();
        Thread.sleep(1000);
        WebElement CartPage = driver.findElement(By.xpath("//a[@href='/cart']"));
        CartPage.click();
        Thread.sleep(1000);
        WebElement ProductImage = driver.findElement(By.xpath("//img[@alt = 'Badminton Shoes']"));
        Assert.assertTrue(ProductImage.isDisplayed(),"TC_005 is failed ");
    }

}
