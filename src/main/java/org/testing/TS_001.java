package org.testing;

import java.io.IOException;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS_001 extends BaseClass {

    @BeforeClass
    public void browserOpen() {
        System.out.println("BeforeClass");
        Date d = new Date();
        System.out.println("Test case executed at: " + d);
    }

    @BeforeMethod
    public void beforeTestCase() {
        launchBrowser();
        windowMaximize();
        launchUrl("http://localhost:3000/login");
    }

    @Test
    public void TC_001() throws IOException {
        WebElement emailField = driver.findElement(By.id("email"));
        try {
            Assert.assertTrue(emailField.isDisplayed(), "TC_001 - Test Failed: Email field is not visible");
            System.out.println("TC_001 - Test passed");
        } catch (Throwable e) {
            System.out.println("TC_001 - Test failed");
            screenShot("TC_001");
            throw e;
        }
    }

    @Test
    public void TC_002() throws IOException {
        try {
            WebElement emailField = driver.findElement(By.id("email"));

            int emailFieldX = emailField.getLocation().getX();
            int emailFieldWidth = emailField.getSize().getWidth();
            int pageWidth = driver.manage().window().getSize().getWidth();

            int emailFieldCenter = emailFieldX + (emailFieldWidth / 2);
            int pageCenter = pageWidth / 2;

            Assert.assertTrue(Math.abs(emailFieldCenter - pageCenter) <= 20,
                    "TC_002 - Test Failed: Email textbox is not properly aligned");

            System.out.println("TC_002 - Test Passed: Email textbox is properly aligned");
        } catch (Throwable e) {
            System.out.println("TC_002 - Test Failed: Email textbox is not properly aligned");
            screenShot("TC_002");
            throw e;
        }
    }

    @Test
    public void TC_003() throws IOException {
        System.out.println("Test case executed at: " + new Date());

        int[][] screenSizes = {
            {1920, 1080},  // Desktop
            {1366, 768},   // Laptop
            {768, 1024},   // Tablet
            {375, 812}     // iPhone X
        };

        for (int[] size : screenSizes) {
            int width = size[0];
            int height = size[1];

            driver.manage().window().setSize(new Dimension(width, height));
            driver.get("http://localhost:3000/login");

            try {
                WebElement emailTextbox = driver.findElement(By.id("email"));

                int textboxWidth = emailTextbox.getSize().getWidth();
                System.out.println("Screen size: " + width + "x" + height);
                System.out.println("Textbox width: " + textboxWidth);
                System.out.println("Minimum required width: " + (int)(width * 0.3));
                int minRequiredWidth = 250;
                Assert.assertTrue(
                    textboxWidth >= minRequiredWidth,
                    "TC_003 - Fail: Textbox not responsive on screen size: " + width + "x" + height
                );

                System.out.println("TC_003 - Pass: Textbox is responsive on screen size: " + width + "x" + height);
            } catch (Throwable e) {
                System.out.println("TC_003 - Test Failed: Issue with screen size: " + width + "x" + height);
                screenShot("TC_003_" + width + "x" + height);
                throw e;
            }
        }
    }

    @AfterMethod
    public void afterTestCase() {
        System.out.println("After Test Case: Closing browser");
        closeEntireBrowser();
    }
}
