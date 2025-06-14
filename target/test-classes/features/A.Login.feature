Feature: Login steps


  @validcredential @totaltest
  Scenario: Entering login credentials and navigating to add employee
    When user enters valid username
    And user enters valid password
    And user clicks login button
    And user is able to see the dashboard page

  @emptyUsername @loginsteps @totaltest
  Scenario: blank username credentials
    When user enters blank username
    And user enters valid password
    And user clicks login button
    Then user is able to see error message

  @emptyPassword @loginsteps @totaltest
  Scenario: blank password credentials
    When user enters valid username
    And user enters blank password
    And user clicks login button
    Then user can see error message

  @InvalidCredentials @loginsteps @totaltest
  Scenario: invalid username and password login credentials
    When user enters invalid username
    And user enters invalid password
    And user clicks login button
    Then user sees error message
