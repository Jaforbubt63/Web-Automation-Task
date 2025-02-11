package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public int getElementCount(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        return elements.size();
    }

    public void sendText(By locator, String value) {
        WebElement element = getElement(locator);
        element.clear();
        element.sendKeys(value);
    }
    public void sendText(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    public void clickOnElement(By locator) {
        WebElement element = getElement(locator);
        element.click();
    }

    public void clickOnElement(WebElement element) {
        element.click();
    }

    public String getAttribute(By locator, String attribute) {
        WebElement element = getElement(locator);
        return element.getAttribute(attribute);
    }

    public String getText(By locator) {
        return getElement(locator).getText().trim();
    }

    public ArrayList<String> getListOfText(By locator) {
        ArrayList<String> text_list = new ArrayList<>();
        for (WebElement element : getElements(locator)) {
            text_list.add(element.getText().trim());
        }
        return text_list;
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public String getAlertText() {
        return driver.switchTo().alert().getText().trim();
    }

    public void sendTextToAlert(String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    public void selectByIndex(By select_locator, int index) {
        Select select = new Select(getElement(select_locator));
        select.selectByIndex(index);
    }

    public void selectByValue(By select_locator, String value) {
        Select select = new Select(getElement(select_locator));
        select.selectByValue(value);
    }

    public void selectByVisibleText(By select_locator, String visible_text) {
        Select select = new Select(getElement(select_locator));
        select.selectByVisibleText(visible_text);
    }

    public void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
    }

    public void scrollToElement(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()", getElement(locator));
    }

    public void waitForVisibilityOfElement(By locator, int... maxWait) {
        int waitTime = (maxWait.length > 0) ? maxWait[0] : 30;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForVisibilityOfElements(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void waitForInvisibilityOfElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForInvisibilityOfElements(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForTextToBePresentInElement(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public boolean isElementVisible(By locator) {
        try {
            waitForVisibilityOfElement(locator, 5);
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public void switchTab(int tabNo) {
        ArrayList<String> allTab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(allTab.get(tabNo));
    }

    public void switchFrame(By frame_locator) {
        WebElement frame_element = getElement(frame_locator);
        driver.switchTo().frame(frame_element);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public String getCssValue(By locator, String css_property) {
        WebElement element = getElement(locator);
        return element.getCssValue(css_property).trim();
    }

    public void dragAndDrop(By source_locator, By target_locator) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(getElement(source_locator), getElement(target_locator))
                .build().perform();
    }


}