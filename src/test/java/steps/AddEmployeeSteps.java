package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.CommonMethods;

public class AddEmployeeSteps extends CommonMethods {

    @When("user clicks on Add employee option")
    public void user_clicks_on_add_employee_option() {
        click(dashboardPage.addEmployeeTab);

    }
    @When("user enters firstname {string} and lastname {string}")
    public void user_enters_firstname_and_lastname(String firstName, String lastName) {
        sendText(firstName, addEmployeePage.firstNameLocator);
        sendText(lastName, addEmployeePage.lastNameLocator);
    }

    @Then("the system should generate a unique employee ID")
    public void the_system_should_generate_a_unique_employee_id() {
        Assert.assertTrue(addEmployeePage.employeeId.isDisplayed());

    }

    @Then("the employee should be added successfully")
    public void the_employee_should_be_added_successfully() {
        Assert.assertTrue(addEmployeePage.employeeId.isDisplayed());
    }

    @Then("user providing a unique employee ID")
    public void user_providing_a_unique_employee_id() {
        String empId = generateUniqueEmployeeId();
        sendText(empId,addEmployeePage.addEmpId);
    }
    @When("user enters incomplete or invalid information")
    public void user_enters_incomplete_or_invalid_information() {
        sendText(" ", addEmployeePage.firstNameLocator);
        sendText("Rogers", addEmployeePage.lastNameLocator);

    }

    @Then("user can see the error message Required")
    public void user_can_see_the_error_message_required() {
        Assert.assertTrue(addEmployeePage.firstNameError.isDisplayed());
    }



}
