package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage{
    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    By complete_header = By.xpath("//h2[@class='complete-header']");

    public String get_complete_header() {
        return getText(complete_header);
    }

}
