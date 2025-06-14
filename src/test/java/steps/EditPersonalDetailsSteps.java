package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonMethods;
import utils.ConfigReader;

import java.time.Duration;


public class EditPersonalDetailsSteps extends CommonMethods {
    // This class will contain step definitions for editing personal details
    // It will interact with the page objects to perform actions and assertions

    // Navigate to HRMS login page

    @Given("the ESS user is on the HRMS login page")
    public void the_ess_user_is_on_the_hrms_login_page() {
        openBrowserAndLaunchApplication();

    }
    // Log in with valid credentials

    @When("the user logs in with valid credentials")
    public void the_user_logs_in_with_valid_credentials() {

        sendText(ConfigReader.read("essUsername"), loginPage.usernameField);
        sendText(ConfigReader.read("essPassword"), loginPage.passwordField);
    }
    // Click the login button and verify redirection to dashboard

    @Then("the user should be redirected to the dashboard")
    public void the_user_should_be_redirected_to_the_dashboard() {
        click(loginPage.loginButton);

    }
    // Navigate to My Info section

    @Given("the user navigates to the My Info section and clicks")
    public void the_user_navigates_to_the_my_info_section_and_clicks() {
        click(editPersonalDetailsPage.MyInfo);
        Assert.assertTrue(editPersonalDetailsPage.MyInfo.isDisplayed());

    }
    // Click the Edit button to update personal information

    @Then("the user clicks Edit update personal information")
    public void the_user_clicks_edit_update_personal_information() throws InterruptedException {
        Assert.assertTrue(editPersonalDetailsPage.editButton.isEnabled());
        click(editPersonalDetailsPage.editButton);


    }
    // Verify that First Name, Middle Name, and Last Name fields are editable and update them

    @Then("the First name {string},Middle Name {string} and Last Name {string} field should be editable")
    public void the_first_name_middle_name_and_last_name_field_should_be_editable(String FirstName, String MiddleName, String LastName) {
        Assert.assertTrue(editPersonalDetailsPage.firstNameField.isEnabled());
        sendText(FirstName, editPersonalDetailsPage.firstNameField);
        Assert.assertTrue(editPersonalDetailsPage.middleNameField.isEnabled());
        sendText(MiddleName, editPersonalDetailsPage.middleNameField);
        Assert.assertTrue(editPersonalDetailsPage.lastNameField.isEnabled());
        sendText(LastName, editPersonalDetailsPage.lastNameField);

    }
    // Select Gender from radio buttons

    @Then("the user should be able to select gender as {string}")
    public void the_user_should_be_able_to_select_gender_as(String Gender) {
        editPersonalDetailsPage.selectGender(Gender);


    }
    // Select Nationality from dropdown

    @Then("the user should be able to select nationality as {string}")
    public void the_user_should_be_able_to_select_nationality_as(String Nationality) {
        Assert.assertTrue(editPersonalDetailsPage.nationalityDropdown.isEnabled());
        selectFromDropDown(editPersonalDetailsPage.nationalityDropdown,Nationality);


    }
    // Select Marital Status from dropdown

    @Then("the user should be able to select marital status as {string}")
    public void the_user_should_be_able_to_select_marital_status_as(String MaritalStatus) {
        Assert.assertTrue(editPersonalDetailsPage.maritalStatusDropdown.isEnabled());
        selectFromDropDown(editPersonalDetailsPage.maritalStatusDropdown, MaritalStatus);

    }
    // Click the Save button to save changes

    @Then("the user clicks the save button")
    public void the_user_clicks_the_save_button() {
        click(editPersonalDetailsPage.saveButton);

    }
    // Verify that personal details are updated successfully

    @Then("the personal details should be updated successfully")
    public void the_personal_details_should_be_updated_successfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("pdMainContainer")
        ));
        String actualMessage = toastMessage.getText();
        Assert.assertTrue(actualMessage.contains("Successfully Saved"));
        closeBrowser();
    }
    // Verify that updated details are reflected in the database


    @Then("the updated details should be reflected in the database")
    public void the_updated_details_should_be_reflected_in_the_database() throws InterruptedException {
        //search in the HRMS database and compare the values

        openBrowserAndLaunchApplication();
        sendText(ConfigReader.read("username"), loginPage.usernameField);
        sendText(ConfigReader.read("password"), loginPage.passwordField);
        click(loginPage.loginButton);
        click(dashboardPage.pimTab);
        click(dashboardPage.employeeListTab);
        sendText("61743965", searchEmployeePage.empIdSearchField);
        click(searchEmployeePage.searchButton);
        selectEmployeeById("61743965");
        System.out.println("First Name: " + editPersonalDetailsPage.firstNameField.getAttribute("value"));
        System.out.println("Middle Name: " + editPersonalDetailsPage.middleNameField.getAttribute("value"));
        System.out.println("Last Name: " + editPersonalDetailsPage.lastNameField.getAttribute("value"));
        // Print selected Gender from radio buttons
        if (editPersonalDetailsPage.femaleRadioButton.isSelected())
        {System.out.println("Gender: Female");
        } else if (editPersonalDetailsPage.maleRadioButton.isSelected()) {
            System.out.println("Gender: Male");
                } else {
                    System.out.println("Gender: Not Selected");
                }
                // Print selected Nationality from dropdown
        System.out.println("Nationality: " + getSelectedDropdownText(editPersonalDetailsPage.nationalityDropdown,"Nationality"));
        // Print selected Marital Status from dropdown
        System.out.println("Marital Status: " + getSelectedDropdownText(editPersonalDetailsPage.maritalStatusDropdown, "Marital Status"));
        closeBrowser();
            }


        }


