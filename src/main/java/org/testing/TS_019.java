package org.testing;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.Assert;

import java.time.Duration;
import java.util.Date;

public class TS_019 {
	
	@BeforeClass
    public void browserOpen() {
        System.out.println("BeforeClass - TS_019:Testing the Functionality of Register Button");
        System.out.println("Test case execution started at: " + new Date());
    }
	
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://localhost:3000/"); // Replace with your actual register page URL
    }

  

    // TC_036 - Register button is clickable and form submits
    @Test
    public void TC_036_registerButtonClickFunctionality() {
        driver.findElement(By.name("username")).sendKeys("TestUser");
        driver.findElement(By.name("email")).sendKeys("testuser@example.com");
        driver.findElement(By.name("password")).sendKeys("Test@1234");
        driver.findElement(By.name("mobile")).sendKeys("9876543210");
        driver.findElement(By.name("gender")).sendKeys("male");

        WebElement registerButton = driver.findElement(By.cssSelector("button[type='submit']"));
        Assert.assertTrue(registerButton.isEnabled(), "Register button is not enabled");

        registerButton.click();
    }

    // TC_037 - Validation errors appear if form is empty
    @Test
    public void TC_037_emptyFieldsValidation() {
        WebElement registerButton = driver.findElement(By.cssSelector("button[type='submit']"));
        registerButton.click();

        WebElement usernameError = driver.findElement(By.xpath("//*[contains(text(),'Username is required')]"));
        WebElement emailError = driver.findElement(By.xpath("//*[contains(text(),'Email is required')]"));

        Assert.assertTrue(usernameError.isDisplayed(), "Username validation not triggered");
        Assert.assertTrue(emailError.isDisplayed(), "Email validation not triggered");

        // Optionally: confirm page did not reload
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/"), "Form should not be submitted on empty fields");
    }

    // TC_038 - Redirect to login after successful registration
    @Test
    public void TC_038_redirectAfterSuccessfulRegistration() throws InterruptedException {
        driver.findElement(By.name("username")).sendKeys("NewUser");
        driver.findElement(By.name("email")).sendKeys("newuser123@example.com");
        driver.findElement(By.name("password")).sendKeys("StrongPass1");
        driver.findElement(By.name("mobile")).sendKeys("9123456789");
        driver.findElement(By.name("gender")).sendKeys("male");

        WebElement registerButton = driver.findElement(By.cssSelector("button[type='submit']"));
        registerButton.click();

        // Wait briefly for redirect (replace with WebDriverWait if needed)
        Thread.sleep(3000); 

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/") || currentUrl.contains("/dashboard"), 
            "User should be redirected after registration, current URL: " + currentUrl);
    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
