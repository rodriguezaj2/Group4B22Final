package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonMethods;
import utils.Constants;

import java.time.Duration;

import static utils.Constants.PROFILE_PIC_FILE_PATH;

public class ProfilePictureStep extends CommonMethods {

    @When("user clicks on the profile picture option")
    public void user_clicks_on_the_profile_picture_option() {

        click(profilePicturePage.profilePictureImg);

    }

    @When("user clicks on choose {string}")
    public void user_clicks_on_choose(String ProfilePhotoFile)  {

        String filePath;
        if ("PROFILE_PIC_FILE_PATH".equals(ProfilePhotoFile)) {
            filePath = PROFILE_PIC_FILE_PATH;
        } else {
            filePath = ProfilePhotoFile;
        }
        uploadFile(profilePicturePage.photograph, filePath);

    }

    @Then("user is able to see the uploaded profile picture")
    public void user_is_able_to_see_the_uploaded_profile_picture() {
        waitForElementToBeClickable(profilePicturePage.saveButton);
        profilePicturePage.saveButton.click();

    }
    @Then("my profile should display the updated picture")
    public void my_profile_should_display_the_updated_picture() {
        getWait().until(ExpectedConditions.visibilityOf(profilePicturePage.uploadSuccessMessage));
        String actualMessage = profilePicturePage.uploadSuccessMessage.getText().trim();
        Assert.assertTrue(actualMessage.contains("Successfully Uploaded"));
        Assert.assertTrue(profilePicturePage.profilePic.isDisplayed());

    }
}
