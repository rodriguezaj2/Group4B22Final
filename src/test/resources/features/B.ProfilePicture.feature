Feature: Profile Picture

  @addprofilepic
  Scenario: Adding a profile picture
    When user clicks add on employee option
    And user enters firstname middlename and lastname
    And clicks on save button
    When user clicks on the profile picture option
    And user clicks on choose file button
    Then user is able to see the uploaded profile picture


