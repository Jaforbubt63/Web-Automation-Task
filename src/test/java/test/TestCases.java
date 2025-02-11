package test;

import driver.BaseDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class TestCases extends BaseDriver {


    @Test(priority = 1)
    public void verify_login_and_logout() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.perform_login("standard_user", "secret_sauce");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

        homePage.perform_logout();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");

        loginPage.perform_login("wrong_user", "secret_sauce");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.get_error_message(), "Epic sadface: Username and password do not match any user in this service");
        Thread.sleep(2000);

    }

    @Test(priority = 2)
    public void verify_add_to_cart() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);

        loginPage.perform_login("standard_user", "secret_sauce");
        homePage.add_product_to_cart();
        Assert.assertEquals(homePage.get_shopping_cart_badge(), "2");
        Thread.sleep(2000);

        homePage.remove_product_from_cart();
        Assert.assertFalse(homePage.is_shopping_cart_badge_exists());

        homePage.add_product_to_cart();
        homePage.go_to_cart_page();

        ArrayList<String> expected_inventory_item = new ArrayList<>();
        expected_inventory_item.add("Sauce Labs Backpack");
        expected_inventory_item.add("Sauce Labs Bike Light");
        Assert.assertEquals(cartPage.get_inventory_item_name_list(), expected_inventory_item);
    }

    @Test(priority = 3)
    public void verify_checkout_process() {
        CartPage cartPage = new CartPage(driver);
        CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage(driver);
        CheckoutStepTwoPage checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);

        cartPage.processed_to_checkout();
        checkoutStepOnePage.add_information("Abu", "Jafor", "123");
        checkoutStepOnePage.click_on_continue();
        checkoutStepTwoPage.click_on_finish();

        Assert.assertEquals(checkoutCompletePage.get_complete_header(), "Thank you for your order!");

    }

    @Test(priority = 4)
    public void sort_product() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.move_to_home();

        ArrayList<String> inventory_item_name_list = homePage.get_inventory_item_name_list();
        homePage.short_product("az");
        ArrayList<String> inventory_item_name_list_asc = new ArrayList<>(inventory_item_name_list);
        Collections.sort(inventory_item_name_list_asc);
        Assert.assertEquals(homePage.get_inventory_item_name_list(), inventory_item_name_list_asc);
        Thread.sleep(2000);

        homePage.short_product("za");
        ArrayList<String> inventory_item_name_list_desc = new ArrayList<>(inventory_item_name_list_asc);
        Collections.reverse(inventory_item_name_list_desc);
        Assert.assertEquals(homePage.get_inventory_item_name_list(), inventory_item_name_list_desc);
        Thread.sleep(2000);

        ArrayList<Float> inventory_item_price_list = homePage.get_inventory_item_price_list();
        homePage.short_product("lohi");
        ArrayList<Float> inventory_item_price_list_asc = new ArrayList<>(inventory_item_price_list);
        Collections.sort(inventory_item_price_list_asc);
        Assert.assertEquals(homePage.get_inventory_item_price_list(), inventory_item_price_list_asc);
        Thread.sleep(2000);

        homePage.short_product("hilo");
        ArrayList<Float> inventory_item_price_list_desc = new ArrayList<>(inventory_item_price_list_asc);
        Collections.reverse(inventory_item_price_list_desc);
        Assert.assertEquals(homePage.get_inventory_item_price_list(), inventory_item_price_list_desc);

    }

    @Test(priority = 5)
    public void verify_locked_user() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        homePage.perform_logout();
        loginPage.perform_login("locked_out_user", "secret_sauce");
        Assert.assertEquals(loginPage.get_error_message(), "Epic sadface: Sorry, this user has been locked out.");

    }

}
