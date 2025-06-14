package APISteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class deleteEmployeeStepsTestCases {


    public static String token;
    RequestSpecification req;
    Response resp;
    static String empID;
    static String jsosnString;

    @Given("a JWT token is generated")
    public void a_jwt_token_is_generated() {
        RequestSpecification req=given().header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).body("{\n" +
                " \"email\": \"Keyblade02@gmail.com\",\n" +
                "  \"password\": \"KingdomHearts02\"\n" +
                "}");

        resp = req.when().post(APIConstants.GENERATE_TOKEN_URI);
        //resp.prettyPrint();
        token="Bearer "+resp.jsonPath().getString("token");
        System.out.println(token);

    }


    @Given("a request is prepare to make a POST call for the creation of an employee")
    public void a_request_is_prepare_to_make_a_post_call_for_the_creation_of_an_employee() {
        req = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY, APIConstants.HEADER_CONTENT_TYPE_VALUE).header(APIConstants.HEADER_AUTHORIZATION_KEY, token)
                .body(APIPayloads.createEmployeePayload());

    }
    @When("a POST call is made to Create an Employee")
    public void a_po_st_call_is_made_to_create_an_employee() {
        resp = req.when().post(APIConstants.CREATE_EMPLOYEE_URI);
        empID=resp.jsonPath().getString("Employee.employee_id");

        System.out.println("Json"+ resp.jsonPath().getString("Employee.emp_birthday"));
        System.out.println("The employee ID is: " + empID);
    }
    @Then("the status code is {int} for this call")
    public void tthe_status_code_is_for_this_call(int expectedStatusCode) {
        resp.then().statusCode(expectedStatusCode);
    }
    @Then("the employee created contains the key {string} and value {string}")
    public void the_employee_created_contains_the_key_and_value(String key, String value) {
        resp.then().assertThat().body(key,equalTo(value));

        resp.prettyPrint();
    }

    @Given("an employee exists with employee_id {string}")
    public void an_employee_exists_with_employee_id(String string) {
        empID = string;
    }

    @When("I send a DELETE request to")
    public void i_send_a_delete_request_to() {


        System.out.println("Deleting employee with ID: " + empID);

        req=given().header(APIConstants.HEADER_AUTHORIZATION_KEY, token)
                .queryParam("employee_id", empID);

        System.out.println("Deleting employee with ID: " + empID);
    }

    @When("a DELETE call is made to delete an Employee")
    public void a_delete_call_is_made_to_delete_an_employee() {
        resp=req.when().delete(APIConstants.DELETE_EMPLOYEE_URI);
        resp.prettyPrint();
    }
    @Then("the response code should be {int}")
    public void the_response_code_should_be(int expectedStatusCode) {
        resp.then().statusCode(expectedStatusCode);
    }

    @Then("the response should contain:")
    public void the_response_should_contain(io.cucumber.datatable.DataTable dataTable) {
        Map <String, String> data = dataTable.asMap(String.class, String.class);
        resp.then().body("message", equalTo(data.get("message")));
        resp.then().body("employee.employee_id", equalTo(empID));
       /* resp.then().body("employee.emp_firstname", equalTo(data.get("emp_firstname")));
        resp.then().body("employee.emp_middle_name", equalTo(data.get("emp_middle_name")));
        resp.then().body("employee.emp_lastname", equalTo(data.get("emp_lastname")));
        resp.then().body("employee.emp_birthday", equalTo(data.get("emp_birthday")));
        resp.then().body("employee.emp_gender", equalTo(data.get("emp_gender")));*/
    }

    @Given("an employee does not exist with employee_id {string}")
    public void an_employee_does_not_exist_with_employee_id(String invalidEmpID) {

        empID=invalidEmpID;
        req=given().header(APIConstants.HEADER_AUTHORIZATION_KEY, token)
                .queryParam("employee_id", invalidEmpID);
        int statusCode = req.given().when().delete(APIConstants.DELETE_EMPLOYEE_URI).getStatusCode();

        Assert.assertEquals(404, statusCode);
    }
    @Then("the response should indicate that the employee was not found")
    public void the_response_should_indicate_that_the_employee_was_not_found() {
        resp.then().body("Massage", equalTo("Employee does not exist or you have provided invalid employee_id"));
    }

    @Given("an employee is empty with employee_id {string}")
    public void an_employee_is_empty_with_employee_id(String emptyID) {
        empID=emptyID;
        req=given().header(APIConstants.HEADER_AUTHORIZATION_KEY, token)
                .queryParam("employee_id", emptyID);
        int statusCode = req.given().when().delete(APIConstants.DELETE_EMPLOYEE_URI).getStatusCode();

        Assert.assertEquals(400, statusCode);
    }
    @Then("the response should indicate that the employeeID was empty")
    public void the_response_should_indicate_that_the_employeeID_was_empty() {
        resp.then().body("Error", equalTo("Please provide employee_id"));
    }

}

