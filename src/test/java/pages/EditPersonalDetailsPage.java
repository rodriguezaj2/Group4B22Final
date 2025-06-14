package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class EditPersonalDetailsPage extends CommonMethods {
    // This class will contain elements and methods related to editing personal details,
    // For example, fields for first name, last name, contact details, etc.
    // My Info Navigation and clicks
    @FindBy(id = "menu_pim_viewMyDetails")
    public WebElement MyInfo;

    // ----------- Edit Buttons -----------
    @FindBy(id = "btnSave")
    public WebElement editButton;

    //---------- Save Button -----------
    @FindBy(id = "btnSave")
    public WebElement saveButton;

    // ----------- Welcome Message -----------
    @FindBy(id = "welcome")
    public WebElement welcomeMessage;

    // ----------- Personal Details Fields -----------
    @FindBy(id = "personal_txtEmpFirstName")
    public WebElement firstNameField;

    @FindBy(id = "personal_txtEmpMiddleName")
    public WebElement middleNameField;

    @FindBy(id = "personal_txtEmpLastName")
    public WebElement lastNameField;

    // ----------- Gender Radio Buttons -----------
    @FindBy(id = "personal_optGender_1")
    public WebElement maleRadioButton;

    @FindBy(id = "personal_optGender_2")
    public WebElement femaleRadioButton;


    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            if (!maleRadioButton.isSelected()) {
                maleRadioButton.click();
            }
        } else if (gender.equalsIgnoreCase("Female")) {
            if (!femaleRadioButton.isSelected()) {
                femaleRadioButton.click();
            }
        } else {
            throw new IllegalArgumentException("Invalid gender: " + gender);
        }
    }



    // ----------- Dropdowns -----------
    @FindBy(id = "personal_cmbNation")
    public WebElement nationalityDropdown;

    // ----------- Marital Status Dropdown -----------
    @FindBy(id = "personal_cmbMarital")
    public WebElement maritalStatusDropdown;


    public EditPersonalDetailsPage(){
        PageFactory.initElements(driver,this);
    }



}
