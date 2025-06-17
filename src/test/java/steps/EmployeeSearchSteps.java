package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import utils.CommonMethods;


public class EmployeeSearchSteps extends CommonMethods {

    @When("user enters valid employee full name")
    public void user_enters_valid_employee_full_name() {
        sendText("Faylin Maksana", employeeSearchPage.empSearchNameField);
    }

    @When("user enters valid employee partial name")
    public void userEntersValidEmployeePartialName() {
        sendText("fay", employeeSearchPage.empSearchNameField);
    }

    @When("user enters valid employee ID")
    public void user_enters_valid_employee_ID() {
        sendText("92501805", employeeSearchPage.empSearchIdField);
    }

    @When("user enters invalid employee details")
    public void userEntersInvalidEmployeeDetails() throws InterruptedException {
        Thread.sleep(5000);
        employeeSearchPage.empSearchNameField.clear();
        sendText("Benji", employeeSearchPage.empSearchNameField);
        Thread.sleep(2000);
        employeeSearchPage.empSearchNameField.sendKeys(Keys.TAB);
        Thread.sleep(2000);
        /*click(employeeSearchPage.searchBtn);*/
    }

    @Then("message {string} appears")
    public void messageAppears(String expectedMessage) throws InterruptedException {

        Thread.sleep(9000);
        String actualMessage = employeeSearchPage.noRecord.getText();
        Assert.assertEquals(expectedMessage, actualMessage);

    }
}
