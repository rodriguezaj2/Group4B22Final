package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class LanguagePage extends CommonMethods {

    @FindBy(xpath = "//a[text()='Qualifications']")
    public WebElement qualificationsTab;

    @FindBy(id="addLanguage")
    public WebElement addLanguageButton;

    @FindBy(xpath = "//select[@id='language_code']")
    public WebElement languageDropdown;

    @FindBy(xpath = "//select[@id='language_lang_type']")
    public WebElement fluencyDropdown;

    @FindBy(xpath = "//select[@id='language_competency']")
    public WebElement competencyDropdown;

    @FindBy(id = "language_comments")
    public WebElement languageComments;

    @FindBy(id = "btnLanguageSave")
    public WebElement languageSaveButton;

    //this will only work for English make it dynamic later
    @FindBy(xpath = "//table[@id='lang_data_table']//tr[td[normalize-space()='English']]//input[@type='checkbox']")
    public WebElement selectLanguageCheckbox;

    @FindBy(id = "delLanguage")
    public WebElement deleteButton;

    @FindBy(xpath = "//span[@for='language_lang_type' and @class='validation-error' and text()='Required']")
    public WebElement fluencyErrorMessage;

    @FindBy(xpath = "//span[@for='language_competency' and @class='validation-error' and text()='Required']")
    public WebElement competencyErrorMessage;

    public LanguagePage(){
        PageFactory.initElements(driver,this);
    }
}
