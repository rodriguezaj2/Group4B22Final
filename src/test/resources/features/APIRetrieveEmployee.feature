Feature: Retrieve Employee API
  This feature validates retrieving employee records via API.

  Background:
    Given JWT token is generated
    Given request is prepared to make a POST call for the creation of an employee
    When POST call is made to Create an Employee
    Then the response code should be 201

  @retrieveValid
  Scenario: Successfully retrieve an employee with a valid ID
    Given a request is prepared to retrieve the created employee
    When a GET call is made to retrieve the employee
    Then response code should be 200
    And the response should contain the employee details:
      | employee_id     | empID          |
      | emp_firstname   | Jon            |
      | emp_middle_name | Bones          |
      | emp_lastname    | Jones          |
      | emp_birthday    | 1985-05-25     |
      | emp_gender      | Male           |
      | emp_job_title   | Banker         |
      | emp_status      | Employed       |

  @retrieveInvalid
  Scenario: Retrieve employee with an invalid ID
    Given employee does not exist with employee_id "119146"
    When a GET call is made to retrieve the employee
    Then response code should be 404
    And the response should contain message "Employee does not exist or you have provided invalid employee_id"