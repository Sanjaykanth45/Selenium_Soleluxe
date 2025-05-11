package org.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Date;

public class TS_021 extends BaseClass {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.out.println("BeforeClass - TS_021: Testing the Logo Visibility and Responsiveness on Homepage");
        System.out.println("Test case execution started at: " + new Date());

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/user");
    }

    // TC_001 – Check Logo Visibility
    @Test(priority = 1)
    public void TC_001_checkLogoVisibility() {
        WebElement logo = driver.findElement(By.cssSelector(".navbar-brand.logo"));
        Assert.assertTrue(logo.isDisplayed(), "Logo should be visible on the homepage.");
    }

    // TC_002 – Check Logo Placement and Alignment
    @Test(priority = 2)
    public void TC_002_checkLogoPlacementAndAlignment() {
        WebElement logo = driver.findElement(By.cssSelector(".navbar-brand.logo"));
        String parentClass = logo.findElement(By.xpath("./..")).getAttribute("class");
        Assert.assertTrue(parentClass.contains("col-md-3"), "Logo should be placed in col-md-3 class column.");
    }

    // TC_003 – Check Logo Responsiveness
    @Test(priority = 3)
    public void TC_003_checkLogoResponsiveness() {
        WebElement logo = driver.findElement(By.cssSelector(".navbar-brand.logo"));

        driver.manage().window().setSize(new Dimension(375, 812));
        Assert.assertTrue(logo.isDisplayed(), "Logo should be visible on mobile screen.");

        driver.manage().window().setSize(new Dimension(1440, 900));
        Assert.assertTrue(logo.isDisplayed(), "Logo should be visible on desktop screen.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
