package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class ProfilePicturePage extends CommonMethods {


    @FindBy(id = "empPic")
    public WebElement profilePicture;

    @FindBy(id = "btnSave")
    public WebElement saveBtn;

    @FindBy(xpath = "//input[@type = 'file']")
    public WebElement uploadFileInput;

    @FindBy(id = "btnSave")
    public WebElement saveButton;

    @FindBy(id = "btnCancel")
    public WebElement cancelButton;

    @FindBy(id = "menu_pim_addEmployee")
    public WebElement addEmployeeButton;

    @FindBy(id = "firstName")
    public WebElement firstName;

    @FindBy(id = "middleName")
    public WebElement middleName;

    @FindBy(id = "lastName")
    public WebElement lastName;




    public ProfilePicturePage() {
        PageFactory.initElements(driver, this);
    }


}