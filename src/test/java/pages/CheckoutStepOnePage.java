package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage extends BasePage {
    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    By first_name_input = By.id("first-name");
    By last_name_input = By.id("last-name");
    By postal_code_input = By.id("postal-code");
    By continue_button = By.id("continue");

    public void add_information(String first_name, String last_name, String postal_code) {
        sendText(first_name_input, first_name);
        sendText(last_name_input, last_name);
        sendText(postal_code_input, postal_code);
    }

    public void click_on_continue() {
        clickOnElement(continue_button);
    }

}
