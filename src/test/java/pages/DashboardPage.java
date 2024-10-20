package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(DashboardPage.class);

    @FindBy(xpath = "//a[contains(@class,'AppHeader-context-item')]")
    private WebElement dashboardElement;

    @FindBy(xpath = "//span[contains(text(),'New')]")
    private WebElement dashboardNewButton;

    @FindBy(xpath = "//span[contains(text(),'Create repository')]")
    private WebElement dashboardNewButtonCreate;

    @FindBy(xpath = "(//span[contains(text(),'New')])[1]")
    private WebElement newRepository;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public boolean isDashboardDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(dashboardElement));
            boolean isDisplayed = dashboardElement.isDisplayed();
            logger.info("Dashboard displayed: {}", isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.error("Error checking if dashboard is displayed: {}", e.getMessage());
            return false;
        }
    }

    public boolean isDashboardButtonPresent() {
        try {
            wait.until(ExpectedConditions.visibilityOf(dashboardNewButton));
            boolean isDisplayed = dashboardNewButton.isDisplayed();
            logger.info("Dashboard button present: {}", isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.error("Error checking if dashboard button is present: {}", e.getMessage());
            return false;
        }
    }

    public boolean isDashboardButtonCreate() {
        try {
            WebElement button = wait.until(ExpectedConditions.visibilityOf(newRepository));
            boolean isEnabled = button.isEnabled();
            logger.info("Create repository button enabled: {}", isEnabled);
            return isEnabled;
        } catch (Exception e) {
            logger.error("Error checking if create repository button is enabled: {}", e.getMessage());
            return false;
        }
    }
}
