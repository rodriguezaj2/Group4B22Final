package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class DashboardPage extends CommonMethods {

    @FindBy (xpath="//*[@id='menu_pim_viewPimModule']/b")
    public WebElement pimTab;

    @FindBy (id="menu_pim_viewEmployeeList")
    public WebElement employeeListTab;

    @FindBy (id="menu_pim_addEmployee")
    public WebElement addEmployeeTab;

    @FindBy (xpath = "//*[@id='menu_pim_viewMyDetails']/b")
    public WebElement myInfoTab;

    @FindBy (id="btnSave")
    public WebElement saveButton;

    public DashboardPage(){
        PageFactory.initElements(driver,this);
    }
}