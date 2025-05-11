package org.testing;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Date;

public class TS_013 {
	
	@BeforeClass
    public void browserOpen() {
        System.out.println("BeforeClass - TS_013:Testing the Functionality of Password Text Box");
        System.out.println("Test case execution started at: " + new Date());
    }

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/");  
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // TC_018 - Test if the Password textbox accepts input and masks characters
    @Test(priority = 1)
    public void TC_018_passwordTextboxMasksCharacters() {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));

        // Type a password in the password textbox
        String password = "Password123!";
        passwordField.sendKeys(password);

        // Check if the password is hidden by verifying the input type is "password"
        String inputType = passwordField.getAttribute("type");
        Assert.assertEquals(inputType, "password", "Password textbox does not mask the typed characters.");
        System.out.println("TC_018 - Pass: Password Textbox accepts input and masks characters (shown as dots)");
    }

    // TC_019 - Test if the Password textbox validates minimum password length
    @Test(priority = 2)
    public void TC_019_passwordTextboxMinimumLength() {
        // Fill all required fields except password (use short password)
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        WebElement mobileField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("mobile")));
        WebElement genderSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("gender")));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("register-button")));

        usernameField.clear();
        usernameField.sendKeys("ShortPwdUser");

        emailField.clear();
        emailField.sendKeys("shortpwd@example.com");

        mobileField.clear();
        mobileField.sendKeys("9999999999");

        Select genderDropdown = new Select(genderSelect);
        genderDropdown.selectByVisibleText("Male");

        passwordField.clear();
        passwordField.sendKeys("123");  // Short password to trigger validation

        submitButton.click();

        // Wait for error message about password length
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[contains(text(),'Password must be at least 6 characters long')]")
        ));
        String errorText = errorMessage.getText();
        Assert.assertTrue(errorText.contains("Password must be at least 6 characters"), "Error message for short password not shown.");
        System.out.println("TC_019 - Pass: Form shows error for short password.");
    }


    // TC_020 - Test if the Password textbox accepts a strong password format
    @Test(priority = 3)
    public void TC_020_passwordTextboxStrongPassword() {
        // Fill username
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        usernameField.clear();
        usernameField.sendKeys("testersample");

        // Fill email
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        emailField.clear();
        emailField.sendKeys("testuser123@gmail.com");

        // Fill mobile number
        WebElement mobileField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("mobile")));
        mobileField.clear();
        mobileField.sendKeys("9876543210");

        // Select gender from dropdown
        WebElement genderDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("gender")));
        Select genderSelect = new Select(genderDropdown);
        genderSelect.selectByVisibleText("Male"); // Change to the exact text used in your dropdown

        // Fill password
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        passwordField.clear();
        String strongPassword = "Str0ngPass!@#";
        passwordField.sendKeys(strongPassword);

        // Click register button
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("register-button")));
        submitButton.click();

        // Assert success message
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@class='register-alert' and text()='Registration Successful!']")
        ));
        Assert.assertTrue(successMessage.isDisplayed(), "Form did not accept the strong password and complete registration.");
        System.out.println("TC_020 - Pass: Registration succeeded with strong password and all required fields.");
    }


}
