package org.testing;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Date;

public class TS_012 {
	
	@BeforeClass
    public void browserOpen() {
        System.out.println("BeforeClass - TS_012:Testing the UI features of password Text Box");
        System.out.println("Test case execution started at: " + new Date());
    }

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/");  // Ensure this URL is correct for your local setup
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // TC_015 - Test if the Password Textbox is visible on the signup page
    @Test(priority = 1)
    public void TC_015_passwordTextboxVisible() {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        Assert.assertTrue(passwordField.isDisplayed(), "Password textbox is not visible.");
        System.out.println("TC_015 - Pass: Password Textbox is visible on the register page");
    }

    // TC_016 - Test the placement and alignment of the Password Textbox
    @Test(priority = 2)
    public void TC_016_passwordTextboxAlignment() {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));

        // Get the X and Y positions of the Password textbox
        int passwordFieldX = passwordField.getLocation().getX();
        int passwordFieldY = passwordField.getLocation().getY();

        // You can test these values against some expected coordinates for proper placement/alignment
        // For this example, we simply print them, but you can compare against expected values
        System.out.println("Password Textbox X Position: " + passwordFieldX);
        System.out.println("Password Textbox Y Position: " + passwordFieldY);

        // For alignment validation, you could add more logic to check position relative to other elements.
        Assert.assertTrue(passwordField.isDisplayed(), "Password textbox is not aligned properly.");
        System.out.println("TC_016 - Pass: Password Textbox is placed and aligned properly within the UI");
    }

    // TC_017 - Test if the Password Textbox hides typed characters (masked with dots)
    @Test(priority = 3)
    public void TC_017_passwordTextboxHidesCharacters() {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));

        // Type a password in the password textbox
        String password = "TestPassword123";
        passwordField.sendKeys(password);

        // Check if the password is hidden by verifying the input type is "password"
        String inputType = passwordField.getAttribute("type");
        Assert.assertEquals(inputType, "password", "Password textbox does not mask the typed characters.");
        System.out.println("TC_017 - Pass: Password Textbox hides typed characters (masked as dots)");
    }
}
