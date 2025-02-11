package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage extends BasePage {
    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    By finish_button = By.id("finish");

    public void click_on_finish() {
        clickOnElement(finish_button);
    }

}
