package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class MembershipPage extends CommonMethods {


    @FindBy(xpath = "//*[@id='txtUsername']")
    public WebElement usernameField;


    @FindBy(xpath = "//*[@id='txtPassword']")
    public WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"sidenav\"]/li[11]/a")
    public WebElement membershipOption;



    @FindBy(id="empsearch_id")
    public WebElement empSearchId;

    @FindBy(id="searchBtn")
    public WebElement searchButton;

    @FindBy(xpath="//a[contains(@href, '/index.php/pim/viewEmployee/empNumber')]")
    public WebElement empSearchIdLink;

    @FindBy(linkText=("Memberships"))
    public WebElement membershipsTab;


    @FindBy(id="membership_list")
    public WebElement membershipList;

    @FindBy(xpath = "//h1[text()='Assigned Memberships']")
    public WebElement AssignedMembershipsHeader;

    public MembershipPage(){
        PageFactory.initElements(driver,this);}

    //---New---
    @FindBy(id = "menu_pim_viewMyDetails")
    public WebElement MyInfo;

    @FindBy(id = "btnAddMembershipDetail")
    public WebElement addMembershipButton;

    @FindBy(id = "membership_membership")
    public WebElement membershipDropdown;

    @FindBy(id = "membership_subscriptionPaidBy")
    public WebElement paidByDropdown;

    @FindBy(id = "membership_subscriptionAmount")
    public WebElement amountField;

    @FindBy(id = "membership_currency")
    public WebElement currencyDropdown;

    @FindBy(id = "membership_subscriptionCommenceDate")
    public WebElement commenceDateField;

    @FindBy(id = "membership_subscriptionRenewalDate")
    public WebElement renewalDateField;

    @FindBy(id = "btnSaveMembership")
    public WebElement saveMembershipButton;

    //---Membership  was saved successfully---
    @FindBy(xpath = "//div[@class='message success fadable']")
    public WebElement membershipSavedSuccessfullyMessage;

    //----click on Memberships1 edit Tab
    @FindBy(xpath = "//td[@class='memshipCode']/a")
    public WebElement editMembershipsTab;

    //----checkbox for delete
    @FindBy(name = "chkmemdel[]")
    public WebElement checkBoxForDelete;

    //---Delete Button----
    @FindBy(id = "delMemsBtn")
    public WebElement deleteButton;

    //---Required Error Message----
    @FindBy(xpath = "//span[text()='Required']")
    public WebElement requiredErrorMessage;





}





    //public MembershipPage() {
    //driver = CommonMethods.getDriver();
    //PageFactory.initElements(driver, this);



