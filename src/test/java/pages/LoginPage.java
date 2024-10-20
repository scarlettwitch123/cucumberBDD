package pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    @FindBy(xpath = "(//a[contains(text(), 'Sign in')])[2]")
    private WebElement signInButton;

    @FindBy(xpath = "//input[@id='login_field']")
    private WebElement userField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@name='commit']")
    private WebElement submitSignIn;

    @FindBy(xpath = "//div[@role='alert']")
    private WebElement errorMessage;

    @FindBy(xpath = "//a[contains(@class,'AppHeader-context-item')]")
    private WebElement navigateDashboard;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void clickSignIn() throws InterruptedException {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
            logger.info("Clicked on the Sign In button.");
        } catch (Exception e) {
            logger.error("Error clicking Sign In button: {}", e.getMessage());
        }
    }

    public void enterUsername(String username) {
        try {
            wait.until(ExpectedConditions.visibilityOf(userField));
            userField.sendKeys(username);
            logger.info("Entered username: {}", username);
        } catch (Exception e) {
            logger.error("Error entering username: {}", e.getMessage());
        }
    }

    public void enterPassword(String password) {
        try {
            passwordField.sendKeys(password);
            logger.info("Entered password.");
        } catch (Exception e) {
            logger.error("Error entering password: {}", e.getMessage());
        }
    }

    public void submitSignInButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(submitSignIn)).click();
            logger.info("Clicked on the Submit Sign In button.");
        } catch (Exception e) {
            logger.error("Error clicking Submit Sign In button: {}", e.getMessage());
        }
    }

    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            String errorMsg = errorMessage.getText();
            logger.info("Retrieved error message: {}", errorMsg);
            return errorMsg;
        } catch (Exception e) {
            logger.error("Error retrieving error message: {}", e.getMessage());
            return null;
        }
    }

    public String successNavigateDashboard() {
        try {
            wait.until(ExpectedConditions.visibilityOf(navigateDashboard));
            String dashboardText = navigateDashboard.getText();
            logger.info("Successfully navigated to Dashboard: {}", dashboardText);
            return dashboardText;
        } catch (Exception e) {
            logger.error("Error navigating to Dashboard: {}", e.getMessage());
            return null;
        }
    }
}
