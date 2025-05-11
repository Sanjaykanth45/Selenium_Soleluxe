package org.testing;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS_003 extends BaseClass {

    @BeforeClass
    public void browserOpen() {
        System.out.println("BeforeClass: Initializing test suite at " + new Date());
    }

    @BeforeMethod
    public void beforeTestCase() {
        // Initialize browser before each test case
        launchBrowser();
        windowMaximize();
        launchUrl("http://localhost:3000/login");
    }

    @Test
    public void TC_007() throws Throwable {
        // Test if password field is visible
        WebElement passwordField = driver.findElement(By.id("password"));
        try {
            Assert.assertTrue(passwordField.isDisplayed(), "TC_007 - Test Failed: Password field is not visible");
            System.out.println("TC_007 - Test Passed: Password field is visible");
        } catch (Throwable e) {
            handleTestFailure("TC_007", e);
        }
    }

    @Test
    public void TC_008() throws Throwable {
        // Test password field alignment
        try {
            WebElement passwordField = driver.findElement(By.id("password"));
            int passwordX = passwordField.getLocation().getX();
            int passwordWidth = passwordField.getSize().getWidth();
            int pageWidth = driver.manage().window().getSize().getWidth();

            int passwordCenter = passwordX + (passwordWidth / 2);
            int pageCenter = pageWidth / 2;

            Assert.assertTrue(Math.abs(passwordCenter - pageCenter) <= 20,
                    "TC_008 - Test Failed: Password textbox is not properly aligned");

            System.out.println("TC_008 - Test Passed: Password textbox is properly aligned");
        } catch (Throwable e) {
            handleTestFailure("TC_008", e);
        }
    }

    @Test
    public void TC_009() throws Throwable {
        // Test password field masking
        try {
            WebElement passwordField = driver.findElement(By.id("password"));
            String fieldType = passwordField.getAttribute("type");

            Assert.assertEquals(fieldType, "password", "TC_009 - Test Failed: Password input is not masked");

            System.out.println("TC_009 - Test Passed: Password input is properly masked");
        } catch (Throwable e) {
            handleTestFailure("TC_009", e);
        }
    }

    @AfterMethod
    public void afterTestCase() {
        System.out.println("After Test Case: Closing browser");
        closeEntireBrowser();
    }

    // Helper method to handle test failure and capture screenshot
    private void handleTestFailure(String testCaseId, Throwable e) throws Throwable {
        System.out.println(testCaseId + " - Test Failed: " + e.getMessage());
        screenShot(testCaseId);  // Capture screenshot for failed test case
        throw e;  // Rethrow the exception to ensure the test is marked as failed
    }
}
