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

    public ProfilePicturePage() {
        PageFactory.initElements(driver, this);
    }


}