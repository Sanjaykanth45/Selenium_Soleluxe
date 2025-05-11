package org.testing;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Date;

public class TS_015 {
	
	@BeforeClass
    public void browserOpen() {
        System.out.println("BeforeClass - TS_015:Testing the Functionality of Mobile number Text Box");
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

    @AfterMethod
    public void refreshPage() {
        driver.navigate().refresh();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    // Common method to fill mandatory fields (except mobile)
    private void fillMandatoryFieldsExceptMobile() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("TestUsertwo");
        driver.findElement(By.name("email")).sendKeys("testusertwo@example.com");
        driver.findElement(By.name("password")).sendKeys("Str0ngPass!@#");
        new Select(driver.findElement(By.name("gender"))).selectByVisibleText("Male");
    }

    @Test(priority = 1)
    public void TC_024_mobileTextboxOnlyAcceptsNumbers() {
        fillMandatoryFieldsExceptMobile();
        WebElement mobileField = driver.findElement(By.name("mobile"));
        mobileField.sendKeys("abc@#123");
        String enteredValue = mobileField.getAttribute("value");

        Assert.assertTrue(enteredValue.matches("\\d*"), "Mobile textbox allows non-numeric input.");
        System.out.println("TC_024 - Pass: Mobile number textbox accepts only numeric input.");
    }

    @Test(priority = 2)
    public void TC_025_mobileTextboxMaxLength() {
        fillMandatoryFieldsExceptMobile();
        WebElement mobileField = driver.findElement(By.name("mobile"));
        mobileField.sendKeys("123456789012345");
        String enteredValue = mobileField.getAttribute("value");

        Assert.assertTrue(enteredValue.length() <= 10, "Mobile number textbox allows more than 10 digits.");
        System.out.println("TC_025 - Pass: Mobile number textbox restricts input to 10 digits.");
    }

    @Test(priority = 3)
    public void TC_026_mobileTextboxIsRequired() {
        fillMandatoryFieldsExceptMobile();
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("register-button")));
        submitButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'Mobile number is required')]")));

        Assert.assertTrue(errorMessage.isDisplayed(), "Required field error not shown for mobile number.");
        System.out.println("TC_026 - Pass: Error is displayed when mobile number is not entered.");
    }
}
