package org.testing;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Date;

public class TS_056 {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
    	 System.out.println("BeforeClass - TS_056: Testing the Functionality of Place Order Button");
	   	  System.out.println("Test case execution started at: " + new Date());

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/checkout");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void fillCheckoutForm() {
        driver.findElement(By.name("name")).sendKeys("Sanjay");
        driver.findElement(By.name("email")).sendKeys("Sanjay@gmail.com");
        driver.findElement(By.name("address")).sendKeys("123 Street");
        driver.findElement(By.name("city")).sendKeys("Sathy");
        driver.findElement(By.name("state")).sendKeys("Erode");
        driver.findElement(By.name("zip")).sendKeys("68402");

        Select paymentSelect = new Select(driver.findElement(By.name("paymentMethod")));
        paymentSelect.selectByValue("cod");
    }

    // TC_029 – Check order is submitted when "Place Order" is clicked
    @Test
    public void testPlaceOrderButtonSubmitsOrder() {
        driver.navigate().refresh();
        fillCheckoutForm();

        WebElement placeOrderBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        Assert.assertTrue(placeOrderBtn.isEnabled(), "Place Order button should be enabled");

        placeOrderBtn.click();

        // Expect alert or redirect confirmation
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "Order Placed Successfully!");
        alert.accept();
    }

    // TC_031 – Check confirmation message or redirection after placing order
    @Test
    public void testPlaceOrderTriggersConfirmation() {
        driver.navigate().refresh();
        fillCheckoutForm();

        WebElement placeOrderBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        placeOrderBtn.click();

        // You can validate alert OR URL redirection
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            Assert.assertEquals(alert.getText(), "Order Placed Successfully!");
            alert.accept();
        } catch (TimeoutException e) {
            // Alternatively check if redirected to confirmation page
            wait.until(ExpectedConditions.urlContains("/confirmation"));
            Assert.assertTrue(driver.getCurrentUrl().contains("confirmation"),
                "Expected to be redirected to confirmation page");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
