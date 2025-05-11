package org.testing;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.Assert;

import java.time.Duration;
import java.util.Date;

public class TS_020 {

    WebDriver driver;

    @BeforeClass
    public void browserOpen() {
        System.out.println("BeforeClass - TS_020: Testing the Login Link Functionality on Register Page");
        System.out.println("Test case execution started at: " + new Date());
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://localhost:3000/"); // Adjust to match your actual register page URL
    }

    // TC_039 - Check if the Login link is visible
    @Test
    public void TC_039_loginLinkIsVisible() {
        WebElement loginLink = driver.findElement(By.linkText("Login"));
        Assert.assertTrue(loginLink.isDisplayed(), "Login link is not visible on the register page");
    }

    // TC_040 - Check if clicking Login link redirects correctly
    @Test
    public void TC_040_loginLinkRedirectsCorrectly() {
        WebElement loginLink = driver.findElement(By.linkText("Login"));
        loginLink.click();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/login"), "User was not redirected to the login page");
    }

    // TC_041 - Check if the Login link points to the correct URL
    @Test
    public void TC_041_loginLinkPointsToCorrectURL() {
        WebElement loginLink = driver.findElement(By.linkText("Login"));
        String href = loginLink.getAttribute("href");

        Assert.assertTrue(href.endsWith("/login") || href.contains("/login"),
            "Login link does not point to the correct URL. Found: " + href);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
