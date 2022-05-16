package prestaShop;

import core.EventDriver;
import helper.CustomUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.BasePage;

public class BaseTest {

    public static final int secondsToWaitLoading = 20;

    private static final ThreadLocal<SoftAssertions> SOFT_ASSERTIONS_THREAD_LOCAL_LOCAL = new ThreadLocal<>();

    public static SoftAssertions getSoftAssertion() {
        return SOFT_ASSERTIONS_THREAD_LOCAL_LOCAL.get();
    }

    @BeforeMethod
    public synchronized void setUp() {

        SOFT_ASSERTIONS_THREAD_LOCAL_LOCAL.set(new SoftAssertions());

        WebDriver original;

        String browser = System.getProperty("browser");
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                original = new FirefoxDriver(firefoxOptions);
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                SafariOptions safariOptions = new SafariOptions();
                original = new SafariDriver(safariOptions);
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                original = new ChromeDriver(chromeOptions);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + browser);
        }

        WebDriverListener listener = new EventDriver();
        WebDriver driver = new EventFiringDecorator(listener).decorate(original);

        String resolution = System.getProperty("resolution");
        if (resolution.equals("full")) {
            driver.manage().window().maximize();
        } else {
            Dimension dimension =  CustomUtils.getDimensionsFromString(resolution);
            if (dimension.width != 0 && dimension.height != 0) {
                driver.manage().window().setSize(dimension);
            }
        }
        driver.get("https://demo.prestashop.com/");
        BasePage.setDriverThreadLocal(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void quit() {
        if (BasePage.getJSExecutorThreadLocal() != null) {
            BasePage.getJSExecutorThreadLocal().remove();
        }
        if (BasePage.getDriverThreadLocal() != null) {
            BasePage.getDriverThreadLocal().get().quit();
            BasePage.getDriverThreadLocal().remove();
        }
        SOFT_ASSERTIONS_THREAD_LOCAL_LOCAL.remove();
    }
}
