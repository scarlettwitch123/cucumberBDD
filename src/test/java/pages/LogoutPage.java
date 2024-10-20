package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class LogoutPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(LogoutPage.class);

    @FindBy(xpath = "//img[@class='avatar circle']")
    private WebElement editImg;

    @FindBy(xpath = "//span[contains(text(),'Sign out')]")
    private WebElement signOut;

    @FindBy(xpath = "//a[@class='HeaderMenu-link HeaderMenu-button d-inline-flex d-lg-none flex-order-1 f5 no-underline border color-border-default rounded-2 px-2 py-1 color-fg-inherit js-prevent-focus-on-mobile-nav']")
    private WebElement refreshSignIn;

    @FindBy(xpath = "//input[@value='Sign out from all accounts']")
    private WebElement signOutPage;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void clickedEditImg() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(editImg)).click();
            logger.info("Clicked on edit image.");
        } catch (Exception e) {
            logger.error("Failed to click on edit image: {}", e.getMessage());
        }
    }

    public void scrollToElementAndClick() {
        try {
            Thread.sleep(2000); // Consider replacing with WebDriverWait
            wait.until(ExpectedConditions.visibilityOf(signOut));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", signOut);
            wait.until(ExpectedConditions.elementToBeClickable(signOut)).click();
            logger.info("Scrolled to and clicked sign out.");
        } catch (Exception e) {
            logger.error("Failed to scroll to and click sign out: {}", e.getMessage());
        }
    }

    public String getUpdatedPage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(refreshSignIn));
            String pageText = refreshSignIn.getText();
            logger.info("Retrieved updated page text: {}", pageText);
            return pageText;
        } catch (Exception e) {
            logger.error("Failed to get updated page text: {}", e.getMessage());
            return null; // Or handle as appropriate
        }
    }

    public String setSignOutPage() {
        try {
            Thread.sleep(2000); // Consider replacing with WebDriverWait
            String message = wait.until(ExpectedConditions.visibilityOf(signOutPage)).getAttribute("value");
            logger.info("Retrieved sign out page value: {}", message);
            return message;
        } catch (Exception e) {
            logger.error("Failed to get sign out page value: {}", e.getMessage());
            return null; // Or handle as appropriate
        }
    }
}
