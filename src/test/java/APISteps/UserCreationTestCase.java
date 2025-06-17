package APISteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;

import static io.restassured.RestAssured.given;


public class UserCreationTestCase {

    private static String uniqueEmail; // Shared across steps to handle email reuse

    private RequestSpecification request;
    private Response response;

    @Given("request with name {string}, email {string} and password {string}")
    public void prepareRequestWithDynamicEmail(String name, String emailPrefix, String password) {
        // Generate the unique email only if it's not already generated
        if (uniqueEmail == null) {
            String truncatedPrefix = emailPrefix.length() > 10 ? emailPrefix.substring(0, 10) : emailPrefix;
            uniqueEmail = truncatedPrefix + System.currentTimeMillis() % 100000 + "@ex.com";
            System.out.println("Generated new unique email: " + uniqueEmail);
        } else {
            System.out.println("Reusing existing unique email: " + uniqueEmail);
        }

        request = given()
                .header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE)
                .body(APIPayload.createUser(name, uniqueEmail, password));

        System.out.println("Prepared request with email: " + uniqueEmail);
    }

    @Given("request is prepared with name {string}, email {string} and password {string}")
    public void prepareRequest(String name, String email, String password) {
        request = given()
                .header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE)
                .body(APIPayload.createUser(name, email, password));

        System.out.println("Prepared request with email: " + email);
    }

    @When("Post call is made")
    public void makePostCall() {
        response = request.when().post(APIConstants.CREATE_USER_URI);
        System.out.println("Post call made to: " + APIConstants.CREATE_USER_URI);
        response.prettyPrint();
        Assert.assertNotNull("Response should not be null", response);
    }

    @Then("the status code for this is {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        System.out.println("Verifying status code: Expected = " + expectedStatusCode + ", Actual = " + actualStatusCode);
        Assert.assertEquals("Status code does not match", expectedStatusCode, actualStatusCode);
    }

    @Then("the confirmation message appears {string}")
    public void verifyConfirmationMessage(String expectedMessage) {
        String actualMessage = response.jsonPath().getString("Message");
        System.out.println("Verifying confirmation message: Expected = " + expectedMessage + ", Actual = " + actualMessage);
        Assert.assertEquals("Confirmation message does not match", expectedMessage, actualMessage);
    }

    @Given("request is prepared with name {string}, email {string}, and password {string}")
    public void prepareRequestWithInvalidOrMissingFields(String name, String email, String password) {
        request = given()
                .header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE)
                .body(APIPayload.createUser(name, email, password));
        System.out.println("Prepared request with invalid/missing data.");
    }

    @Then("the error message appears {string}")
    public void verifyErrorMessage(String expectedErrorMessage) {
        String actualErrorMessage = null;

        if (response.jsonPath().get("Message") != null) {
            actualErrorMessage = response.jsonPath().getString("Message");
        } else if (response.jsonPath().get("condition") != null &&
                response.jsonPath().get("data") != null) {
            actualErrorMessage = response.jsonPath().getString("data");
        }

        System.out.println("Verifying error message: Expected = " + expectedErrorMessage + ", Actual = " + actualErrorMessage);
        Assert.assertEquals("Error message does not match", expectedErrorMessage, actualErrorMessage);
    }
}

