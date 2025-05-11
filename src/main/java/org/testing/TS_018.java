package org.testing;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.Assert;

import java.time.Duration;
import java.util.Date;

public class TS_018 {
	
	@BeforeClass
    public void browserOpen() {
        System.out.println("BeforeClass - TS_018:Testing the UI features of Register button");
        System.out.println("Test case execution started at: " + new Date());
    }
	
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://localhost:3000/");  
    }

   

    // TC_033 - Check if Register button is visible
    @Test
    public void TC_033_registerButtonVisible() {
        WebElement registerButton = driver.findElement(By.cssSelector("button[type='submit']"));
        Assert.assertTrue(registerButton.isDisplayed(), "Register button is not visible");
    }

    // TC_034 - Check placement and alignment of Register button
    @Test
    public void TC_034_registerButtonPlacementAndAlignment() {
        WebElement registerButton = driver.findElement(By.cssSelector("button[type='submit']"));
        Point location = registerButton.getLocation();
        Dimension size = registerButton.getSize();

        System.out.println("Button location: " + location);
        System.out.println("Button size: " + size);

        Assert.assertTrue(location.getX() > 0 && location.getY() > 0, "Button is misplaced");
        Assert.assertTrue(size.getWidth() > 80, "Button width too small");
        Assert.assertTrue(size.getHeight() > 25, "Button height too small");
    }

    // TC_035 - Check responsiveness of Register button
    @Test
    public void TC_035_registerButtonResponsiveness() {
        WebElement registerButton = driver.findElement(By.cssSelector("button[type='submit']"));

        // Desktop view
        driver.manage().window().setSize(new Dimension(1200, 800));
        Assert.assertTrue(registerButton.isDisplayed(), "Button not visible in desktop view");

        // Tablet view
        driver.manage().window().setSize(new Dimension(768, 1024));
        Assert.assertTrue(registerButton.isDisplayed(), "Button not visible in tablet view");

        // Mobile view
        driver.manage().window().setSize(new Dimension(375, 667));
        Assert.assertTrue(registerButton.isDisplayed(), "Button not visible in mobile view");
    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
