package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private WebDriver driver;
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillForm(String firstName, String lastName, String postalCode) {
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        wait.until(d -> driver.findElement(firstNameField).isDisplayed());
        driver.findElement(firstNameField).sendKeys(firstName);
        wait.until(d -> driver.findElement(lastNameField).isDisplayed());
        driver.findElement(lastNameField).sendKeys(lastName);
        wait.until(d -> driver.findElement(postalCodeField).isDisplayed());
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }
}
