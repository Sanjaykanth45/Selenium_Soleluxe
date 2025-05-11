package org.testing;

import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class TS_005 extends BaseClass {

    @BeforeClass
    public void browserOpen() {
        System.out.println("BeforeClass - TS_005: Login Button UI Tests");
        System.out.println("Test case execution started at: " + new Date());
    }

    @BeforeMethod
    public void setup() {
        launchBrowser();
        windowMaximize();
        launchUrl("http://localhost:3000/login");
    }

    @Test
    public void TC_013_loginButtonVisibility() throws IOException {
        try {
            WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));
            Assert.assertTrue(loginBtn.isDisplayed(), "TC_013 - Fail: Login button is not visible");
            System.out.println("TC_013 - Pass: Login button is visible on the login page");
        } catch (Exception e) {
            System.out.println("TC_013 - Fail: Exception during visibility check");
            screenShot("TC_013");
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void TC_014_loginButtonAlignment() throws IOException {
        try {
            WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));

            int x = loginBtn.getLocation().getX();
            int y = loginBtn.getLocation().getY();
            int height = loginBtn.getSize().getHeight();
            int width = loginBtn.getSize().getWidth();

            System.out.println("Login button position - X: " + x + ", Y: " + y);
            System.out.println("Login button size - Height: " + height + ", Width: " + width);

            Assert.assertTrue(x >= 0 && y >= 0 && height > 0 && width > 0, 
                "TC_014 - Fail: Login button position or size invalid");
            System.out.println("TC_014 - Pass: Login button is properly aligned and sized");
        } catch (Exception e) {
            System.out.println("TC_014 - Fail: Exception during alignment check");
            screenShot("TC_014");
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void TC_015_loginButtonResponsiveness() throws IOException {
        try {
            WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));

            // Original size
            driver.manage().window().setSize(new org.openqa.selenium.Dimension(1920, 1080));
            boolean visibleLarge = loginBtn.isDisplayed();

            // Tablet size
            driver.manage().window().setSize(new org.openqa.selenium.Dimension(768, 1024));
            Thread.sleep(1000);
            boolean visibleTablet = loginBtn.isDisplayed();

            // Mobile size
            driver.manage().window().setSize(new org.openqa.selenium.Dimension(375, 667));
            Thread.sleep(1000);
            boolean visibleMobile = loginBtn.isDisplayed();

            Assert.assertTrue(visibleLarge && visibleTablet && visibleMobile, 
                "TC_015 - Fail: Login button is not responsive across all screen sizes");

            System.out.println("TC_015 - Pass: Login button adjusts correctly on all screen sizes");
        } catch (Exception e) {
            System.out.println("TC_015 - Fail: Exception during responsiveness check");
            screenShot("TC_015");
            Assert.fail(e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("AfterMethod - Closing browser");
        closeEntireBrowser();
    }
}
