package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    public LoginPage (WebDriver driver) {
        super(driver);
    }


    By email_input = By.id("user-name");
    By password_input = By.id("password");
    By login_button = By.id("login-button");
    By error_button = By.xpath("//h3[@data-test='error']");



    public void perform_login(String email, String password) {
        sendText(email_input, email);
        sendText(password_input, password);
        clickOnElement(login_button);
    }

    public String get_error_message(){
        return getText(error_button);
    }

}
