package stepDefinitions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.DashboardPage;
import pages.LoginPage;
import pages.LogoutPage;
import pages.ProfilePage;
import org.testng.Assert;
import java.util.Random;
import org.openqa.selenium.Keys;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import utilities.PropertyReader;

public class StepDefinitions {

    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private LogoutPage logoutPage;
    private ProfilePage profilePage;
    private PropertyReader propertyReader;
    private String setName;
    private static final Logger logger = LoggerFactory.getLogger(StepDefinitions.class);

    public StepDefinitions() {
        driver = Hooks.getDriver();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        logoutPage = new LogoutPage(driver);
        profilePage = new ProfilePage(driver);
        propertyReader = new PropertyReader("config.properties");
    }

    @Given("I am on the Homepage")
    public void i_open_the_github_homepage() {
        try {
            driver.get(propertyReader.getProperty("base.url"));
            logger.info("Opened the homepage: {}", propertyReader.getProperty("base.url"));
        } catch (Exception e) {
            logger.error("Error opening the homepage: {}", e.getMessage());
        }
    }

    @Given("I click on the {string} button")
    public void click_on_button(String button) throws InterruptedException {
        try {
            switch (button.toLowerCase()) {
                case "signin":
                    loginPage.clickSignIn();
                    logger.info("Clicked on Sign In button");
                    break;
                case "submitsignin":
                    loginPage.submitSignInButton();
                    logger.info("Clicked on Submit Sign In button");
                    break;
                case "profileimage":
                    logoutPage.clickedEditImg();
                    logger.info("Clicked on profile image");
                    break;
                case "saveprofile":
                    profilePage.setSaveButton();
                    logger.info("Clicked on Save Profile button");
                    break;
                default:
                    logger.warn("Button {} not recognized", button);
            }
        } catch (Exception e) {
            logger.error("Error clicking on button {}: {}", button, e.getMessage());
        }
    }

    @Then("I enter a valid {string}")
    public void enter_valid_credentials(String field) {
        try {
            if (field.equalsIgnoreCase("username")) {
                String username = propertyReader.getProperty("login.username");
                logger.info("Entering username: {}", username);
                loginPage.enterUsername(username);
            } else if (field.equalsIgnoreCase("password")) {
                String password = propertyReader.getProperty("login.password");
                logger.info("Entering password");
                loginPage.enterPassword(password);
            }
        } catch (Exception e) {
            logger.error("Error entering valid credentials: {}", e.getMessage());
        }
    }

    @Then("I enter an invalid {string}")
    public void enter_invalid_credentials(String field) {
        try {
            if (field.equalsIgnoreCase("invalidusername")) {
                loginPage.enterUsername(propertyReader.getProperty("incorrect.user"));
                logger.info("Entering invalid username");
            } else if (field.equalsIgnoreCase("invalidpassword")) {
                loginPage.enterPassword(propertyReader.getProperty("incorrect.password"));
                logger.info("Entering invalid password");
            }
        } catch (Exception e) {
            logger.error("Error entering invalid credentials: {}", e.getMessage());
        }
    }

    @Then("I should be redirected to the Dashboard")
    public void validate_dasboard_page() {
        try {
            String actualMessage = loginPage.successNavigateDashboard();
            Assert.assertTrue(actualMessage.contains("Dashboard"), "Login failed");
            logger.info("Successfully redirected to the Dashboard");
        } catch (Exception e) {
            logger.error("Error during dashboard validation: {}", e.getMessage());
        }
    }

    @Then("I should see an error message indicating invalid credentials")
    public void validate_invalid_credentail_message() {
        try {
            String actualMessage = loginPage.getErrorMessage();
            Assert.assertTrue(actualMessage.contains("Incorrect username or password."),
                    "Expected error message for invalid login not displayed.");
            logger.info("Displayed error message for invalid credentials");
        } catch (Exception e) {
            logger.error("Error validating invalid credential message: {}", e.getMessage());
        }
    }

    @Then("I validate all the links and button should be visible on Dashboard")
    public void validate_all_links_buttons() {
        try {
            Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard is not displayed!");
            Assert.assertTrue(dashboardPage.isDashboardButtonPresent(), "Dashboard Button is not present!");
            Assert.assertTrue(dashboardPage.isDashboardButtonCreate(), "Another Dashboard Button is not present!");
            logger.info("All dashboard elements are validated successfully");
        } catch (Exception e) {
            logger.error("An error occurred while verifying dashboard elements: {}", e.getMessage());
            Assert.fail("Test failed due to: " + e.getMessage());
        }
    }

    @Then("I scroll to the element and click on it")
    public void scroll_to_the_view_and_click() throws InterruptedException {
        try {
            logoutPage.scrollToElementAndClick();
            String updatedSignOutPage = logoutPage.setSignOutPage();
            Assert.assertTrue(updatedSignOutPage.contains("Sign out from all accounts"), "Sign out unsuccessful");
            logger.info("Scrolled to element and clicked successfully");
            Thread.sleep(20000); // Consider avoiding Thread.sleep
        } catch (Exception e) {
            logger.error("Error scrolling and clicking: {}", e.getMessage());
        }
    }

    @Then("I navigate to the Profile section and click on the Edit Profile icon")
    public void navigate_to_section() {
        try {
            profilePage.iconImgEdit();
            profilePage.setEditYourProfile();
            profilePage.setEditProfile();
            logger.info("Navigated to the Profile section and clicked on Edit Profile icon");
        } catch (Exception e) {
            logger.error("Error navigating to Profile section: {}", e.getMessage());
        }
    }

    @When("I update the {string} for Profile section")
    public void update_field(String str) throws InterruptedException {
        try {
            if (str.equalsIgnoreCase("name")) {
                profilePage.clickNameField();
                Random random = new Random();
                int randomNumber = 10000 + random.nextInt(90000);
                setName = propertyReader.getProperty("updated.name") + "-" + randomNumber;
                Actions actions = new Actions(driver);
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
                profilePage.setNameField(setName);
                logger.info("Updated the name field with: {}", setName);
            }
        } catch (Exception e) {
            logger.error("Error updating field {}: {}", str, e.getMessage());
        }
    }

    @Then("I validate that the updated name is visible after refreshing the page")
    public void validateUpdatedNameVisible() {
        try {
            driver.navigate().refresh();
            String updatedName = profilePage.getUpdatedName();
            logger.info("Validating updated name. Expected: {}, Actual: {}", setName, updatedName);
            Assert.assertTrue(updatedName.contains(setName), "Updated name does not match expected value.");
        } catch (Exception e) {
            logger.error("Error validating updated name visibility: {}", e.getMessage());
        }
    }
}
