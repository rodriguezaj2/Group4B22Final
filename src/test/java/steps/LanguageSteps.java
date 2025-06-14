package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.CommonMethods;

import java.util.List;

public class LanguageSteps extends CommonMethods {

    @Given("the user navigates to the language section")
    public void the_user_navigates_to_the_language_section() {
        click(dashboardPage.myInfoTab);
        click(languagePage.qualificationsTab);

    }

    @When("the user clicks the Add button")
    public void the_user_clicks_the_add_button() {
        click(languagePage.addLanguageButton);
    }

    @When("the user selects {string} as Language")
    public void the_user_selects_as_language(String language) {
        Select languageDropdown= new Select(languagePage.languageDropdown);
        languageDropdown.selectByVisibleText(language);

    }
    @When("the user selects {string} as Fluency")
    public void the_user_selects_as_fluency(String fluency) {
        Select fluencyDropdown=new Select(languagePage.fluencyDropdown);
        fluencyDropdown.selectByVisibleText(fluency);
    }
    @When("the user selects {string} as Competency")
    public void the_user_selects_as_competency(String competency) {
        Select competencyDropdown=new Select(languagePage.competencyDropdown);
        competencyDropdown.selectByVisibleText(competency);
    }
    @When("the user enters {string} as comment")
    public void the_user_enters_as_comment(String comment) {
        sendText(comment, languagePage.languageComments);
    }
    @When("the user clicks on Save")
    public void the_user_clicks_on_save() {
        click(languagePage.languageSaveButton);
    }
    @Then("{string} should be listed in the language table")
    public void should_be_listed_in_the_language_table(String expectedLanguage) {
        String xpath = "//table[@id='lang_data_table']//td[@class='name']/a[text()='" + expectedLanguage + "']";
        WebElement languageCell=driver.findElement(By.xpath(xpath));
        String actualLanguage=languageCell.getText();
        Assert.assertEquals(expectedLanguage,actualLanguage);
    }
    @When("the user clicks on the {string}")
    public void the_user_clicks_on_the(String language) {
        String xpath = "//table[@id='lang_data_table']//td[@class='name']/a[text()='" + language + "']";
        WebElement languageButton=driver.findElement(By.xpath(xpath));
        click(languageButton);
    }
    @When("the user changes competency to {string}")
    public void the_user_changes_competency_to(String competency) {
        Select competencyDropdown=new Select(languagePage.competencyDropdown);
        competencyDropdown.selectByVisibleText(competency);
    }
    @Then("the updated competency for {string} should be {string}")
    public void competency(String expectedLanguage, String expectedCompetency) {
        String xpath = "//table[@id='lang_data_table']//tr[td[@class='name']/a[text()='" + expectedLanguage + "']]/td[4]";
        WebElement competencyCell=driver.findElement(By.xpath(xpath));
        String actualCompetency=competencyCell.getText();
        Assert.assertEquals(expectedCompetency,actualCompetency);
    }

    @When("the user clicks on the {string} checkbox")
    public void the_user_clicks_on_the_checkbox(String language) {
        String xpath = "//table[@id='lang_data_table']//tr[td[normalize-space()='" + language + "']]//input[@type='checkbox']";
        WebElement checkbox=driver.findElement(By.xpath(xpath));
        click(checkbox);
    }

    @When("the user clicks on the Delete button")
    public void the_user_clicks_on_the_delete_button() {
        click(languagePage.deleteButton);
    }

    @Then("the language entry for {string} with fluency {string} and competency {string} should no longer be listed")
    public void the_language_entry_for_with_fluency_and_competency_should_no_longer_be_listed(String language, String fluency, String competency) {
        String xpath = "//table[@id='lang_data_table']//tr[td[@class='name']/a[text()='" + language + "'] and td[3][text()='" + fluency + "'] and td[4][text()='" + competency + "']]";
        List<WebElement> rows=driver.findElements(By.xpath(xpath));
        Assert.assertEquals(0,rows.size());
    }

    @When("the user leaves Fluency and Competency blank")
    public void the_user_leaves_fluency_and_competency_blank() {
        Select fluencyDropdown=new Select(languagePage.fluencyDropdown);
        Select competencyDropdown=new Select(languagePage.competencyDropdown);
        fluencyDropdown.selectByVisibleText("-- Select --");
        competencyDropdown.selectByVisibleText("-- Select --");

    }
    @Then("an error message should be displayed saying {string}")
    public void an_error_message_should_be_displayed_saying(String errorMessage) {
        Assert.assertEquals(errorMessage,languagePage.fluencyErrorMessage.getText());
        Assert.assertEquals(errorMessage,languagePage.competencyErrorMessage.getText());

    }


}
