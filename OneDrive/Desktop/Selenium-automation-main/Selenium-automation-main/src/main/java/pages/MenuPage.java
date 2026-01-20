package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MenuPage {
    private WebDriver driver;
    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");

    public MenuPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openMenu() {
        try {
            driver.findElement(menuButton).click();
            Thread.sleep(500);
        } catch (Exception e) {
            throw new RuntimeException("Could not open menu", e);
        }
    }

    public void clickLogout() {
        try {
            driver.findElement(logoutLink).click();
            Thread.sleep(500);
        } catch (Exception e) {
            throw new RuntimeException("Could not click logout", e);
        }
    }
}
