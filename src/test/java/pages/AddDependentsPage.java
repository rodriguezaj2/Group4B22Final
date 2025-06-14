package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;




public class AddDependentsPage extends CommonMethods {

    @FindBy(xpath = "//a [contains(text() ,'Dependents')]")
    public WebElement dependentsBtn;

    @FindBy(id = "btnAddDependent")
    public WebElement addDependentBtn;

    @FindBy(id = "dependent_name")
    public WebElement addName;

    @FindBy(xpath = "//select[@id='dependent_relationshipType']")
    public WebElement selectDropdown;

    @FindBy(id = "dependent_relationship")
    public WebElement relationshipType;

    @FindBy(xpath = "//img[@class = 'ui-datepicker-trigger']")
    public WebElement dob;

    @FindBy(xpath = "//select[@class='ui-datepicker-month']")
    public WebElement month;

    @FindBy(xpath = "//select[@class='ui-datepicker-year']")
    public WebElement year;

    @FindBy(xpath = "//table[@class='ui-datepicker-calendar']/tbody/tr/td/a[contains(text(), '17')]")
    public WebElement day;

    @FindBy(id = "btnSaveDependent")
    public WebElement saveBtn;

    @FindBy(xpath =  "//input[@id = 'delDependentBtn']")
    public WebElement deleteBtn;


    public AddDependentsPage() {
        PageFactory.initElements(driver, this);
    }


}