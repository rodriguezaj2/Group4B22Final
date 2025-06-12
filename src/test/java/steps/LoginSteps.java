package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.CommonMethods;
import utils.ConfigReader;

public class LoginSteps extends CommonMethods {

    @Given("user is able to access HRMS application")
    public void user_is_able_to_access_hrms_application() {
        openBrowserAndLaunchApplication();
    }
    @When("user enters valid username and password")
    public void user_enters_valid_username_and_password() {
        sendText(ConfigReader.read("userName"), loginPage.usernameField);
        sendText(ConfigReader.read("password"), loginPage.passwordField);

    }
    @When("user clicks login button")
    public void user_clicks_login_button() {
        click(loginPage.loginButton);

    }
    @Then("user is able to see the dashboard page")
    public void user_is_able_to_see_the_dashboard_page() {
        String loginMessage=loginPage.loginMessage.getText();
        Assert.assertEquals(loginMessage,"Dashboard");

    }

    @When("user enters invalid username and password")
    public void user_enters_invalid_username_and_password() {
        sendText("wrongUserName", loginPage.usernameField);
        sendText("wrongPassword", loginPage.passwordField);
    }
    @Then("user sees error message {string}")
    public void user_sees_error_message(String expectedMessage) {
        String errorMessage=loginPage.errorMessage.getText();
        Assert.assertEquals(expectedMessage,errorMessage);
    }

    @When("user leaves username field empty")
    public void user_leaves_username_field_empty() {
        sendText("", loginPage.usernameField);
    }
    @When("user enters password")
    public void user_enters_password() {
        sendText("asdf", loginPage.passwordField);
    }

    @When("user enters username")
    public void user_enters_username() {
        sendText("sad32", loginPage.usernameField);
    }
    @When("user leaves password field empty")
    public void user_leaves_password_field_empty() {
        sendText("", loginPage.passwordField);
    }


}
