package org.testing;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Date;

public class TS_046 {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
    	  System.out.println("BeforeClass - TS_046: Testing Contact Form Functionality");
    	  System.out.println("Test case execution started at: " + new Date());

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/user");
    }

    @Test
    public void testNameTextboxAcceptsInput() {
        WebElement nameField = driver.findElement(By.id("senderName"));
        nameField.clear();
        nameField.sendKeys("Sanjay");
        Assert.assertEquals(nameField.getAttribute("value"), "Sanjay");
    }

    @Test
    public void testEmailTextboxAcceptsInput() {
        WebElement emailField = driver.findElement(By.id("senderEmail"));
        emailField.clear();
        emailField.sendKeys("sanjay@gmail.com");
        Assert.assertEquals(emailField.getAttribute("value"), "sanjay@gmail.com");
    }

    @Test
    public void testMessageTextboxAcceptsInput() {
        WebElement messageField = driver.findElement(By.id("senderMessage"));
        messageField.clear();
        messageField.sendKeys("This is a test message.");
        Assert.assertEquals(messageField.getAttribute("value"), "This is a test message.");
    }


    @Test
    public void testSubmitButtonWorksWhenAllFieldsFilled() {
        driver.navigate().refresh();

        WebElement nameField = driver.findElement(By.id("senderName"));
        WebElement emailField = driver.findElement(By.id("senderEmail"));
        WebElement messageField = driver.findElement(By.id("senderMessage"));

        nameField.sendKeys("Sanjay");
        emailField.sendKeys("sanjay@gmail.com");
        messageField.sendKeys("This is a valid message.");

        WebElement submitButton = driver.findElement(By.id("messageSubmit"));
        scrollToElement(submitButton);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        } catch (ElementClickInterceptedException e) {
            // Fallback: click with JS if normal click is intercepted
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
        }

       
    }



    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
