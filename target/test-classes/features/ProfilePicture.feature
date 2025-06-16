Feature: Profile Picture

  Background:
    # Given the ESS user is on the HRMS login page
    When the user logs in with valid credentials
    Then the employee should be redirected to the dashboard
    Then the employee navigates to the My Info section and clicks



  @addprofilepic @totaltest
  Scenario: Adding a profile picture
    When user clicks on the profile picture option
    And user clicks on choose "PROFILE_PIC_FILE_PATH"
    Then user is able to see the uploaded profile picture
    And my profile should display the updated picture

