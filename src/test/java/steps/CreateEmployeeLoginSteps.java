package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.CommonMethods;

import java.util.List;
import java.util.stream.Collectors;

//extends CommonMethods: inherits similar functions like Webdriver and Selenium
//Step Definitions (Cucumber Framework): allow us to interact with UI

public class CreateEmployeeLoginSteps extends CommonMethods {
    @And("clicks PIM option")
    public void clicksPIMOption() {
        click(dashboardPage.pimTab);
    }

    @And("clicks add employee")
    public void clicksAddEmployee() {
        click(dashboardPage.addEmployeeTab);
    }

    @When("user enters first name as {string}")
    public void UserentersFirstNameAs(String firstName) {
        WebElement firstNameField= driver.findElement(By.id("firstName"));
        firstNameField.click();
        firstNameField.sendKeys(firstName);
    }

    @And("optionally enters middle name as {string}")
    public void optionallyEntersMiddleNameAs(String middleName) {
        WebElement middleNameField=driver.findElement(By.id("middleName"));
        middleNameField.click();
        middleNameField.sendKeys(middleName);
    }

    @And("enters last name as {string}")
    public void entersLastNameAs(String lastName) {
        WebElement lastNameField=driver.findElement(By.id("lastName"));
        lastNameField.click();
        lastNameField.sendKeys(lastName);
    }

    @And("checks the {string} checkbox")
    public void checksTheCheckbox(String loginDetails) {
        WebElement createLoginCheckbox=driver.findElement(By.id("chkLogin"));
        createLoginCheckbox.click();
    }

    @And("enters username as {string}")
    public void entersUsernameAs(String userName) {
        WebElement empUsernameField=driver.findElement(By.id("user_name"));
        empUsernameField.click();
        empUsernameField.sendKeys(userName);
    }


    @And("enters password as {string}")
    public void entersPasswordAs(String passWord) {
        WebElement empPasswordField=driver.findElement(By.id("user_password"));
        empPasswordField.click();
        empPasswordField.sendKeys(passWord);
    }

    @And("user enters confirm password as {string}")
    public void userEntersConfirmPasswordAs(String confirmPass) {
        WebElement confirmPassword= driver.findElement(By.id("re_password"));
        confirmPassword.click();
        confirmPassword.sendKeys(confirmPass);
    }

    @And("selects enabled from the status dropdown")
    public void selectsFromTheStatusDropdown() {
        WebElement dropdownElement = driver.findElement(By.id("status"));
        Select statusDropdown = new Select(dropdownElement);
        statusDropdown.selectByVisibleText("Enabled");
    }

    @And("clicks on save button")
    public void clicksOnSaveButton() {
        WebElement saveButton=driver.findElement(By.id("btnSave"));
        saveButton.click();
    }

    @Then("user is redirected to Personal Details page")
    public void userIsRedirectedToPage() {
        WebElement personalDetails= driver.findElement(By.xpath("//h1[text()='Personal Details']"));
        Assert.assertEquals("Personal Details", personalDetails.getText());
    }

    @When("user enters {string} in the last name field")
    public void userEntersInTheLastNameField(String lastNameTextbox) {
        WebElement lastNameText=driver.findElement(By.id("lastName"));
        lastNameText.click();
        lastNameText.sendKeys(lastNameTextbox);
    }

    @And("user leaves first name field empty")
    public void userLeavesFirstNameFieldEmpty() {
        WebElement firstNameField= driver.findElement(By.id("firstName"));
        firstNameField.click();
        firstNameField.sendKeys(" ");
    }

    @And("user leaves last name field empty")
    public void userLeavesLastNameFieldEmpty() {
        WebElement lastNameField=driver.findElement(By.id("lastName"));
        lastNameField.click();
        lastNameField.clear();
    }


    @Then("user sees an error message in firstname field")
    public void userSeesAnErrorMessageInFirstnameField() {
        WebElement validationErrorMsg=driver.findElement(By.xpath("//span[@for='firstName' and contains(@class, 'validation-error')]"));
        Assert.assertEquals("Required", validationErrorMsg.getText());
    }

