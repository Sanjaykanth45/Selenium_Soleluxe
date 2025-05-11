package org.testing;

import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class TS_006 extends BaseClass {

    @BeforeClass
    public void browserOpen() {
        System.out.println("BeforeClass - TS_006: Login Functionality Tests");
        System.out.println("Test case execution started at: " + new Date());
    }

    @BeforeMethod
    public void setup() {
        launchBrowser();
        windowMaximize();
        launchUrl("http://localhost:3000/login");
    }

    @Test
    public void TC_016_loginButtonClickable() throws IOException {
        try {
            WebElement username = driver.findElement(By.id("email"));
            WebElement password = driver.findElement(By.id("password"));
            WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));

            username.sendKeys("validemail@example.com");
            password.sendKeys("ValidPass123!");
            Assert.assertTrue(loginBtn.isEnabled(), "TC_016 - Fail: Login button is not clickable");

            loginBtn.click();
            System.out.println("TC_016 - Pass: Login button is clickable and clicked");
        } catch (Exception e) {
            System.out.println("TC_016 - Fail: Exception occurred during click check");
            screenShot("TC_016");
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void TC_017_loginWithValidCredentials() throws IOException {
        try {
            WebElement username = driver.findElement(By.id("email"));
            WebElement password = driver.findElement(By.id("password"));
            WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));

            username.sendKeys("sanjay@gmail.com");
            password.sendKeys("12345");
            loginBtn.click();
            Thread.sleep(2000);

            boolean redirected = !driver.getCurrentUrl().contains("/login");
            Assert.assertTrue(redirected, "TC_017 - Fail: Did not redirect after valid login");

            System.out.println("TC_017 - Pass: User redirected successfully after valid login");
        } catch (Exception e) {
            System.out.println("TC_017 - Fail: Exception during valid login");
            screenShot("TC_017");
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void TC_018_loginWithInvalidCredentials() throws IOException {
        try {
            WebElement username = driver.findElement(By.id("email"));
            WebElement password = driver.findElement(By.id("password"));
            WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));

            username.sendKeys("invalid@gmail.com");
            password.sendKeys("WrongPass!");
            loginBtn.click();
            Thread.sleep(2000);

            boolean errorDisplayed = driver.getPageSource().toLowerCase().contains("invalid") ||
                                     driver.getPageSource().toLowerCase().contains("error");

            Assert.assertTrue(errorDisplayed, "TC_018 - Fail: No error message shown for invalid credentials");
            System.out.println("TC_018 - Pass: Error message displayed for invalid login");
        } catch (Exception e) {
            System.out.println("TC_018 - Fail: Exception during invalid login");
            screenShot("TC_018");
            Assert.fail(e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("AfterMethod - Closing browser");
        closeEntireBrowser();
    }
}
