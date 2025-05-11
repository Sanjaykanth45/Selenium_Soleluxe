package org.testing;

import java.util.Date;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TS_030 {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
    	System.out.println("BeforeClass - TS_030: Testing the Functionality of Navbar Links");
        System.out.println("Test case execution started at: " + new Date());

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/user"); // adjust as needed
    }

    // Helper method to scroll and check visibility
    public boolean isSectionVisible(String sectionId) {
        try {
            WebElement section = driver.findElement(By.id(sectionId));
            return section.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Test(priority = 1)
    public void TC_028_homeLinkNavigation() throws InterruptedException {
        WebElement homeLink = driver.findElement(By.linkText("Home"));
        homeLink.click();
        Thread.sleep(1000);
        Assert.assertTrue(isSectionVisible("home"), "Failed to scroll to Home section.");
    }

    @Test(priority = 2)
    public void TC_029_productsLinkNavigation() throws InterruptedException {
        WebElement productLink = driver.findElement(By.linkText("Product"));
        productLink.click();
        Thread.sleep(1000);
        Assert.assertTrue(isSectionVisible("products"), "Failed to scroll to Product section.");
    }

    @Test(priority = 3)
    public void TC_030_contactLinkNavigation() throws InterruptedException {
        WebElement contactLink = driver.findElement(By.linkText("Contact"));
        contactLink.click();
        Thread.sleep(1000);
        Assert.assertTrue(isSectionVisible("contact"), "Failed to scroll to Contact section.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
