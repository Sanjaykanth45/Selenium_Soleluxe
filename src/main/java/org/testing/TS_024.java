package org.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Date;

public class TS_024 extends BaseClass {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.out.println("BeforeClass - TS_024: Testing Search Box Functionality (Valid, Empty, Special Inputs)");
        System.out.println("Test case execution started at: " + new Date());

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/user");
    }

    // TC_010 – Valid input: "shoes" (Intentionally Failing)
    @Test(priority = 1)
    public void TC_010_searchWithValidInput() throws InterruptedException, Throwable {
        WebElement searchBox = driver.findElement(By.cssSelector(".form-control.search-input"));
        searchBox.clear();
        searchBox.sendKeys("shoes");
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
       screenShot("TC_010_searchWithValidInput");
        Assert.fail("The Search box does not display relevant results for 'Shoes'");
    }

    // TC_011 – Empty input
    @Test(priority = 2)
    public void TC_011_searchWithEmptyInput() throws InterruptedException {
        WebElement searchBox = driver.findElement(By.cssSelector(".form-control.search-input"));
        searchBox.clear();
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        WebElement resultMsg = driver.findElement(By.tagName("body"));
        Assert.assertFalse(resultMsg.getText().toLowerCase().contains("no results") ||
                          resultMsg.getText().toLowerCase().contains("nothing found"),
                "Search should handle empty input gracefully.");
    }

    // TC_012 – Special characters
    @Test(priority = 3)
    public void TC_012_searchWithSpecialCharacters() throws InterruptedException {
        WebElement searchBox = driver.findElement(By.cssSelector(".form-control.search-input"));
        searchBox.clear();
        searchBox.sendKeys("!@#$%^&*()");
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        String bodyText = driver.findElement(By.tagName("body")).getText().toLowerCase();
        Assert.assertTrue(bodyText.contains("no results") || bodyText.contains("nothing found") ||
                          bodyText.contains("0 items"),
                "Search should handle special characters gracefully.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
