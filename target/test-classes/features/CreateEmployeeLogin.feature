Feature: Create Employee Login Scenarios
  Background:
 #Given user is able to access HRMS application
    And employee enters valid username and password
    And user clicks login button
    And user is able to see the dashboard page
    And clicks PIM option
    And clicks add employee

  @validCreateEmployeeLoginDetails
  Scenario: Valid employee login details
    When user enters first name as "Maria"
    And optionally enters middle name as "Alexandra"
    And enters last name as "Perez"
    And checks the "Create Login Details" checkbox
    And enters username as "mariap76"
    And enters password as "River#Sky8!Stone"
    And user enters confirm password as "River#Sky8!Stone"
    And selects enabled from the status dropdown
    And clicks on save button
    Then user is redirected to Personal Details page

  @validationFullNameFields
  Scenario: Validation when Full Name fields are left blank
    When user enters "Smith" in the last name field
    And user leaves first name field empty
    And clicks on save button
    Then user sees an error message in firstname field
    When user enters first name as "John"
    And user leaves last name field empty
    And clicks on save button
    Then user sees an error message in lastname field

  @validationCreateLoginDetailsFields
  Scenario: Validation when Create Login Details are left blank
    When user clicks on create login details checkbox
    And enters username as "john"
    And enters password as "hrms"
    And sees a error message "Should have at least 5 characters"
    And error message appears "Should have at least 8 characters"
    Then password message is displayed "For a strong password, please use a hard to guess combination of text with upper and lower case characters, symbols and numbers"
    When user enters confirm password as "Hro"
    And clicks on save button
    And sees password error message "Passwords do not match"
    Then the Status dropdown should show "Enabled" and "Disabled"

  @checkboxBehavior
  Scenario: Username and password fields are disabled until checkbox is checked
    When user clicks on create login details checkbox
    Then the "Username", "Password", and "Confirm Password" fields should be enabled
    When user unchecks the "Create Login Details" checkbox
    Then the "Username", "Password", and "Confirm Password" fields should be disabled
