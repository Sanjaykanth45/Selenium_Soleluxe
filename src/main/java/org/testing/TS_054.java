package org.testing;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

public class TS_054 {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {

   	 System.out.println("BeforeClass - TS_052: Testing the Functionality of PaymentDropdown");
	   	  System.out.println("Test case execution started at: " + new Date());

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/checkout");
    }

    // TC_023 – verify you can select each payment method and it shows correctly
    @Test
    public void testPaymentDropdownSelection() {
        Select paymentSelect = new Select(driver.findElement(By.name("paymentMethod")));

        // Iterate through all options and verify selection
        for (WebElement option : paymentSelect.getOptions()) {
            String value = option.getAttribute("value");
            paymentSelect.selectByValue(value);

            // After selecting, the currently selected option should match
            WebElement selected = paymentSelect.getFirstSelectedOption();
            Assert.assertEquals(selected.getAttribute("value"), value,
                "Dropdown did not select value=" + value);
        }
    }

    // TC_024 – verify default/pre-selected payment method (if any)
    @Test
    public void testDefaultPaymentMethodPreselected() {
        Select paymentSelect = new Select(driver.findElement(By.name("paymentMethod")));
        WebElement defaultOption = paymentSelect.getFirstSelectedOption();

        // Replace "cod" below with whatever your app should default to; 
        // if none expected, assert that it’s the first in the list
        String expectedDefault = "cod"; 
        Assert.assertEquals(defaultOption.getAttribute("value"), expectedDefault,
            "Default payment method should be '" + expectedDefault + "'");
    }

    // TC_025 – verify that changing the selection updates correctly
    @Test
    public void testPaymentDropdownUpdatesOnChange() {
        Select paymentSelect = new Select(driver.findElement(By.name("paymentMethod")));

        // start with one method
        paymentSelect.selectByValue("cod");
        Assert.assertEquals(paymentSelect.getFirstSelectedOption().getAttribute("value"), "cod");

        // change to UPI
        paymentSelect.selectByValue("upi");
        Assert.assertEquals(paymentSelect.getFirstSelectedOption().getAttribute("value"), "upi");

        // change to Card
        paymentSelect.selectByValue("card");
        Assert.assertEquals(paymentSelect.getFirstSelectedOption().getAttribute("value"), "card");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
