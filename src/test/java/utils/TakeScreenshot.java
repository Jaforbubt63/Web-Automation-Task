package utils;

import driver.BaseDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

public class TakeScreenshot extends BaseDriver {

    @Step("Taking a screenshot for {0}")
    public static void screenshotForAllure(String screenshot_name) {
        Allure.addAttachment(screenshot_name, new ByteArrayInputStream(((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES)));
    }

}