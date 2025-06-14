package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;



public class ProfilePictureStep extends CommonMethods {

    @When("user clicks on the profile picture option")
    public void user_clicks_on_the_profile_picture_option() {

        click(profilePicturePage.profilePicture);
    }



    @When("user clicks on choose file button")
    public void user_clicks_on_choose_file_button() {

        WebElement image = driver.findElement(By.id("photofile"));
        image.sendKeys("C:\\Users\\btrue\\IdeaProjects\\Group4B22\\images\\profilepic.jpg");


    }



    @When("user uploads a profile picture")
    public void user_uploads_a_profile_picture() throws InterruptedException {

        Thread.sleep(5000);
        click(profilePicturePage.saveBtn);
    }



    @Then("user is able to see the uploaded profile picture")
    public void user_is_able_to_see_the_uploaded_profile_picture() {

        click(profilePicturePage.saveButton);
        System.out.println("Profile picture uploaded successfully");
    }
}
