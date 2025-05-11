package org.testing;

import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class TS_009 extends BaseClass {

    @BeforeClass
    public void browserOpen() {
        System.out.println("BeforeClass - TS_009: Username Functionality Tests");
        System.out.println("Test case execution started at: " + new Date());
    }

    @BeforeMethod
    public void setup() {
        launchBrowser();
        windowMaximize();
        launchUrl("http://localhost:3000/"); // Adjust if needed
    }

    @Test
    public void TC_004_usernameContainsOnlyAlphabets() throws IOException {
        try {
            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.clear();
            usernameField.sendKeys("OnlyAlpha");
            String username = usernameField.getAttribute("value");

            Assert.assertTrue(username.matches("[a-zA-Z]+"), "TC_004 - Fail: Username contains non-alphabetic characters");
            System.out.println("TC_004 - Pass: Username contains only alphabets");
        } catch (Exception e) {
            screenShot("TC_004");
            Assert.fail("Exception in TC_004: " + e.getMessage());
        }
    }

    @Test
    public void TC_005_usernameContainsNoNumericValues() throws IOException {
        try {
            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.clear();
            usernameField.sendKeys("UserName"); // corrected to not have numbers
            String username = usernameField.getAttribute("value");

            Assert.assertFalse(username.matches(".*\\d.*"), "TC_005 - Fail: Username contains numeric values");
            System.out.println("TC_005 - Pass: Username does not contain numeric values");
        } catch (Exception e) {
            screenShot("TC_005");
            Assert.fail("Exception in TC_005: " + e.getMessage());
        }
    }

    @Test
    public void TC_006_usernameContainsNoSpecialCharacters() throws IOException {
        try {
            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.clear();
            usernameField.sendKeys("ValidName"); // no special characters
            String username = usernameField.getAttribute("value");

            Assert.assertFalse(username.matches(".*[!@#$%^&*(),.?\":{}|<>].*"), "TC_006 - Fail: Username contains special characters");
            System.out.println("TC_006 - Pass: Username does not contain special characters");
        } catch (Exception e) {
            screenShot("TC_006");
            Assert.fail("Exception in TC_006: " + e.getMessage());
        }
    }

    @Test
    public void TC_007_usernameContainsNoSpaces() throws IOException {
        try {
            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.clear();
            usernameField.sendKeys("NoSpaces"); // no spaces
            String username = usernameField.getAttribute("value");

            Assert.assertFalse(username.contains(" "), "TC_007 - Fail: Username contains spaces");
            System.out.println("TC_007 - Pass: Username does not contain spaces");
        } catch (Exception e) {
            screenShot("TC_007");
            Assert.fail("Exception in TC_007: " + e.getMessage());
        }
    }

    @Test
    public void TC_008_usernameIsMandatory() {
        System.out.println("TC_008 - Started: Username is a mandatory field");

        // Open the form page
        driver.get("http://localhost:3000");

        // Use FluentWait to wait for elements with a longer timeout and proper conditions
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(30))  // Increased timeout to 30 seconds
            .pollingEvery(Duration.ofMillis(500))  // Poll every 500ms
            .ignoring(NoSuchElementException.class);

        // Wait for the username field to be visible and clear any pre-existing value
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        usernameField.clear();

        // Fill out other fields in the form
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).sendKeys("test@example.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys("Test@1234");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("mobile"))).sendKeys("9876543210");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("gender"))).sendKeys("Male");

        // Wait for the submit button to be visible and enabled
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        
        // Ensure the submit button is clickable and then click it
        Assert.assertTrue(submitButton.isDisplayed(), "Submit button is not displayed");
        Assert.assertTrue(submitButton.isEnabled(), "Submit button is not enabled");
        
        submitButton.click();

        // Check for error message indicating that username is mandatory
        boolean isErrorDisplayed;
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Username is required')]")));
            isErrorDisplayed = error.isDisplayed();
        } catch (NoSuchElementException e) {
            isErrorDisplayed = false;
        }

        // Assert that error is displayed for missing username
        Assert.assertTrue(isErrorDisplayed, "TC_008 - Fail: Username field is empty and no error shown");
        System.out.println("TC_008 - Pass: Username is mandatory and properly validated");
    }




    @AfterMethod
    public void tearDown() {
        closeEntireBrowser();
    }
}
