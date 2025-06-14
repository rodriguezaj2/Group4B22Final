Feature: Delete Employee API
  This feature file contains scenarios to test the Delete Employee API endpoint.

  Background:
    Given a request is prepared to make a POST call for the creation of an employee
    When a POST call is made to Create an Employee
    Then the response code should be 201
    And the response should contain:
      | message     | Employee Created |
      | employee_id | empID           |
    When I send a DELETE request to
@deleteEmployee
Scenario: Successfully delete an employee with a valid ID
And a request is prepare to make a POST call for the creation of an employee
When a POST call is made to Create an Employee
When I send a DELETE request to
When a DELETE call is made to delete an Employee
Then the response code should be 200
And the response should contain:
| message          | Employee deleted |
| employee_id      | empID          |

@apiInvalidID
Scenario: Attempt to delete an employee with an invalid ID
Given an employee does not exist with employee_id "999999"
When I send a DELETE request to
And a DELETE call is made to delete an Employee
Then the response code should be 404
And the response should indicate that the employee was not found

@emptyID
Scenario: Attempt to delete an employee with an empty ID
Given an employee is empty with employee_id ""
When I send a DELETE request to
When a DELETE call is made to delete an Employee
Then the response code should be 400
And the response should indicate that the employeeID was empty