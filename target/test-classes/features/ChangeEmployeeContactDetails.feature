Feature: Change Employee Contact Details
  Background:
    Given user is able to access HRMS application
    And employee enters unique username as "ALJ61743965" and password as "Mr!9vT#27qLp@XyZ"
    And user clicks login button
    And user is able to see the dashboard page
    And navigates to My Info tab
    And clicks on Contact Details
    And clicks the Edit button
    And all fields will clear to revalidate

  @validEmployeeContactDetails
  Scenario: Valid employee contact details
    When user enters Address Street one as "4531 Oak Meadow Drive"
    And enters Address Street two as "Apt 21B"
    And selects as Country "United States"
    And enters City as "Richmond"
    And selects State
    And enters Zipcode as "23225"
    And enters Home Telephone as "7873249765"
    And enters Mobile as "3472348976"
    And enters Work Telephone as "3987640374"
    And enters Work Email as "johnsmith787@yahoo.com"
    And enters Other Email as "JohnSmith123@gmail.com"
    And clicks on the save button
    And the system should save the changes successfully

    @invalidEmailErrorMessage
    Scenario: Invalid email details
    When user enters work email "jSmith543"
    And enters Other Email as "johnSmith123"
    And clicks on the save button
    Then email error message will appear as "Expected format: admin@example.com"