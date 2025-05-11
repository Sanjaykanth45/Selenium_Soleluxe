package org.testing;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TS_028 {
	
	

    WebDriver driver;

    @BeforeClass
    public void setUp() throws InterruptedException {
    	System.out.println("BeforeClass - TS_028: Testing the Functionality of Exit");
        System.out.println("Test case execution started at: " + new Date());

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/user");


    }

    // TC_022 – Check if user logs out
    @Test(priority = 1)
    public void TC_022_exitLogsUserOut() throws InterruptedException {
        Thread.sleep(1000);
        WebElement exitBtn = driver.findElement(By.cssSelector(".svg-inline--fa.fa-arrow-right-from-bracket.icon.logout-icon"));
        exitBtn.click();

        Thread.sleep(1000);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"), "User was not logged out successfully.");
    }

    // TC_023 – Check if redirected to login page
    @Test(priority = 2)
    public void TC_023_redirectToLoginAfterExit() throws InterruptedException {
        driver.get("http://localhost:3000/user");


        // Click logout
        WebElement exitBtn = driver.findElement(By.cssSelector(".svg-inline--fa.fa-arrow-right-from-bracket.icon.logout-icon"));
        exitBtn.click();
        Thread.sleep(1000);

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"), "User was not redirected to login page after logout.");
    }

    // TC_024 – Check if session is cleared after exit
    @Test(priority = 3)
    public void TC_024_sessionClearedAfterExit() throws InterruptedException {
        driver.get("http://localhost:3000/user");


        // Logout
        WebElement exitBtn = driver.findElement(By.cssSelector(".svg-inline--fa.fa-arrow-right-from-bracket.icon.logout-icon"));
        exitBtn.click();
        Thread.sleep(1000);

        // Try accessing protected page directly
        driver.get("http://localhost:3000/user"); // Protected page
        Thread.sleep(1000);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("login"), "User was able to access protected page after logout.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
