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


    @FindBy (id="photofile")
    public WebElement photograph;

    @FindBy(xpath = "//div[@id='profile-pic']//img")
    public WebElement profilePictureImg;

    @FindBy(xpath = "//div[contains(text(),'Successfully Uploaded')]")
    public WebElement uploadSuccessMessage;

    @FindBy(id = "empPic")
    public WebElement profilePic;








    public ProfilePicturePage() {
        PageFactory.initElements(driver, this);
    }


}