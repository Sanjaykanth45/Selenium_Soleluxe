package org.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Date;

public class TS_022 extends BaseClass {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.out.println("BeforeClass - TS_022: Testing the Logo Redirection and Accessibility Functionality");
        System.out.println("Test case execution started at: " + new Date());

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/user");
    }

    // TC_004 – Clicking the logo redirects to homepage
    @Test(priority = 1)
    public void TC_004_testingLogoFunctionality() {
        WebElement logo = driver.findElement(By.cssSelector(".navbar-brand.logo"));
        logo.click();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("home"), "User is not redirected to the homepage after clicking the logo.");
    }

    // TC_005 – Verify the Logo link works without errors
    @Test(priority = 2)
    public void TC_005_verifyLogoLink() {
        WebElement logo = driver.findElement(By.cssSelector(".navbar-brand.logo"));
        logo.click();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("home"), "No error occurred while clicking on the logo, but no redirection happened.");
    }

    // TC_006 – Verify Logo link is accessible using keyboard
    @Test(priority = 3)
    public void TC_006_verifyLogoAccessibility() {
        WebElement logo = driver.findElement(By.cssSelector(".navbar-brand.logo"));
        logo.sendKeys(Keys.TAB);
        Assert.assertFalse(logo.equals(driver.switchTo().activeElement()), "Logo is not focusable via keyboard navigation.");
        logo.sendKeys(Keys.ENTER);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("home"), "Logo click via keyboard does not redirect to the homepage.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
