package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;


public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    By inventory_item_name_list = By.className("inventory_item_name");
    By checkout_button = By.id("checkout");


    public ArrayList<String> get_inventory_item_name_list() {
        return getListOfText(inventory_item_name_list);
    }

    public void processed_to_checkout() {
        clickOnElement(checkout_button);
    }

}
