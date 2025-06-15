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
import java.util.List;


public class MembershipSteps extends CommonMethods{

    @Then("the employee should be redirected to the dashboard")
    public void the_employee_should_be_redirected_to_the_dashboard() {
        click(loginPage.loginButton);
    }


    @Given("the employee navigates to the My Info section and clicks")
    public void the_employee_navigates_to_the_my_info_section_and_clicks() {
        click(membershipPage.MyInfo);
        Assert.assertTrue(membershipPage.MyInfo.isDisplayed());


    }



    @Then("user clicks on Membership from Personal Details")
    public void userClicksOnMembershipFromPersonalDetails() {
        Assert.assertTrue(membershipPage.membershipsTab.isEnabled());
        click(membershipPage.membershipsTab);

    }

    @Then("user clicks on Add Membership button")
    public void user_clicks_on_add_membership_button() throws InterruptedException {
        Assert.assertTrue(membershipPage.addMembershipButton.isEnabled());
        click(membershipPage.addMembershipButton);

    }

    @When("user selects {string} from Membership dropdown")
    public void user_selects_from_membership_dropdown(String Membership) {

        Assert.assertTrue(membershipPage.membershipDropdown.isEnabled());
        selectFromDropDown(membershipPage.membershipDropdown,Membership);

    }

    @When("user selects {string} from Subscription Paid By dropdown")
    public void user_selects_from_subscription_paid_by_dropdown(String SubscriptionPaid) {
        Assert.assertTrue(membershipPage.paidByDropdown.isEnabled());
        selectFromDropDown(membershipPage.paidByDropdown, SubscriptionPaid);


    }

    @When("user enters {string} in Subscription Amount field")
    public void user_enters_in_subscription_amount_field(String SubscriptionAmount) {
        Assert.assertTrue(membershipPage.amountField.isEnabled());
        sendText(SubscriptionAmount, membershipPage.amountField);


    }

    @When("user selects {string} from Currency dropdown")
    public void user_selects_from_currency_dropdown(String CurrencyDropdown) {
        Assert.assertTrue(membershipPage.currencyDropdown.isEnabled());
        selectFromDropDown(membershipPage.currencyDropdown, CurrencyDropdown);


    }

    @When("user selects {string} as Subscription Commence Date")
    public void user_selects_as_subscription_commence_date(String CommenceDate) {
        Assert.assertTrue(membershipPage.commenceDateField.isEnabled());
        sendText(CommenceDate, membershipPage.commenceDateField);


    }

    @When("user selects {string} as Subscription Renewal Date")
    public void user_selects_as_subscription_renewal_date(String RenewalDate) {
        Assert.assertTrue(membershipPage.renewalDateField.isEnabled());
        sendText(RenewalDate, membershipPage.renewalDateField);


    }

    @When("employee clicks on Save button")
    public void employee_clicks_on_save_button() {
        Assert.assertTrue(membershipPage.saveMembershipButton.isEnabled());
        click(membershipPage.saveMembershipButton);


    }


