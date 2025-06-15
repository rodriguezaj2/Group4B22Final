Feature: Profile Picture

    Background: user is logged in and on the Add Employee page
      Given user is able to access HRMS application
      When user enters valid username and password
      And user clicks login button
      And user is able to see the dashboard page
      When user clicks on PIM option
      And user clicks on Add employee option



  @addprofilepic @totaltest
  Scenario: Adding a profile picture
    When user enters firstname middlename and lastname
    And clicks on save button
    When user clicks on the profile picture option
    And user clicks on choose file button
    Then user is able to see the uploaded profile picture


