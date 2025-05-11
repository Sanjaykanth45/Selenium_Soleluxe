package org.testing;

import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class TS_007 extends BaseClass {

    @BeforeClass
    public void browserOpen() {
        System.out.println("BeforeClass - TS_007: Register Link Functionality Tests");
        System.out.println("Test case execution started at: " + new Date());
    }

    @BeforeMethod
    public void setup() {
        launchBrowser();
        windowMaximize();
        launchUrl("http://localhost:3000/login");
    }

    @Test
    public void TC_019_registerLinkVisible() throws IOException {
        try {
            WebElement registerLink = driver.findElement(By.linkText("Register"));
            Assert.assertTrue(registerLink.isDisplayed(), "TC_019 - Fail: Register link is not visible");
            System.out.println("TC_019 - Pass: Register link is visible on the login page");
        } catch (Exception e) {
            System.out.println("TC_019 - Fail: Exception occurred");
            screenShot("TC_019");
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void TC_020_registerLinkRedirection() throws IOException {
        try {
            WebElement registerLink = driver.findElement(By.linkText("Register"));
            registerLink.click();
            Thread.sleep(2000); // Wait for redirection

            boolean redirected = driver.getCurrentUrl().contains("http://localhost:3000");
            Assert.assertTrue(redirected, "TC_020 - Fail: Not redirected to register page after clicking Register link");
            System.out.println("TC_020 - Pass: Register link redirects to register page");
        } catch (Exception e) {
            System.out.println("TC_020 - Fail: Exception occurred");
            screenShot("TC_020");
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void TC_021_registerLinkHrefCheck() throws IOException {
        try {
            WebElement registerLink = driver.findElement(By.linkText("Register"));
            String href = registerLink.getAttribute("href");
            Assert.assertTrue(href.contains("/"), "TC_021 - Fail: Register link does not point to correct URL");
            System.out.println("TC_021 - Pass: Register link URL is correct: " + href);
        } catch (Exception e) {
            System.out.println("TC_021 - Fail: Exception occurred");
            screenShot("TC_021");
            Assert.fail(e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("AfterMethod - Closing browser");
        closeEntireBrowser();
    }
}