    @Then("membership details should be added successfully")
    public void membership_details_should_be_added_successfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Successfully')]")
        ));
        String actualMessage = toastMessage.getText();
        Assert.assertTrue(actualMessage.contains("Successfully Saved"));

    }

    //You can implement missing steps with the snippets below:

    @Then("the membership details {string} should be displayed on the profile")
    public void the_membership_details_should_be_displayed_on_the_profile(String expectedMembership) {
        List<WebElement> memberships = driver.findElements(By.xpath("//td[@class='memshipCode']/a"));

        boolean found = memberships.stream()
                .anyMatch(el -> el.getText().equalsIgnoreCase(expectedMembership));

        Assert.assertTrue("Membership '" + expectedMembership + "' not found on profile!", found);
        closeBrowser();

    }


    @Then("the membership details should be reflected correctly in the database")
    public void the_membership_details_should_be_reflected_correctly_in_the_database() {
        openBrowserAndLaunchApplication();
        sendText(ConfigReader.read("username"), loginPage.usernameField);
        sendText(ConfigReader.read("password"), loginPage.passwordField);
        click(loginPage.loginButton);
        click(dashboardPage.pimTab);
        click(dashboardPage.employeeListTab);
        sendText("61743965", searchEmployeePage.empIdSearchField);
        click(searchEmployeePage.searchButton);
        selectEmployeeById("61743965");
        click(membershipPage.membershipsTab);
        List<WebElement> memberships = driver.findElements(By.xpath("//td[@class='memshipCode']/a"));

        System.out.println("Memberships found on the profile:");
        for (WebElement el : memberships) {
            System.out.println(" - Text: " + el.getText() + " | Href: " + el.getAttribute("href"));
        }

        // Set your expected membership directly
        String expectedMembership = "Membership1";  // Replace with the value you expect

        boolean found = memberships.stream()
                .anyMatch(el -> el.getText().equalsIgnoreCase(expectedMembership));

        Assert.assertTrue("Membership '" + expectedMembership + "' not found on profile!", found);

        closeBrowser();

    }

    @Given("user has previously saved membership details")
    public void user_has_previously_saved_membership_details() {
        Assert.assertTrue(membershipPage.membershipsTab.isEnabled());
        click(membershipPage.membershipsTab);


    }

    @When("user clicks on Edit button next to the membership record")
    public void user_clicks_on_edit_button_next_to_the_membership_record() {

        Assert.assertTrue(membershipPage.editMembershipsTab.isEnabled());
        click(membershipPage.editMembershipsTab);
    }

    @When("user updates the Subscription Amount to {string}")
    public void user_updates_the_subscription_amount_to(String newAmount) {
        membershipPage.amountField.clear();
        sendText(newAmount, membershipPage.amountField);

    }

    @Then("the updated membership {string} details should be displayed on the profile")
    public void the_updated_membership_details_should_be_displayed_on_the_profile(String UpdatedMembership) {
         String expectedMembership = UpdatedMembership;
        List<WebElement> memberships = driver.findElements(By.xpath("//td[@class='memshipCode']/a"));

        boolean found = memberships.stream()
                .anyMatch(el -> el.getText().equalsIgnoreCase(expectedMembership));

        Assert.assertTrue("Membership '" + expectedMembership + "' not found on profile!", found);
        closeBrowser();

    }


    @When("user clicks on Delete button next to the membership record")
    public void user_clicks_on_delete_button_next_to_the_membership_record() {
        checkboxClick(membershipPage.checkBoxForDelete);
        Assert.assertTrue(membershipPage.deleteButton.isEnabled());
        click(membershipPage.deleteButton);

    }

    @When("user confirms the deletion")
    public void user_confirms_the_deletion() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Successfully')]")
        ));
        String actualMessage = toastMessage.getText();
        Assert.assertTrue(actualMessage.contains("Successfully Deleted"));
        closeBrowser();
    }




    @When("user leaves required fields blank")
    public void user_leaves_required_fields_blank() {
        Assert.assertTrue(membershipPage.addMembershipButton.isEnabled());
        click(membershipPage.addMembershipButton);



    }

    @Then("an error message should be displayed for each missing mandatory field")
    public void an_error_message_should_be_displayed_for_each_missing_mandatory_field() {
        Assert.assertTrue(membershipPage.requiredErrorMessage.isDisplayed());

    }
    @Then("error message should be displayed saying {string}")
    public void an_error_message_should_be_displayed_saying(String string) {
        Assert.assertTrue(membershipPage.requiredErrorMessage.isDisplayed());
        closeBrowser();

    }

    @When("user leaves Subscription Paid By, Subscription Amount, Currency, Subscription Commence Date, and Subscription Renewal Date blank")
    public void user_leaves_subscription_paid_by_subscription_amount_currency_subscription_commence_date_and_subscription_renewal_date_blank() {
        Assert.assertTrue(membershipPage.amountField.isEnabled());
        sendText(" ", membershipPage.amountField);


    }







}








