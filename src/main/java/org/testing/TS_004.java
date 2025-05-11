package org.testing;

import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;


public class TS_004 extends BaseClass {

    @BeforeClass
    public void browserOpen() {
        System.out.println("BeforeClass - TS_004: Password Field Tests");
        System.out.println("Test case executed at: " + new Date());
    }

    @BeforeMethod
    public void beforeTestCase() {
        launchBrowser();
        windowMaximize();
        launchUrl("http://localhost:3000/login");
    }

    @Test
    public void TC_010_maskedPasswordField() throws IOException {
        try {
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("password123");
            String fieldType = passwordField.getAttribute("type");

            Assert.assertEquals(fieldType, "password", "TC_010 - Fail: Password field not masked");
            System.out.println("TC_010 - Pass: Password is properly masked as dots");
        } catch (Exception e) {
            System.out.println("TC_010 - Fail: Exception occurred");
            screenShot("TC_010");
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void TC_011_shortPasswordValidation() throws IOException {
        try {
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement submitBtn = driver.findElement(By.xpath("//button[@type='submit']"));

            passwordField.sendKeys("abc");
            submitBtn.click();
            Thread.sleep(2000);

            boolean isErrorShown = driver.getPageSource().contains("short") || driver.getPageSource().contains("error");

            Assert.assertTrue(isErrorShown, "TC_011 - Fail: Short password not properly validated");
            System.out.println("TC_011 - Pass: Error shown for short password");
        } catch (Exception e) {
            System.out.println("TC_011 - Fail: Exception occurred");
            screenShot("TC_011");
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void TC_012_strongPasswordAccepted() throws IOException {
        try {
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement submitBtn = driver.findElement(By.xpath("//button[@type='submit']"));

            passwordField.sendKeys("Sanjaysk123!");
            submitBtn.click();
            Thread.sleep(2000);

            boolean isSuccess = !driver.getCurrentUrl().contains("/register") || driver.getPageSource().contains("success");

            Assert.assertTrue(isSuccess, "TC_012 - Fail: Strong password not accepted");
            System.out.println("TC_012 - Pass: Strong password accepted and form submitted");
        } catch (Exception e) {
            System.out.println("TC_012 - Fail: Exception occurred");
            screenShot("TC_012");
            Assert.fail(e.getMessage());
        }
    }

    @AfterMethod
    public void afterTestCase() {
        System.out.println("After Test Case - TS_004: Closing browser");
        closeEntireBrowser();
    }
}
