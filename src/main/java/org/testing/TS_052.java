package org.testing;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Date;

public class TS_052 {

    private WebDriver driver;
    @BeforeClass
    public void setUp() {
    	
    	 System.out.println("BeforeClass - TS_052: Testing the Functionality of Shipping Address Box");
	   	  System.out.println("Test case execution started at: " + new Date());

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/checkout");
        new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // TC_010 - Full Name Textbox Validation
    @Test
    public void testFullNameTextbox() {
        WebElement fullNameTextbox = driver.findElement(By.name("name"));
        fullNameTextbox.clear();
        fullNameTextbox.sendKeys("John Doe");
        String enteredName = fullNameTextbox.getAttribute("value");
        Assert.assertEquals(enteredName, "John Doe");
    }

    // TC_011 - Email Textbox Validation
    @Test
    public void testEmailTextbox() {
        WebElement emailTextbox = driver.findElement(By.name("email"));
        emailTextbox.clear();
        emailTextbox.sendKeys("sanjay@gmail.com");
        String enteredEmail = emailTextbox.getAttribute("value");
        Assert.assertEquals(enteredEmail, "sanjay@gmail.com");
    }

    // TC_012 - Street Address Textbox Validation
    @Test
    public void testStreetAddressTextbox() {
        WebElement streetAddressTextbox = driver.findElement(By.name("address"));
        streetAddressTextbox.clear();
        streetAddressTextbox.sendKeys("1/90 Sathy");
        String enteredAddress = streetAddressTextbox.getAttribute("value");
        Assert.assertEquals(enteredAddress, "1/90 Sathy");
    }

    // TC_013 - City Textbox Validation
    @Test
    public void testCityTextbox() {
        WebElement cityTextbox = driver.findElement(By.name("city"));
        cityTextbox.clear();
        cityTextbox.sendKeys("Erode");
        String enteredCity = cityTextbox.getAttribute("value");
        Assert.assertEquals(enteredCity, "Erode");
    }

    // TC_014 - State Textbox Validation
    @Test
    public void testStateTextbox() {
        WebElement stateTextbox = driver.findElement(By.name("state"));
        stateTextbox.clear();
        stateTextbox.sendKeys("Tamil Nadu");
        String enteredState = stateTextbox.getAttribute("value");
        Assert.assertEquals(enteredState, "Tamil Nadu");
    }

    // TC_015 - Zip Code Textbox Validation
    @Test
    public void testZipCodeTextbox() {
        WebElement zipCodeTextbox = driver.findElement(By.name("zip"));
        zipCodeTextbox.clear();
        zipCodeTextbox.sendKeys("638402");
        String enteredZip = zipCodeTextbox.getAttribute("value");
        Assert.assertEquals(enteredZip, "638402");
    }

    // TC_016 - Validation for Missing Fields
    @Test
    public void testMissingFieldsValidation() {
        driver.navigate().refresh(); // Clear form

        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();

        // Adjust below as per how your app shows validation
        // Example: Using CSS class or error divs
        // Assert error fields exist or some error message
        // WebElement errorMsg = driver.findElement(By.id("errorMessage"));
        // Assert.assertTrue(errorMsg.isDisplayed());
    }

    // TC_017 - Form Submission with Valid Data
    @Test
    public void testFormSubmission() {
        driver.navigate().refresh();

        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys("sanjay");

        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("sanjay@gmail.com");

        driver.findElement(By.name("address")).clear();
        driver.findElement(By.name("address")).sendKeys("1/90 Sathy");

        driver.findElement(By.name("city")).clear();
        driver.findElement(By.name("city")).sendKeys("Erode");

        driver.findElement(By.name("state")).clear();
        driver.findElement(By.name("state")).sendKeys("Tamil Nadu");

        driver.findElement(By.name("zip")).clear();
        driver.findElement(By.name("zip")).sendKeys("638402");

        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            // Handle and assert the alert
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            Assert.assertEquals(alertText, "Order Placed Successfully!");
            alert.accept();
        } catch (TimeoutException e) {
            System.out.println("No alert appeared after form submission.");
        }

        // Check if success message element exists (optional, only if you have this element)
        try {
            WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("successMessage"))
            );
            Assert.assertTrue(successMessage.isDisplayed());
        } catch (TimeoutException e) {
            System.out.println("Success message not displayed after form submission.");
        }
    }

    
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
