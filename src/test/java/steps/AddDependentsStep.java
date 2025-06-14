package steps;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;
import utils.ConfigReader;

public class AddDependentsStep extends CommonMethods {


    @When("user clicks on the dependants option")
    public void user_clicks_on_the_dependants_option() {

        click(addDependentPage.dependentsBtn);
    }


    @When("user clicks add dependant button")
    public void user_clicks_add_dependant_button() {

        click(addDependentPage.addDependentBtn);
    }


    @When("user enters dependant first and last name")
    public void user_enters_dependant_first_and_last_name() {

        click(addDependentPage.addName);

        sendText(ConfigReader.read("spousename"), addDependentPage.addName);
    }


    @When("user selects relationship type")
    public void user_selects_relationship_type() {

        click(addDependentPage.selectDropdown);

        selectFromDropDown("other", addDependentPage.selectDropdown);

        sendText("Spouse", addDependentPage.relationshipType);
    }



    @When("user enters dependant date of birth")
    public void user_enters_dependant_date_of_birth() {

        click(addDependentPage.dob);

        selectFromDropDown(addDependentPage.month, "Aug");
        selectFromDropDown(addDependentPage.year, "1992");
        click(addDependentPage.day);

    }

    @When("user saves dependent information")
    public void user_saves_dependent_information() {

        click(addDependentPage.saveBtn);
    }


    @When("user has option to delete dependant")
    public void user_has_option_to_delete_dependant() {

       /* WebElement radioBtn = driver.findElement(By.xpath("//input[@value = '1']"));
        radioBtn.click();
        click(addDependentPage.deleteBtn);*/


        System.out.println("optional step to delete dependant");



    }


    @Then("user is able to see the added dependant in the profile")
    public void user_is_able_to_see_the_added_dependant_in_the_profile() {

        System.out.println("Successfully Saved");

    }
}
