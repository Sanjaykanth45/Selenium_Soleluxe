package org.testing;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Date;

public class TS_010 extends BaseClass  {
	
	 @BeforeClass
	    public void browserOpen() {
	        System.out.println("BeforeClass - TS_010:Testing the UI features of Email Text Box");
	        System.out.println("Test case execution started at: " + new Date());
	    }

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://localhost:3000");  // Update if the URL is different
    }

   

    // TC_009 - Check visibility of email textbox
    @Test(priority = 1)
    public void TC_009_checkEmailTextboxIsVisible() {
        WebElement emailBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        Assert.assertTrue(emailBox.isDisplayed(), "Email textbox should be visible");
        System.out.println("TC_009 - Pass: Email textbox is visible on the signup page");
    }

    // TC_010 - Check alignment (example check using location and size)
    @Test(priority = 2)
    public void TC_010_checkEmailTextboxAlignment() {
        WebElement emailBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        Point location = emailBox.getLocation();
        Dimension size = emailBox.getSize();

        // Check it's not placed off-screen (you can add your own logic here)
        Assert.assertTrue(location.getX() >= 0 && location.getY() >= 0, "Textbox is not positioned properly");
        Assert.assertTrue(size.getWidth() > 100, "Textbox width should be reasonable for visibility");

        System.out.println("TC_010 - Pass: Email textbox is properly placed and aligned");
    }

    // TC_011 - Check responsiveness on different screen sizes
    @Test(priority = 3)
    public void TC_011_checkEmailTextboxResponsiveDesign() {
        int[][] screenSizes = {
            {1920, 1080},  // Desktop
            {1366, 768},   // Laptop
            {768, 1024},   // Tablet
            {375, 667}     // Mobile
        };

        for (int[] size : screenSizes) {
            driver.manage().window().setSize(new Dimension(size[0], size[1]));
            WebElement emailBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
            Assert.assertTrue(emailBox.isDisplayed(), "Email textbox should be visible at resolution: " + size[0] + "x" + size[1]);
        }

        System.out.println("TC_011 - Pass: Email textbox is responsive across screen sizes");
    }
    
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
