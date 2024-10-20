package pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProfilePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(ProfilePage.class);

    @FindBy(xpath = "//img[@class='avatar circle']")
    private WebElement editImg;

    @FindBy(xpath = "//span[contains(text(),'Your profile')]")
    private WebElement yourProfile;

    @FindBy(xpath = "//button[normalize-space()='Edit profile']")
    private WebElement editProfile;

    @FindBy(xpath = "//input[@id='user_profile_name']")
    private WebElement nameField;

    @FindBy(xpath = "//button[@class='Button--primary Button--small Button d-inline-flex']")
    private WebElement saveButton;

    @FindBy(xpath = "//span[@class='p-name vcard-fullname d-block overflow-hidden']")
    private WebElement updatedName;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void iconImgEdit() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(editImg)).click();
            logger.info("Clicked on edit image icon.");
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Edit image icon not found or not clickable: {}", e.getMessage());
        }
    }

    public void setEditYourProfile() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(yourProfile)).click();
            logger.info("Clicked on 'Your Profile' option.");
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Your Profile option not found or not clickable: {}", e.getMessage());
        }
    }

    public void setEditProfile() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(editProfile)).click();
            logger.info("Clicked on edit profile button.");
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Edit profile button not found or not clickable: {}", e.getMessage());
        }
    }

    public void clickNameField() {
        try {
            wait.until(ExpectedConditions.visibilityOf(nameField)).click();
            logger.info("Clicked on name field.");
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Name field not found or not visible: {}", e.getMessage());
        }
    }

    public void setNameField(String username) throws InterruptedException {
        try {
            wait.until(ExpectedConditions.visibilityOf(nameField));
            nameField.sendKeys(username);
            logger.info("Set name field to: {}", username);
            Thread.sleep(1000);
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Name field not found or not visible: {}", e.getMessage());
        }
    }

    public void setSaveButton() throws InterruptedException {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
            logger.info("Clicked on save button.");
            Thread.sleep(1000);
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Save button not found or not clickable: {}", e.getMessage());
        }
    }

    public void navigatePage() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
            logger.info("Navigated using save button.");
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Navigation button not found or not clickable: {}", e.getMessage());
        }
    }

    public String getUpdatedName() {
        try {
            wait.until(ExpectedConditions.visibilityOf(updatedName));
            String name = updatedName.getText();
            logger.info("Retrieved updated name: {}", name);
            return name;
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Updated name element not found or not visible: {}", e.getMessage());
            return "";
        }
    }
}
