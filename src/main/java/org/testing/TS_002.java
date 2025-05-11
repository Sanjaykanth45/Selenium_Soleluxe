package org.testing;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS_002 extends BaseClass {

    @BeforeClass
    public void browserOpen() {
        System.out.println("BeforeClass - TS_002");
        System.out.println("Test case executed at: " + new Date());
    }

    @BeforeMethod
    public void beforeTestCase() {
        launchBrowser();
        windowMaximize();
        launchUrl("http://localhost:3000/login");
    }

    @Test
    public void TC_004_validEmailAndPassword() throws Throwable {
        try {
            WebElement emailField = driver.findElement(By.id("email"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

            emailField.sendKeys("sanjay@gmail.com");
            passwordField.sendKeys("12345"); // Use a valid test password
            loginButton.click();

            Thread.sleep(2000); // Wait for navigation or message to appear

            boolean isLoginSuccess = driver.getPageSource().contains("Login Successful") 
                                   || !driver.getCurrentUrl().contains("/login");

            Assert.assertTrue(isLoginSuccess, "TC_004 - Fail: Login failed with valid credentials");
            System.out.println("TC_004 - Pass: Valid login successful");
        } catch (Throwable e) {
            System.out.println("TC_004 - Fail: Exception during valid login test");
            screenShot("TC_004");
            throw e;
        }
    }

    @Test
    public void TC_005_invalidEmailFormat() throws Throwable {
        try {
            WebElement emailField = driver.findElement(By.id("email"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

            emailField.sendKeys("sanjay#gmail.com");
            passwordField.sendKeys("123456");
            loginButton.click();

            Thread.sleep(2000); // Wait for message

            boolean isErrorShown = driver.getPageSource().contains("error") 
                                || driver.getCurrentUrl().contains("/login");

            Assert.assertTrue(isErrorShown, "TC_005 - Fail: Invalid email format accepted");
            System.out.println("TC_005 - Pass: Invalid email format rejected");
        } catch (Throwable e) {
            System.out.println("TC_005 - Fail: Exception during invalid email format test");
            screenShot("TC_005");
            throw e;
        }
    }

    @Test
    public void TC_006_emptyEmailAndPassword() throws Throwable {
        try {
            WebElement emailField = driver.findElement(By.id("email"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

            emailField.clear();
            passwordField.clear();
            loginButton.click();

            Thread.sleep(2000); // Wait for validation or error message

            boolean isMandatoryError = driver.getPageSource().contains("required") 
                                    || driver.getCurrentUrl().contains("/login");

            Assert.assertTrue(isMandatoryError, "TC_006 - Fail: Form submitted without email or password");
            System.out.println("TC_006 - Pass: Required fields enforced");
        } catch (Throwable e) {
            System.out.println("TC_006 - Fail: Exception during empty field test");
            screenShot("TC_006");
            throw e;
        }
    }

    @AfterMethod
    public void afterTestCase() {
        System.out.println("After Test Case - TS_002: Closing browser");
        closeEntireBrowser();
    }
}
