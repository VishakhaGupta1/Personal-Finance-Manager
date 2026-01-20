package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage {
    private WebDriver driver;
    private By totalLabel = By.className("summary_total_label");
    private By finishButton = By.id("finish");
    private By successMessage = By.className("complete-header");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTotalAmount() {
        return driver.findElement(totalLabel).getText();
    }

    public void clickFinish() {
        driver.findElement(finishButton).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }
}
