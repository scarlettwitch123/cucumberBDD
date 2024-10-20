package stepDefinitions;

import java.time.Duration;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before("@FeatureSetup")
    public void setUp() {
        try {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            logger.info("ChromeDriver setup completed and browser window maximized.");
        } catch (Exception e) {
            logger.error("Error during setup: {}", e.getMessage());
        }
    }

    @After("@FeatureSetup")
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
                logger.info("Browser closed successfully.");
            }
        } catch (Exception e) {
            logger.error("Error during tear down: {}", e.getMessage());
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
