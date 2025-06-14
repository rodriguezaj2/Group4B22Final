Feature: Profile Picture

  Background:
    When user enters valid username and password
    And user clicks on login button
    And user is able to see the dashboard page
    When user clicks on PIM option
    And user clicks on Add employee option
    And user enters firstname middlename and lastname
    And user clicks on save button

  @addprofilepic @totaltest
  Scenario: Adding a profile picture
    When user clicks on the profile picture option
    And user clicks on choose file button
    Then user is able to see the uploaded profile picture
