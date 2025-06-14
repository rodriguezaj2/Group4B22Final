package steps;

import groovy.xml.StreamingDOMBuilder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Se;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonMethods;

import java.time.Duration;

public class ChangeEmployeeContactDetailsSteps extends CommonMethods {
    @And("navigates to My Info tab")
    public void navigatesToMyInfoTab() {
        click(dashboardPage.myInfoTab);
    }

    @And("clicks on Contact Details")
    public void clicksOnContactDetails() {
        WebElement contactDetails=driver.findElement(By.xpath("//a[text()='Contact Details']"));
        contactDetails.click();
    }

    @And("clicks the Edit button")
    public void clicksTheEditButton() {
        WebElement editButton=driver.findElement(By.xpath("//input[@value='Edit']"));
        editButton.click();
    }

    @When("user enters Address Street one as {string}")
    public void userEntersStreetAddressStreetAs(String streetOne) {
        WebElement streetAddressOne= driver.findElement(By.id("contact_street1"));
        streetAddressOne.clear();
        streetAddressOne.sendKeys(streetOne);
    }

    @And("enters Address Street two as {string}")
    public void entersAddressStreetAs(String streetTwo) {
        WebElement streetAddressTwo=driver.findElement(By.id("contact_street2"));
        streetAddressTwo.clear();
        streetAddressTwo.sendKeys(streetTwo);
    }

    @And("enters City as {string}")
    public void entersCityAs(String cityName) {
        WebElement city=driver.findElement(By.id("contact_city"));
        city.clear();
        city.sendKeys(cityName);
    }

    @And("selects State")
    public void selectsState() {
        WebElement state=driver.findElement(By.xpath("//*[@id=\"contact_state\"]"));
        Select select=new Select(state);
        select.selectByIndex(1);
    }

    @And("enters Zipcode as {string}")
    public void entersZipcodeAs(String postalCode) {
        WebElement zipcode=driver.findElement(By.id("contact_emp_zipcode"));
        zipcode.clear();
        zipcode.sendKeys(postalCode);
    }

    @And("selects as Country {string}")
    public void selectsAsCountry(String countryName) {
        WebElement country=driver.findElement(By.id("contact_country"));
        Select select=new Select(country);
        select.selectByIndex(0);
        select.selectByVisibleText(countryName);
    }

    @And("enters Home Telephone as {string}")
    public void entersHomeTelephoneAs(String homePhone) {
        WebElement homeTelephone=driver.findElement(By.id("contact_emp_hm_telephone"));
        homeTelephone.clear();
        homeTelephone.sendKeys(homePhone);
    }

    @And("enters Mobile as {string}")
    public void entersMobileAs(String mobile) {
        WebElement mobileTelephone=driver.findElement(By.id("contact_emp_mobile"));
        mobileTelephone.clear();
        mobileTelephone.sendKeys(mobile);
    }

    @And("enters Work Telephone as {string}")
    public void entersWorkTelephoneAs(String workPhone) {
        WebElement workTelephone=driver.findElement(By.id("contact_emp_work_telephone"));
        workTelephone.clear();
        workTelephone.sendKeys(workPhone);
    }

    @And("enters Work Email as {string}")
    public void entersWorkEmailAs(String workMail) {
        WebElement workEmail= driver.findElement(By.id("contact_emp_work_email"));
        workEmail.clear();
        workEmail.sendKeys(workMail);
    }

    @And("enters Other Email as {string}")
    public void entersOtherEmailAs(String otherMail) {
        WebElement otherEmail=driver.findElement(By.id("contact_emp_oth_email"));
        otherEmail.sendKeys(otherMail);
    }

    @And("the system should save the changes successfully")
    public void andTheSystemShouldSaveTheChangesSuccessfully() {
        WebElement editButton=driver.findElement(By.xpath("//input[@value='Edit']"));
        Assert.assertEquals("Edit", editButton.getDomAttribute("value"));
    }

    @And("clicks on the save button")
    public void clicksOnTheSaveButton() {
        WebElement saveButton=driver.findElement(By.xpath("//input[@value='Save']"));
        saveButton.click();
    }

    @And("employee enters unique username as {string} and password as {string}")
    public void employeeEntersUniqueUsernameAsAndPasswordAs(String userName, String passWord) {
        sendText(userName, loginPage.usernameField);
        sendText(passWord, loginPage.passwordField);
    }

    @And("all fields will clear to revalidate")
    public void allFieldsWillClearToRevalidate() {
        driver.findElement(By.id("contact_street1")).clear();
        driver.findElement(By.id("contact_street2")).clear();
        driver.findElement(By.id("contact_city")).clear();
        driver.findElement(By.id("contact_emp_zipcode")).clear();
        driver.findElement(By.id("contact_emp_hm_telephone")).clear();
        driver.findElement(By.id("contact_emp_mobile")).clear();
        driver.findElement(By.id("contact_emp_work_telephone")).clear();
        driver.findElement(By.id("contact_emp_work_email")).clear();
        driver.findElement(By.id("contact_emp_oth_email")).clear();

        Select stateSelect=new Select(driver.findElement(By.xpath("//*[@id=\"contact_state\"]")));
        stateSelect.selectByIndex(0);

        Select countrySelect = new Select(driver.findElement(By.id("contact_country")));
        countrySelect.selectByIndex(0);
    }

    @When("user enters work email {string}")
    public void userEntersWorkEmail(String workMail) {
        WebElement workEmail= driver.findElement(By.id("contact_emp_work_email"));
        workEmail.clear();
        workEmail.sendKeys(workMail);
    }

    @Then("email error message will appear as {string}")
    public void emailErrorMessageWillAppearAs(String emailError) {
        WebElement emailErrorMsg=driver.findElement(By.xpath("//span[text()='Expected format: admin@example.com']"));
    }
}
