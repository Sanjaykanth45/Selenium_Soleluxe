package org.testing;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Date;

public class TS_011 {
	
	@BeforeClass
    public void browserOpen() {
        System.out.println("BeforeClass - TS_011:Testing the Functionality of Email Text Box");
        System.out.println("Test case execution started at: " + new Date());
    }

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("http://localhost:3000"); 
    }

    

    // Helper method to fill and submit the registration form
    public void submitFormWithEmail(String email) {
        WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
        emailInput.clear();
        emailInput.sendKeys(email);
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();
    }

    // TC_012 - Valid email should be accepted
    @Test(priority = 1)
    public void TC_012_validEmailAccepted() {
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement emailField = driver.findElement(By.name("email"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement mobileField = driver.findElement(By.name("mobile"));
        WebElement genderField = driver.findElement(By.name("gender"));

        usernameField.sendKeys("validuser");
        emailField.sendKeys("sanjay1234@gmail.com");
        passwordField.sendKeys("Password123");
        mobileField.sendKeys("9876543210");
        genderField.sendKeys("male");

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        // Wait for success message or form to disappear (depends on your app's response)
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("register-alert")));
        Assert.assertTrue(successMessage.getText().contains("Registration Successful"));
        System.out.println("TC_012 - Pass: Valid email accepted and form submitted successfully");
    }

    // TC_013 - Invalid email should be rejected
    @Test(priority = 2)
    public void TC_013_invalidEmailRejected() {
        driver.navigate().refresh();

        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement emailField = driver.findElement(By.name("email"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement mobileField = driver.findElement(By.name("mobile"));
        WebElement genderField = driver.findElement(By.name("gender"));

        usernameField.sendKeys("validuser");
        emailField.sendKeys("sanjay#gmail.com");  // Invalid email
        passwordField.sendKeys("Password123");
        mobileField.sendKeys("9876543210");
        genderField.sendKeys("male");

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        // Wait for error message to appear
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
        Assert.assertTrue(errorMessage.getText().toLowerCase().contains("invalid"), "Invalid email should trigger error");
        System.out.println("TC_013 - Pass: Invalid email rejected with error message");
    }

    // TC_014 - Email field is mandatory
    @Test(priority = 3)
    public void TC_014_emailIsMandatory() {
        driver.navigate().refresh();

        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement emailField = driver.findElement(By.name("email"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement mobileField = driver.findElement(By.name("mobile"));
        WebElement genderField = driver.findElement(By.name("gender"));

        usernameField.sendKeys("validuser");
        emailField.clear();  // Leave email blank
        passwordField.sendKeys("Password123");
        mobileField.sendKeys("9876543210");
        genderField.sendKeys("male");

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        // Wait for validation message for email to appear
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
        Assert.assertTrue(errorMessage.getText().toLowerCase().contains("required") || errorMessage.getText().toLowerCase().contains("email"), "Empty email should trigger required validation");
        System.out.println("TC_014 - Pass: Form rejected submission without email");
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
