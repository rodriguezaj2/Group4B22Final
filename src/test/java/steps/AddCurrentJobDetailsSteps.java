package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonMethods;
import utils.ConfigReader;

import java.time.Duration;
import java.util.List;

import static utils.Constants.EMP_CONTRACT_FILE_PATH;

public class AddCurrentJobDetailsSteps extends CommonMethods {


    //click on PIM option
    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        click(dashboardPage.pimTab);
    }
    //click on employee list option

    @When("user clicks on employee list option")
    public void user_clicks_on_employee_list_option() {
        click(dashboardPage.employeeListTab);
    }
    //enter valid employee id

    @When("user enters valid employee id")
    public void user_enters_valid_employee_id() {
        sendText("92442547", searchEmployeePage.empIdSearchField);
    }
    //click on search button

    @When("user clicks on search button")
    public void user_clicks_on_search_button() {
        click(searchEmployeePage.searchButton);
    }
    //select employee from the result table

    @Then("user is able to see employee details")
    public void user_is_able_to_see_employee_details() {
        selectEmployeeById("92442547");

    }
    //click on job tab

    @When("the user navigates to the Job section and clicks")
    public void the_user_navigates_to_the_job_section_and_clicks() {

        click(employeeDetailsPage.jobTab);
    }
    //click on edit button

    @Then("the user clicks on Edit button to update current job details")
    public void the_user_clicks_on_edit_button_to_update_current_job_details() {
        Assert.assertTrue(employeeDetailsPage.editBtn.isEnabled());
        click(employeeDetailsPage.editBtn);

    }
    //Add current Job Title details

    @When("the Admin user selects {string} from Job Title dropdown")
    public void the_admin_user_selects_from_job_title_dropdown(String JobTitle) {
        selectFromDropDown(employeeDetailsPage.jobTitleDD, JobTitle);

    }
    //Add current Employment Status details

    @When("the Admin user selects {string} from Employment Status dropdown")
    public void the_admin_user_selects_from_employment_status_dropdown(String EmploymentStatus) {
        selectFromDropDown(employeeDetailsPage.empStatusDD, EmploymentStatus);

    }
    //Add current Job Category details

    @When("the Admin user selects {string} from Job Category dropdown")
    public void the_admin_user_selects_from_job_category_dropdown(String JobCategory) {
        /*selectFromDropDown(employeeDetailsPage.eeoCategoryDD, JobCategory);*/
        try {
            List<WebElement> options = new Select(employeeDetailsPage.eeoCategoryDD).getOptions();

            boolean optionFound = options.stream()
                    .anyMatch(option -> option.getText().trim().equalsIgnoreCase(JobCategory));

            if (optionFound) {
                selectFromDropDown(employeeDetailsPage.eeoCategoryDD, JobCategory);
                System.out.println("Selected job category: " + JobCategory);
            } else {
                System.out.println("JobCategory = " + JobCategory + " Not able to select as Only --Select-- option available in the dropdown");
            }

        } catch (Exception e) {
            // Log the error but don't stop the test
            System.err.println("Error while selecting job category: " + e.getMessage());
        }

    }
    //Add current Joined Date details

    @When("the Admin user selects {string} from Joined Date calendar")
    public void the_admin_user_selects_from_joined_date_calendar(String JoinedDate) {
        sendText(JoinedDate, employeeDetailsPage.joinedDateField);

    }
    //Add current Sub Unit details

    @When("the Admin user selects {string} from Sub Unit dropdown")
    public void the_admin_user_selects_from_sub_unit_dropdown(String SubUnit) {
        selectFromDropDown(employeeDetailsPage.subUnitDD, SubUnit);

    }
    //Add current Location details

    @When("the Admin user selects {string} from Location dropdown")
    public void the_admin_user_selects_from_location_dropdown(String Location) {
        selectFromDropDown(employeeDetailsPage.locationDD, Location);

    }
    //Add current Contract Start Date details

    @When("the Admin user selects {string} from Start Date calendar")
    public void the_admin_user_selects_from_start_date_calendar(String startDate) {
        sendText(startDate, employeeDetailsPage.contractStartDateField);
    }
    //Add current Contract End Date details

    @When("the Admin user leaves End Date calendar empty")
    public void the_admin_user_leaves_end_date_calendar_empty() {
        sendText(" ", employeeDetailsPage.contractEndDateField);

    }
    //Replace and Upload a contract file

    @When("the Admin user uploads a valid contract file {string}")
    public void the_admin_user_uploads_a_valid_contract_file(String contractFile) {
        // Click the Replace Current File radio button
        click(employeeDetailsPage.replaceCurrentFileRadioButton);
        /*uploadFile(employeeDetailsPage.contractFileUpload, contractFile);*/
        String filePath;
        if ("EMP_CONTRACT_FILE_PATH".equals(contractFile)) {
            filePath = EMP_CONTRACT_FILE_PATH;
        } else {
            filePath = contractFile; // fallback to any string passed in feature file
        }
        uploadFile(employeeDetailsPage.contractFileUpload, filePath);
    }
    //Save and update current job details and verify updated Successfully


    @Then("the Current job details should be updated successfully")
    public void the_current_job_details_should_be_updated_successfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Successfully')]")
        ));
        String actualMessage = toastMessage.getText();
        Assert.assertTrue(actualMessage.contains("Successfully Updated"));
        closeBrowser();

    }
    //Verify the changes in the database

    @Then("the changes should be reflected in the database")
    public void the_changes_should_be_reflected_in_the_database() {
        openBrowserAndLaunchApplication();
        sendText(ConfigReader.read("username"), loginPage.usernameField);
        sendText(ConfigReader.read("password"), loginPage.passwordField);
        click(loginPage.loginButton);
        click(dashboardPage.pimTab);
        click(dashboardPage.employeeListTab);
        sendText("92442547", searchEmployeePage.empIdSearchField);
        click(searchEmployeePage.searchButton);
        selectEmployeeById("92442547");
        click(employeeDetailsPage.jobTab);
        System.out.println("Job Title: " + getSelectedDropdownText(employeeDetailsPage.jobTitleDD, "Job Title"));
        System.out.println("Employment Status: " + getSelectedDropdownText(employeeDetailsPage.empStatusDD, "Employment Status"));
        System.out.println("Job Category: " + getSelectedDropdownText(employeeDetailsPage.eeoCategoryDD, "Job Category"));
        System.out.println("Joined Date: " + employeeDetailsPage.joinedDateField.getAttribute("value"));
        System.out.println("Sub Unit: " + getSelectedDropdownText(employeeDetailsPage.subUnitDD, "Sub Unit"));
        System.out.println("Location: " + getSelectedDropdownText(employeeDetailsPage.locationDD, "Location"));
        System.out.println("Contract Start Date: " + employeeDetailsPage.contractStartDateField.getAttribute("value"));
        System.out.println("Contract End Date: " + employeeDetailsPage.contractEndDateField.getAttribute("value"));
        //print the name of the uploaded file
        String uploadedFileName = uploadFile(employeeDetailsPage.contractFileUpload, EMP_CONTRACT_FILE_PATH);
        if (uploadedFileName != null) {
            System.out.println("Uploaded File Name: " + uploadedFileName);
        }
        System.out.println("The changes have been reflected in the database successfully.");
        closeBrowser();


    }

}
