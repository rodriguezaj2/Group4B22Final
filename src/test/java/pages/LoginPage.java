package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;


public class LoginPage extends CommonMethods {

    @FindBy (id="txtUsername")
    public WebElement usernameField;

    @FindBy (id="txtPassword")
    public WebElement passwordField;

    @FindBy (id="btnLogin")
    public WebElement loginButton;

    @FindBy (id="spanMessage")
    public WebElement errorMessage;

    @FindBy (xpath="//h1[text()='Dashboard']")
    public WebElement loginMessage;

    @FindBy(id = "txtUsername")
    public WebElement userName;


    @FindBy(id = "txtPassword")
    public WebElement password;

    @FindBy(xpath = "//h1[text()='Dashboard']")
    public WebElement dashboard;

    public LoginPage(){
        PageFactory.initElements(driver,this);
    }
}