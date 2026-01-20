package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemDetailsPage {
    private WebDriver driver;
    private By addToCartButton = By.cssSelector("button[data-test^='add-to-cart-']");

    public ItemDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addToCart() {
        try {
            org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            org.openqa.selenium.WebElement btn = wait.until(d -> d.findElement(addToCartButton));
            btn.click();
            System.out.println("Added item from details page");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Failed to add item from details page: " + e.getMessage());
            throw new RuntimeException("Could not add item from details page", e);
        }
    }
}
