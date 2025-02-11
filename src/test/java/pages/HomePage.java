package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;


public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    By hamburger_menu_button = By.id("react-burger-menu-btn");
    By inventory_sidebar_link = By.id("inventory_sidebar_link");
    By logout_button = By.id("logout_sidebar_link");
    By add_to_cart_backpack_button = By.id("add-to-cart-sauce-labs-backpack");
    By add_to_cart_bike_light_button = By.id("add-to-cart-sauce-labs-bike-light");
    By remove_from_cart_backpack_button = By.id("remove-sauce-labs-backpack");
    By remove_from_cart_bike_light_button = By.id("remove-sauce-labs-bike-light");
    By shopping_cart_link = By.className("shopping_cart_link");
    By shopping_cart_badge = By.className("shopping_cart_badge");
    By product_sort_container = By.className("product_sort_container");
    By inventory_item_name  = By.className("inventory_item_name");
    By inventory_item_price = By.className("inventory_item_price");



    public void add_product_to_cart() {
        clickOnElement(add_to_cart_backpack_button);
        clickOnElement(add_to_cart_bike_light_button);
    }

    public void remove_product_from_cart() {
        clickOnElement(remove_from_cart_backpack_button);
        clickOnElement(remove_from_cart_bike_light_button);
    }

    public void go_to_cart_page() {
        clickOnElement(shopping_cart_link);
    }

    public String get_shopping_cart_badge() {
        return getText(shopping_cart_badge);
    }

    public boolean is_shopping_cart_badge_exists() {
        return getElementCount(shopping_cart_badge) == 1;
    }

    public void perform_logout() {
        clickOnElement(hamburger_menu_button);
        clickOnElement(logout_button);
    }

    public void move_to_home() {
        clickOnElement(hamburger_menu_button);
        clickOnElement(inventory_sidebar_link);
    }

    public void short_product(String select_value) {
        selectByValue(product_sort_container, select_value);
    }

    public ArrayList<String> get_inventory_item_name_list() {
        return getListOfText(inventory_item_name);
    }

    public ArrayList<Float> get_inventory_item_price_list() {
        ArrayList<Float> new_inventory_item_price_list = new ArrayList<>();
        ArrayList<String> inventory_item_price_list = getListOfText(inventory_item_price);
        for (String price : inventory_item_price_list ) {
            new_inventory_item_price_list.add(Float.parseFloat(price.replace("$", "")));
        }
        return new_inventory_item_price_list;
    }

}
