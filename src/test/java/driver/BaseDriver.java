package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

import static utils.PropertiesDataParser.loadProperties;
import static utils.TakeScreenshot.screenshotForAllure;


public class BaseDriver {
    public static WebDriver driver = null;

    @BeforeSuite
    public void driverSetup() {
        Properties properties = loadProperties("config.properties");
        String browser = properties.getProperty("browser");
        boolean headless_browser = properties.getProperty("headless-browser").equals("true");

        ArrayList<String> headless_options = new ArrayList<>();
        headless_options.add("--headless");
        headless_options.add("--window-size=1920, 1080");

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                if (headless_browser) {
                    chromeOptions.addArguments(headless_options);
                }
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--remote-allow-origins=*");
                if (headless_browser) {
                    firefoxOptions.addArguments(headless_options);
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                if (headless_browser) {
                    edgeOptions.addArguments(headless_options);
                }
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new RuntimeException(browser + "not found");
        }

        driver.manage().window().maximize();
        driver.get(properties.getProperty("base-url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @AfterMethod
    public void checkFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            screenshotForAllure(result.getName());
        }
    }

//    @AfterSuite
//    public void driverTeardown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

}
