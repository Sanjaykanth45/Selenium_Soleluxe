package org.testing;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.time.Duration;

public class TS_041 extends BaseClass{

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
    	 System.out.println("BeforeClass - TS_041: Testing About Section Functionality");
    	 System.out.println("Test case execution started at: " + new Date());

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://localhost:3000/user");
    }

    private void scrollToAboutSection() {
        WebElement aboutSection = driver.findElement(By.id("about"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", aboutSection);
    }

    // TC_004 – Check if the About cards display the correct content
    @Test(priority = 1)
    public void testAboutCardsDisplayCorrectContent() {
        scrollToAboutSection();
        List<WebElement> cards = driver.findElements(By.cssSelector("#about .value-item"));
        Assert.assertEquals(cards.size(), 4, "There should be 4 About cards.");

        for (WebElement card : cards) {
            String text = card.getText().trim();
            Assert.assertFalse(text.isEmpty(), "Card content should not be empty.");
        }
    }

    // TC_005 – Check if About cards are clickable (linked or interactive)
    @Test(priority = 2)
    public void TC_005_testAboutCardsAreClickable() throws IOException {
        scrollToAboutSection();
        List<WebElement> cards = driver.findElements(By.cssSelector("#about .value-item"));
        screenShot("TC_005_testAboutCardsAreClickable");
        boolean atLeastOneClickable = false;
        for (WebElement card : cards) {
            try {
                card.click(); // Try clicking
                atLeastOneClickable = true;
                break; 
            } catch (WebDriverException e) {
               
            }
        }

        Assert.assertTrue(atLeastOneClickable, "At least one About card should be clickable or interactive.");
    }

    // TC_006 – Check for hover effect on About cards
    @Test(priority = 3)
    public void testAboutCardsHoverEffect() {
        scrollToAboutSection();
        List<WebElement> cards = driver.findElements(By.cssSelector("#about .value-item"));
        Actions actions = new Actions(driver);

        for (WebElement card : cards) {
            actions.moveToElement(card).perform();

            // Optional: Check for a style change (e.g., scale, color)
            String transform = card.getCssValue("transform");
            if (transform != null && !transform.equals("none")) {
                Assert.assertTrue(true, "Hover effect is present.");
                return;
            }
        }

        Assert.fail("No hover effect detected on any About card.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