    @Then("user sees an error message in lastname field")
    public void userSeesAnErrorMessageInLastnameField() {
        WebElement validationErrorMsg=driver.findElement(By.xpath("//span[@for='lastName' and contains(@class, 'validation-error')]"));
        Assert.assertEquals("Required", validationErrorMsg.getText());
    }

    @When("user clicks on create login details checkbox")
    public void userClicksOnCheckbox() {
        WebElement checkbox=driver.findElement(By.id("chkLogin"));
        checkbox.click();
    }

    @And("error message appears {string}")
    public void errorMessageAppears(String errorMsg) {
        WebElement errorMessage=driver.findElement(By.xpath("//span[@for='user_password' and text()='Should have at least 8 characters']"));
        Assert.assertEquals(errorMsg, errorMessage.getText().trim());
    }


    @And("sees a error message {string}")
    public void seesAErrorMessage(String errorMsg) {
        WebElement errorMessage=driver.findElement(By.xpath("//span[@for='user_name' and contains(text(), 'Should have at least 5 characters')]"));
        Assert.assertEquals(errorMsg, errorMessage.getText().trim());
    }


    @Then("the Status dropdown should show {string} and {string}")
    public void theStatusDropdownShouldShowAnd(String enabled, String disabled) {
        WebElement dropdownElement=driver.findElement(By.id("status"));
        Select select = new Select(dropdownElement);

        List<String> optionTexts = select.getOptions().stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();

        Assert.assertTrue(optionTexts.contains(enabled));
        Assert.assertTrue(optionTexts.contains(disabled));
    }

    @Then("the {string}, {string}, and {string} fields should be enabled")
    public void theAndFieldsShouldBeEnabled(String username, String password, String confirmPass) {
        WebElement usernameField = driver.findElement(By.id("user_name"));
        WebElement passwordField = driver.findElement(By.id("user_password"));
        WebElement confirmPassField = driver.findElement(By.id("re_password"));

        Assert.assertTrue("Username field is not enabled", usernameField.isEnabled());
        Assert.assertTrue("Password field is not enabled", passwordField.isEnabled());
        Assert.assertTrue("Confirm Password field is not enabled", confirmPassField.isEnabled());
    }

    @When("user unchecks the {string} checkbox")
    public void userUnchecksTheCheckbox(String loginDetailsBox) {
        WebElement checkboxLoginDetails=driver.findElement(By.id("chkLogin"));
        if (checkboxLoginDetails.isSelected()) {
            checkboxLoginDetails.click();
        }
    }

    @Then("the {string}, {string}, and {string} fields should be disabled")
    public void theAndFieldsShouldBeDisabled(String username, String password, String confirmPass) {
        WebElement usernameField = driver.findElement(By.id("user_name"));
        WebElement passwordField = driver.findElement(By.id("user_password"));
        WebElement confirmPassField = driver.findElement(By.id("re_password"));

        Assert.assertTrue(username, usernameField.isEnabled());
        Assert.assertTrue(password, passwordField.isEnabled());
        Assert.assertTrue(confirmPass, confirmPassField.isEnabled());
    }

    @Then("password message is displayed {string}")
    public void passwordMessageIsDisplayed(String passwordMessage) {
        WebElement passMessage= driver.findElement(By.xpath("//span[text()='For a strong password, please use a hard to guess combination of text with upper and lower case characters, symbols and numbers']\n"));
        Assert.assertEquals(passwordMessage, passMessage.getText().trim());
    }

    @And("sees password error message {string}")
    public void seesPasswordErrorMessage(String noMatchMsg) {
        WebElement passwordsDoNotMatchMsg=driver.findElement(By.xpath("//span[text()='Passwords do not match']\n"));
        Assert.assertEquals(noMatchMsg, passwordsDoNotMatchMsg.getText().trim());
    }
}


