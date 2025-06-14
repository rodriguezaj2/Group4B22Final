package utils;


//import pages.AddEmployeePage;
import pages.*;

public class PageInitializer {

    public static LoginPage loginPage;
    //public static AddEmployeePage addEmployeePage;
    public static DashboardPage dashboardPage;
    public static LanguagePage languagePage;
    public static ProfilePicturePage profilePicturePage;
    public static AddDependentsPage addDependentPage;



    public static void initializerPageObjects(){
        loginPage=new LoginPage();
       // addEmployeePage=new AddEmployeePage();
        dashboardPage=new DashboardPage();
        languagePage=new LanguagePage();
        profilePicturePage=new ProfilePicturePage();
        addDependentPage=new AddDependentsPage();
    }
}
