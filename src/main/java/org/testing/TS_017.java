package org.testing;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Date;
import java.util.List;

public class TS_017 {
	
	@BeforeClass
    public void browserOpen() {
        System.out.println("BeforeClass - TS_017:Testing the Functionality of Gender Dropdown");
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

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // TC_030: Check if gender dropdown is clickable and displays options
    @Test(priority = 1)
    public void TC_030_genderDropdownOptionsVisible() {
        WebElement genderDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("gender")));
        genderDropdown.click(); // Open the dropdown if needed

        List<WebElement> options = genderDropdown.findElements(By.tagName("option"));
        boolean hasMale = false, hasFemale = false, hasOther = false;

        for (WebElement option : options) {
            String text = option.getText().trim();
            if (text.equals("Male")) hasMale = true;
            if (text.equals("Female")) hasFemale = true;
            if (text.equals("Other")) hasOther = true;
        }

        Assert.assertTrue(hasMale && hasFemale && hasOther, "Gender options not all present.");
        System.out.println("TC_030 - Pass: Gender dropdown shows Male, Female, and Other.");
    }

    // TC_031: Select an option from the dropdown and verify
    @Test(priority = 2)
    public void TC_031_selectGenderOption() {
        WebElement genderDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.name("gender")));
        Select select = new Select(genderDropdown);
        select.selectByVisibleText("Male");

        String selected = select.getFirstSelectedOption().getText();
        Assert.assertEquals(selected, "Male", "'Male' was not selected.");
        System.out.println("TC_031 - Pass: 'Male' option is selected successfully.");
    }

    // TC_032: Check default value is "Select Gender"
    @Test
    public void TC_032_defaultGenderOption() {
        WebElement genderDropdown = driver.findElement(By.name("gender"));
        Select select = new Select(genderDropdown);
        String selectedOption = select.getFirstSelectedOption().getText();
        Assert.assertEquals(selectedOption, "Select Gender", "Default gender is not 'Select Gender'");
    }

}
