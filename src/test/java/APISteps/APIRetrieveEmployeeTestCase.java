package APISteps;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIConstants;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class APIRetrieveEmployeeTestCase {

    public static String token;
    RequestSpecification req;
    Response resp;
    public static String empID;

    // Step: Generate JWT Token
    @Given("JWT token is generated")
    public void jwt_token_is_generated() {
        RequestSpecification request = given()
                .header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE)
                .body("{\n" +
                        "  \"email\": \"turtle24256@gmail.com\",\n" +
                        "  \"password\": \"sea@123\"\n" +
                        "}");

        Response response = request.when().post(APIConstants.GENERATE_TOKEN_URI);
        token = "Bearer " + response.jsonPath().getString("token");
        System.out.println("Token: " + token);
    }

    // Step: Create an employee before retrieving
    @Given("an employee is created for retrieval")
    public void an_employee_is_created_for_retrieval() {
        RequestSpecification createReq = given()
                .header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE)
                .header(APIConstants.HEADER_AUTHORIZATION_KEY, token)
                .body(APIPayload.createEmployeePayload());

        Response createResp = createReq.when().post(APIConstants.CREATE_EMPLOYEE_URI);
        createResp.prettyPrint();
        empID = createResp.jsonPath().getString("Employee.employee_id");
        System.out.println("Created Employee ID: " + empID);
    }

    // Step: Prepare the GET request
    @Given("a request is prepared to retrieve the created employee")
    public void prepare_retrieve_request() {
        req = given()
                .header(APIConstants.HEADER_AUTHORIZATION_KEY, token)
                .queryParam("employee_id", empID);
    }

    // Step: Make the GET call
    @When("a GET call is made to retrieve the employee")
    public void get_employee_call() {
        resp = req.when().get(APIConstants.GET_ONE_EMPLOYEE_URI);
        resp.prettyPrint();
    }

    // Step: Validate employee details in the response
    @Then("the response should contain the employee details:")
    public void verify_employee_details(DataTable dataTable) {
        Map<String, String> expectedData = dataTable.asMap(String.class, String.class);
        for (Map.Entry<String, String> entry : expectedData.entrySet()) {
            String key = entry.getKey();
            String expectedValue = entry.getValue().equals("empID") ? empID : entry.getValue();
            String actualValue = resp.jsonPath().getString("employee." + key);

            // Case-insensitive comparison
            assertThat("Mismatch in field: " + key, actualValue.equalsIgnoreCase(expectedValue));
        }
    }


    // Step: Response should indicate employee does not exist
    /*@Then("response should indicate that the employee was not found")
    public void response_should_indicate_not_found() {
        resp.then().body("massage", equalTo("Employee does not exist or you have provided invalid employee_id"));
    }*/

    //Duplicate Step Definition
    @Given("request is prepared to make a POST call for the creation of an employee")
    public void prepare_the_create_employee_request() {
        req = given()
                .header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE)
                .header(APIConstants.HEADER_AUTHORIZATION_KEY, token)
                .body(APIPayload.createEmployeePayload());
    }
    @When("POST call is made to Create an Employee")
    public void create_an_employee() {
        resp = req.when().post(APIConstants.CREATE_EMPLOYEE_URI);
        resp.prettyPrint();
        empID = resp.jsonPath().getString("Employee.employee_id");
        System.out.println("Created Employee ID: " + empID);
    }
    @Then("response code should be {int}")
    public void verify_the_status_code(int expectedStatusCode) {
        if (resp == null) {
            throw new RuntimeException("Response object is null — make sure API call was made.");
        }
        int actualStatusCode = resp.getStatusCode();
        System.out.println("Expected: " + expectedStatusCode + ", Actual: " + actualStatusCode);
        resp.then().statusCode(expectedStatusCode);
    }

    @Given("employee does not exist with employee_id {string}")
    public void set_the_invalid_employee_id(String invalidID) {
        req = given()
                .header(APIConstants.HEADER_AUTHORIZATION_KEY, token)
                .queryParam("employee_id", invalidID);

    }

    @Then("the response should contain message {string}")
    public void the_response_should_contain_message(String expectedMessage) {
        resp.then().body("massage", equalTo(expectedMessage));

    }

}