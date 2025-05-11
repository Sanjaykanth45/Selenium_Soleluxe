package org.testing;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Date;

public class TS_059 {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
      	 System.out.println("BeforeClass - TS_059: Testing the Functionality of Delivery and Total Cost");
	   	  System.out.println("Test case execution started at: " + new Date());

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/checkout");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void fillCheckoutForm() {
        driver.findElement(By.name("name")).sendKeys("Sanjay");
        driver.findElement(By.name("email")).sendKeys("sanjay@example.com");
        driver.findElement(By.name("address")).sendKeys("1/90 Sathy");
        driver.findElement(By.name("city")).sendKeys("Erode");
        driver.findElement(By.name("state")).sendKeys("Tamil Nadu");
        driver.findElement(By.name("zip")).sendKeys("638402");

        Select paymentSelect = new Select(driver.findElement(By.name("paymentMethod")));
        paymentSelect.selectByValue("cod");
    }

    // TC_038 – Verify delivery cost is added to total cost
    @Test
    public void testDeliveryCostIsAdded() {
        driver.navigate().refresh();
        fillCheckoutForm();

        WebElement deliveryChargeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("DeliveryCharge")));
        double deliveryCharge = Double.parseDouble(deliveryChargeElement.getText().replaceAll("[^\\d.]", ""));

        Assert.assertTrue(deliveryCharge > 0, "Delivery charge should be greater than 0");
    }

    // TC_039 – Verify total = subtotal + delivery cost
    @Test
    public void testTotalCostCalculation() {
        driver.navigate().refresh();
        fillCheckoutForm();

        WebElement subtotalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subtotal")));
        WebElement deliveryChargeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("DeliveryCharge")));
        WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Total")));

        double subtotal = Double.parseDouble(subtotalElement.getText().replaceAll("[^\\d.]", ""));
        double deliveryCharge = Double.parseDouble(deliveryChargeElement.getText().replaceAll("[^\\d.]", ""));
        double total = Double.parseDouble(totalElement.getText().replaceAll("[^\\d.]", ""));

        Assert.assertEquals(total, subtotal + deliveryCharge, "Total cost should equal subtotal + delivery");
    }

    // TC_040 – Check discounts/charges are reflected in total
    @Test
    public void testDiscountsOrExtraChargesReflectedInTotal() {
        driver.navigate().refresh();
        fillCheckoutForm();

        WebElement subtotalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subtotal")));
        WebElement deliveryChargeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("DeliveryCharge")));
        WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Total")));

        double subtotal = Double.parseDouble(subtotalElement.getText().replaceAll("[^\\d.]", ""));
        double deliveryCharge = Double.parseDouble(deliveryChargeElement.getText().replaceAll("[^\\d.]", ""));
        double total = Double.parseDouble(totalElement.getText().replaceAll("[^\\d.]", ""));

        // Example logic assuming subtotal is 3900, delivery is 50, total could be 3950 or less (with discount)
        double expectedMinimum = subtotal + deliveryCharge;
        Assert.assertTrue(total <= expectedMinimum, "Total should reflect discounts if any (<= subtotal + delivery)");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
