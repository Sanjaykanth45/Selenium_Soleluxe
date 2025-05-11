package org.testing;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Date;

public class TS_014 {
	
	@BeforeClass
    public void browserOpen() {
        System.out.println("BeforeClass - TS_014:Testing the  UI features of Mobile number Text Box");
        System.out.println("Test case execution started at: " + new Date());
    }
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new org.openqa.selenium.chrome.ChromeDriver(); 
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://localhost:3000/"); 
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1)
    public void TC_021_mobileTextboxIsVisible() {
        WebElement mobileField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("mobile")));
        Assert.assertTrue(mobileField.isDisplayed(), "Mobile number textbox is not visible.");
        System.out.println("TC_021 - Pass: Mobile number textbox is visible.");
    }

    @Test(priority = 2)
    public void TC_022_mobileTextboxAlignment() {
        WebElement mobileField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("mobile")));
        Point location = mobileField.getLocation();
        Dimension size = mobileField.getSize();

        System.out.println("TC_022 - Location: " + location + ", Size: " + size);
        Assert.assertTrue(location.getX() >= 0 && location.getY() >= 0, "Mobile textbox is misaligned.");
        Assert.assertTrue(size.getHeight() > 0 && size.getWidth() > 0, "Mobile textbox size seems invalid.");
        System.out.println("TC_022 - Pass: Mobile number textbox is properly placed and aligned.");
    }

    @Test(priority = 3)
    public void TC_023_mobileTextboxResponsive() {
        WebElement mobileField;

        // Desktop view
        driver.manage().window().setSize(new Dimension(1200, 800));
        mobileField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("mobile")));
        Assert.assertTrue(mobileField.isDisplayed(), "Mobile textbox not visible in desktop view.");
        System.out.println("TC_023 - Desktop view: Mobile number textbox fits.");

        // Tablet view
        driver.manage().window().setSize(new Dimension(768, 1024));
        mobileField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("mobile")));
        Assert.assertTrue(mobileField.isDisplayed(), "Mobile textbox not visible in tablet view.");
        System.out.println("TC_023 - Tablet view: Mobile number textbox fits.");

        // Mobile view
        driver.manage().window().setSize(new Dimension(375, 667));
        mobileField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("mobile")));
        Assert.assertTrue(mobileField.isDisplayed(), "Mobile textbox not visible in mobile view.");
        System.out.println("TC_023 - Mobile view: Mobile number textbox fits.");
    }
}
