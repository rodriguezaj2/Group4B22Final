Feature: Search for employee

  Background:
   # Given user is able to access HRMS application
    When user enters valid username and password
    And user clicks login button
    Then user is able to see the dashboard page
    When user clicks on PIM option
    And user clicks on employee list option

  @fullNameSearch
  Scenario: Search employee by full name
    When user enters valid employee full name
    And user clicks on search button
    Then user is able to see employee details

  @partialNameSearch
  Scenario: Search employee by partial name
    When user enters valid employee partial name
    And user clicks on search button
    Then user is able to see employee details

  @empIdSearch
  Scenario: Search employee by employee ID
    When user enters valid employee ID
    And user clicks on search button
    Then user is able to see employee details

  @invalidSearch
  Scenario: Search employee with invalid details
    When user enters invalid employee details
    And user clicks on search button
    Then message "No Records Found" appears