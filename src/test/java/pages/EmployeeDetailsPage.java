package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class EmployeeDetailsPage extends CommonMethods {

    //-------------Job Tab-------------------------------------------------
    @FindBy(xpath = "id(\"sidenav\")/li[6]/a")
    public WebElement jobTab;
    //------------------Edit Button--------------------------------------------------

    @FindBy(id = "btnSave")
    public WebElement editBtn;
    //------------------Job Details--------------------------------------------------

    @FindBy(id = "job_job_title")
    public WebElement jobTitleDD;

    //------------------Employment Status--------------------------------------------------

    @FindBy(id = "job_emp_status")
    public WebElement empStatusDD;

    //------------------Job Category--------------------------------------------------

    @FindBy(id = "job_eeo_category")
    public WebElement eeoCategoryDD;

    //------------------Joined Date--------------------------------------------------

    @FindBy(id = "job_joined_date")
    public WebElement joinedDateField;

    //------------------Contract Start Date--------------------------------------------------

    @FindBy(id = "job_contract_start_date")
    public WebElement contractStartDateField;

    //------------------Contract End Date--------------------------------------------------

    @FindBy(id = "job_contract_end_date")
    public WebElement contractEndDateField;

    //------------------Sub Unit--------------------------------------------------

    @FindBy(id = "job_sub_unit")
    public WebElement subUnitDD;

    //------------------Location--------------------------------------------------

    @FindBy(id = "job_location")
    public WebElement locationDD;

    //------------------Save Button--------------------------------------------------
    @FindBy(id = "btnSave")
    public WebElement saveBtn;


    //------------------Success Message--------------------------------------------------

    @FindBy(xpath = "//*[contains(text(), 'Successfully')]")
    public WebDriver updateSuccessMessage;

    //------------------Contract File Upload--------------------------------------------------


    @FindBy(id = "job_contract_file")
    public WebElement contractFileUpload;

    //-------------------View Contract File Link--------------------------------------------------
    @FindBy(xpath= "(//a[@class='fileLink'])[1]")
    public WebElement viewContractFileLink;


    //-----------Keep Current File Radio Button -----------
    @FindBy(id = "job_contract_update_1")
    public WebElement keepCurrentFileRadioButton;

    //-----------Delete Current File Radio Button -----------
    @FindBy(id = "job_contract_update_2")
    public WebElement deleteCurrentFileRadioButton;

    //-----------Replace Current File Radio Button -----------
    @FindBy(id = "job_contract_update_3")
    public WebElement replaceCurrentFileRadioButton;


    public EmployeeDetailsPage(){
        PageFactory.initElements(driver,this);
    }
}

