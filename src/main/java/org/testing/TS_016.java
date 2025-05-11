package org.testing;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Date;

public class TS_016 {
	
	@BeforeClass
    public void browserOpen() {
        System.out.println("BeforeClass - TS_016:Testing the UI features of Gender Text Box");
        System.out.println("Test case execution started at: " + new Date());
    }
	
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver(); 
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

    // TC_027 - Testing the visibility of the Gender dropdown
    @Test(priority = 1)
    public void TC_027_genderDropdownVisibility() {
        WebElement genderDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("gender")));
        Assert.assertTrue(genderDropdown.isDisplayed(), "Gender dropdown is not visible.");
        System.out.println("TC_027 - Pass: Gender dropdown is visible on the register page.");
    }

    // TC_028 - Testing the alignment and placement of the Gender dropdown
    @Test(priority = 2)
    public void TC_028_genderDropdownAlignment() {
        WebElement genderDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("gender")));
        Point location = genderDropdown.getLocation();
        Dimension size = genderDropdown.getSize();

        System.out.println("TC_028 - Location: " + location + ", Size: " + size);
        Assert.assertTrue(location.getX() >= 0 && location.getY() >= 0, "Gender dropdown is misaligned.");
        Assert.assertTrue(size.getHeight() > 0 && size.getWidth() > 0, "Gender dropdown size seems invalid.");
        System.out.println("TC_028 - Pass: Gender dropdown is properly aligned and placed within the UI.");
    }

    // TC_029 - Testing the responsiveness of the Gender dropdown on different screen sizes
    @Test(priority = 3)
    public void TC_029_genderDropdownResponsiveness() {
        WebElement genderDropdown;

        // Desktop view
        driver.manage().window().setSize(new Dimension(1200, 800));
        genderDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("gender")));
        Assert.assertTrue(genderDropdown.isDisplayed(), "Gender dropdown not visible in desktop view.");
        System.out.println("TC_029 - Desktop view: Gender dropdown fits.");

        // Tablet view
        driver.manage().window().setSize(new Dimension(768, 1024));
        genderDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("gender")));
        Assert.assertTrue(genderDropdown.isDisplayed(), "Gender dropdown not visible in tablet view.");
        System.out.println("TC_029 - Tablet view: Gender dropdown fits.");

        // Mobile view
        driver.manage().window().setSize(new Dimension(375, 667));
        genderDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("gender")));
        Assert.assertTrue(genderDropdown.isDisplayed(), "Gender dropdown not visible in mobile view.");
        System.out.println("TC_029 - Mobile view: Gender dropdown fits.");
    }
}
