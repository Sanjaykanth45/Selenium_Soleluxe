package org.testing;

import java.io.IOException;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class TS_008 extends BaseClass {

    @BeforeClass
    public void initSuite() {
        System.out.println("BeforeClass - TS_008: Username Field UI Tests");
        System.out.println("Test case executed at: " + new Date());
    }

    @BeforeMethod
    public void setUp() {
        launchBrowser();
        windowMaximize();
        launchUrl("http://localhost:3000/");
    }

    @Test
    public void TC_001_usernameTextboxVisible() throws IOException {
        try {
            WebElement usernameField = driver.findElement(By.name("username"));
            Assert.assertTrue(usernameField.isDisplayed(), "TC_001 - Fail: Username textbox not visible");
            System.out.println("TC_001 - Pass: Username textbox is visible");
        } catch (Exception e) {
            screenShot("TC_001");
            Assert.fail("Exception in TC_001: " + e.getMessage());
        }
    }

    @Test
    public void TC_002_usernameTextboxAlignment() throws IOException {
        try {
            WebElement usernameField = driver.findElement(By.name("username"));
            int x = usernameField.getLocation().getX();
            int y = usernameField.getLocation().getY();
            Assert.assertTrue(x > 0 && y > 0, "TC_002 - Fail: Username textbox not aligned properly");
            System.out.println("TC_002 - Pass: Username textbox is placed and aligned properly");
        } catch (Exception e) {
            screenShot("TC_002");
            Assert.fail("Exception in TC_002: " + e.getMessage());
        }
    }

    @Test
    public void TC_003_usernameTextboxResponsive() {
        System.out.println("TC_003 - Executing: Username textbox responsiveness test");

        // Resize to typical mobile screen dimensions
        driver.manage().window().setSize(new Dimension(375, 667)); // iPhone 8-like screen

        // Wait briefly for layout adjustment (optional, better to use explicit wait if necessary)
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Locate the username input
        WebElement usernameField = driver.findElement(By.name("username"));

        // Check: visible and has reasonable width (> 150px assumed)
        boolean isResponsive = usernameField.isDisplayed() && usernameField.getSize().getWidth() >= 150;

        // Assert
        Assert.assertTrue(isResponsive, "TC_003 - Fail: Username textbox not responsive");
        System.out.println("TC_003 - Pass: Username textbox is responsive");
    }


    @AfterMethod
    public void tearDown() {
        closeEntireBrowser();
    }
}
