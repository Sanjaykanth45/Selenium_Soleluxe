package org.testing;


import java.util.Date;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.Assert;

public class TS_048 extends BaseClass {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
    	 System.out.println("BeforeClass - TS_048: Testing the Functionality of Cart Container");
   	  System.out.println("Test case execution started at: " + new Date());

    	
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/user");
    }

    @Test
    public void TC_007() throws InterruptedException {
        WebElement productButton = driver.findElement(By.xpath("//button[@data-product-id ='67ab6ef9df9a5a297700d7b3']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'})", productButton);
        Thread.sleep(1000);

        productButton.click();
        Thread.sleep(1000);

        WebElement cartPage = driver.findElement(By.xpath("//a[@href='/cart']"));
        cartPage.click();
        Thread.sleep(1000);

        WebElement productImage = driver.findElement(By.xpath("//img[@alt='Badminton Shoes']"));
        Assert.assertTrue(productImage.isDisplayed());
        System.out.println("It is Displayed properly");
    }

    @Test
    public void TC_008() throws InterruptedException {
    	
        WebElement productButton = driver.findElement(By.xpath("//button[@data-product-id ='67ab6ef9df9a5a297700d7b3']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'})", productButton);
        Thread.sleep(1000);

        productButton.click();
        Thread.sleep(1000);

        WebElement cartPage = driver.findElement(By.xpath("//a[@href='/cart']"));
        cartPage.click();
        Thread.sleep(1000);

        WebElement productImage = driver.findElement(By.xpath("//img[@alt='Badminton Shoes']"));
        Assert.assertTrue(productImage.isDisplayed());

        WebElement plusSymbol = driver.findElement(By.xpath("//button[@id='plus1']"));
        WebElement minusSymbol = driver.findElement(By.xpath("//button[@id='minus1']"));

        plusSymbol.click();
        plusSymbol.click();
        plusSymbol.click();
        Thread.sleep(1000);
        minusSymbol.click();

        System.out.println("Add To Cart Functionality runs properly");
    }
    
    @Test
    public void TC_009() throws InterruptedException {
        // Step 1: Scroll and add product to cart
        WebElement productButton = driver.findElement(By.xpath("//button[@data-product-id ='67ab6ef9df9a5a297700d7b3']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'})", productButton);
        Thread.sleep(1000);
        productButton.click();
        Thread.sleep(1000);

        // Step 2: Go to cart page
        WebElement cartPage = driver.findElement(By.xpath("//a[@href='/cart']"));
        cartPage.click();
        Thread.sleep(1000);

        // Step 3: Verify product is displayed
        WebElement productImage = driver.findElement(By.xpath("//img[@alt='Badminton Shoes']"));
        Assert.assertTrue(productImage.isDisplayed());

        // Step 4: Capture initial subtotal
        WebElement subtotalElement = driver.findElement(By.className("subtotal-amount"));
        String subtotalTextBefore = subtotalElement.getText().replaceAll("[^0-9]", "");
        int subtotalBefore = Integer.parseInt(subtotalTextBefore);
        System.out.println("Subtotal before: " + subtotalBefore);

        // Step 5: Click + button three times, - button once
        WebElement plusSymbol = driver.findElement(By.id("plus1"));
        WebElement minusSymbol = driver.findElement(By.id("minus1"));
        plusSymbol.click();
        plusSymbol.click();
        plusSymbol.click();
        Thread.sleep(1000);
        minusSymbol.click();
        Thread.sleep(1000);

        // Step 6: Capture updated subtotal
        WebElement updatedSubtotalElement = driver.findElement(By.className("subtotal-amount"));
        String subtotalTextAfter = updatedSubtotalElement.getText().replaceAll("[^0-9]", "");
        int subtotalAfter = Integer.parseInt(subtotalTextAfter);
        System.out.println("Subtotal after: " + subtotalAfter);

        // Step 7: Verify that subtotal has changed
        Assert.assertNotEquals(subtotalBefore, subtotalAfter);
        System.out.println("Total price updates correctly when quantity changes.");
    }

    @Test
    public void TC_010() throws InterruptedException {
        // Step 1: Scroll to the product and add it to cart
        WebElement productButton = driver.findElement(By.xpath("//button[@data-product-id ='67ab6ef9df9a5a297700d7b3']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'})", productButton);
        Thread.sleep(1000);
        productButton.click();
        Thread.sleep(1000);

        // Step 2: Navigate to Cart Page
        WebElement cartPage = driver.findElement(By.xpath("//a[@href='/cart']"));
        cartPage.click();
        Thread.sleep(1000);

        // Step 3: Verify product is present before deletion
        WebElement productImage = driver.findElement(By.xpath("//img[@alt='Badminton Shoes']"));
        Assert.assertTrue(productImage.isDisplayed());
        System.out.println("Product is present in the cart before deletion.");

        // Step 4: Click the Delete button
        WebElement deleteButton = driver.findElement(By.id("deleteButton"));
        deleteButton.click();
        Thread.sleep(1000); // wait for cart to update

        // Step 5: Check product is removed (image not displayed)
        boolean isProductRemoved;
        try {
            driver.findElement(By.xpath("//img[@alt='Badminton Shoes']"));
            isProductRemoved = false;
        } catch (NoSuchElementException e) {
            isProductRemoved = true;
        }

        Assert.assertTrue(isProductRemoved);
        System.out.println("Product removed successfully by clicking the delete button.");
    }
    
    @Test
    public void TC_011() throws InterruptedException {
        // Step 1: Add two different products to the cart
        WebElement productButton1 = driver.findElement(By.xpath("//button[@data-product-id='67ab6ef9df9a5a297700d7b3']")); // Badminton Shoes
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'})", productButton1);
        Thread.sleep(1000);
        productButton1.click();
        Thread.sleep(1000);

        WebElement productButton2 = driver.findElement(By.xpath("//button[@data-product-id='67ab6f87df9a5a297700d7b9']")); // Another product
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'})", productButton2);
        Thread.sleep(1000);
        productButton2.click();
        Thread.sleep(1000);

        // Step 2: Go to cart page
        WebElement cartPage = driver.findElement(By.xpath("//a[@href='/cart']"));
        cartPage.click();
        Thread.sleep(1000);

        // Step 3: Verify both products are displayed
        WebElement productImage1 = driver.findElement(By.xpath("//img[@alt='Badminton Shoes']"));
        WebElement productImage2 = driver.findElement(By.xpath("//img[@alt='Run Shoes White']")); // Replace with actual alt

        Assert.assertTrue(productImage1.isDisplayed());
        Assert.assertTrue(productImage2.isDisplayed());
        System.out.println("Both products are present in the cart.");

        // Step 4: Delete first product
        WebElement deleteButton = driver.findElement(By.id("deleteButton")); // Assumes it deletes the first product
        deleteButton.click();
        Thread.sleep(1000);

        // Step 5: Verify first product is removed
        boolean isFirstProductRemoved;
        try {
            driver.findElement(By.xpath("//img[@alt='Badminton Shoes']"));
            isFirstProductRemoved = false;
        } catch (NoSuchElementException e) {
            isFirstProductRemoved = true;
        }

        Assert.assertTrue(isFirstProductRemoved);

        // Step 6: Verify second product is still present
        WebElement remainingProduct = driver.findElement(By.xpath("//img[@alt='Run Shoes White']")); // Replace with actual alt
        Assert.assertTrue( remainingProduct.isDisplayed());
        System.out.println("Cart updated correctly after deleting an item.");
    }

    @Test
    public void TC_012() throws InterruptedException {
        // Step 1: Add two specific products to the cart
        WebElement productButton1 = driver.findElement(By.xpath("//button[@data-product-id='67ab6ef9df9a5a297700d7b3']")); // Badminton Shoes
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'})", productButton1);
        Thread.sleep(1000);
        productButton1.click();
        Thread.sleep(1000);

        WebElement productButton2 = driver.findElement(By.xpath("//button[@data-product-id='67ab6f87df9a5a297700d7b9']")); // Run Shoes White
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'})", productButton2);
        Thread.sleep(1000);
        productButton2.click();
        Thread.sleep(1000);

        // Step 2: Navigate to cart page
        WebElement cartPageLink = driver.findElement(By.xpath("//a[@href='/cart']"));
        cartPageLink.click();
        Thread.sleep(1000);

        // Step 3: Verify both products are present
        WebElement productImage1 = driver.findElement(By.xpath("//img[@alt='Badminton Shoes']"));
        WebElement productImage2 = driver.findElement(By.xpath("//img[@alt='Run Shoes White']"));

        Assert.assertTrue(productImage1.isDisplayed());
        Assert.assertTrue(productImage2.isDisplayed());
        System.out.println("Both products are present in the cart.");

        // Step 4: Extract displayed subtotal
        WebElement subtotalElement = driver.findElement(By.className("subtotal-amount")); // Assuming className used
        String subtotalText = subtotalElement.getText(); // e.g., ₹2,899.00
        String cleanedSubtotal = subtotalText.replace("₹", "").replace(",", "").trim();
        double displayedSubtotal = Double.parseDouble(cleanedSubtotal);

        // Step 5: Define expected subtotal
        double expectedSubtotal = 1499 + 1400; // Replace with dynamic values if needed

        // Step 6: Assert subtotal matches expected value
        Assert.assertEquals(expectedSubtotal, displayedSubtotal, 0.01);

        System.out.println("Displayed Subtotal: " + displayedSubtotal);
        System.out.println("Expected Subtotal (sum of products): " + expectedSubtotal);
        System.out.println("Cart subtotal is correctly displayed.");
    }


    
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
