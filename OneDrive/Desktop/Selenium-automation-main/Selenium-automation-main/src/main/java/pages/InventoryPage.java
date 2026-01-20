package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class InventoryPage {
    private WebDriver driver;
    private By filterDropdown = By.className("product_sort_container");
    private By addToCartButtons = By.xpath("//button[contains(text(),'Add to cart')]");
    private By cartIcon = By.cssSelector(".shopping_cart_badge");
    private By itemNames = By.className("inventory_item_name");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectFilter(String filterText) {
        WebElement dropdown = driver.findElement(filterDropdown);
        dropdown.click();
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        wait.until(d -> dropdown.findElement(By.xpath("//option[text()='" + filterText + "']")).isDisplayed());
        dropdown.findElement(By.xpath("//option[text()='" + filterText + "']")).click();
    }

    public void addItemToCart(String itemName) {
        String dataTestName = "add-to-cart-" + itemName.toLowerCase().replaceAll(" ", "-").replaceAll("[()'.]", "").replaceAll("-+", "-");
        By addBtnLocator = By.cssSelector("button[data-test='" + dataTestName + "']");
        
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement addBtn = wait.until(d -> d.findElement(addBtnLocator));
            addBtn.click();
            System.out.println("Added to cart: " + itemName);
            Thread.sleep(1000); // Brief pause for cart update
        } catch (Exception e) {
            System.out.println("Failed to add item to cart: " + itemName + ". Error: " + e.getMessage());
            throw new RuntimeException("Could not add item: " + itemName, e);
        }
    }

    public int getCartCount() {
        try {
            List<WebElement> badges = driver.findElements(cartIcon);
            if (badges.size() > 0 && badges.get(0).isDisplayed()) {
                return Integer.parseInt(badges.get(0).getText());
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    public void clickItem(String itemName) {
        List<WebElement> items = driver.findElements(itemNames);
        for (WebElement item : items) {
            if (item.getText().equals(itemName)) {
                org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5));
                wait.until(d -> item.isDisplayed() && item.isEnabled());
                item.click();
                break;
            }
        }
    }
}
