Feature: Employee adds Dependants

  Background: user is logged in and on the Add Employee page
    Given user is able to access HRMS application
    When user enters valid username and password
    And user clicks login button
    And user is able to see the dashboard page
    And user clicks on PIM option
    And user clicks on Add employee option
    And user enters firstname middlename and lastname
    Then user clicks on save button


  @adddependents @totaltest
  Scenario: User adds a dependant to profile
    When user clicks on the dependants option
    And user clicks add dependant button
    And user enters dependant first and last name
    And user selects relationship type
    And user enters dependant date of birth
    And user saves dependent information
    And user has option to delete dependant
    Then user is able to see the added dependant in the profile