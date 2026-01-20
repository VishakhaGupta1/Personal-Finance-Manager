package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private By cartItems = By.className("cart_item");
    private By cartIcon = By.cssSelector(".shopping_cart_badge");
    private By removeButton = By.xpath(".//button[contains(text(),'Remove')]");
    private By itemPrice = By.className("inventory_item_price");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void removeItemByPriceRange(double min, double max) {
        List<WebElement> items = driver.findElements(cartItems);
        for (WebElement item : items) {
            String priceText = item.findElement(itemPrice).getText().replace("$", "");
            double price = Double.parseDouble(priceText);
            if (price >= min && price <= max) {
                item.findElement(removeButton).click();
                break;
            }
        }
    }

    public int getCartItemsCount() {
        try {
            List<WebElement> items = driver.findElements(cartItems);
            System.out.println("Cart items found: " + items.size());
            return items.size();
        } catch (Exception e) {
            System.out.println("Error getting cart items count: " + e.getMessage());
            return 0;
        }
    }

    public int getCartCount() {
        try {
            org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            List<WebElement> badges = driver.findElements(cartIcon);
            if (badges.size() > 0 && badges.get(0).isDisplayed()) {
                return Integer.parseInt(badges.get(0).getText());
            }
            return 0;
        } catch (Exception e) {
            System.out.println("Exception in getCartCount: " + e.getMessage());
            return getCartItemsCount(); // Fallback to counting items
        }
    }
}
