Feature: Employee membership details
  Background:
    Given the ESS user is on the HRMS login page
    When the user logs in with valid credentials
    Then the employee should be redirected to the dashboard
    Then the employee navigates to the My Info section and clicks
    Given user clicks on Membership from Personal Details


  @membershipDetails
  Scenario: user adds membership details
    Given user clicks on Membership from Personal Details
    Then user clicks on Add Membership button
    When user selects "Membership1" from Membership dropdown
    And user selects "Company" from Subscription Paid By dropdown
    And user enters "1000" in Subscription Amount field
    And user selects "Euro" from Currency dropdown
    And user selects "2024-06-01" as Subscription Commence Date
    And user selects "2025-06-01" as Subscription Renewal Date
    And employee clicks on Save button
    Then membership details should be added successfully
    And the membership details "Membership1" should be displayed on the profile
    Then the membership details should be reflected correctly in the database


    @EditMembership
    Scenario: user edits membership details
      Given user has previously saved membership details
      When user clicks on Edit button next to the membership record
      And user updates the Subscription Amount to "1200"
      And employee clicks on Save button
      Then the updated membership "Membership1" details should be displayed on the profile

    @DeleteMembership
    Scenario: user deletes membership details
      Given user has previously saved membership details
      When user clicks on Delete button next to the membership record
      And user confirms the deletion

    @NegativeMembership @MandatoryFields
    Scenario: user tries to save with missing mandatory fields
      When user leaves required fields blank
      And employee clicks on Save button
      Then an error message should be displayed for each missing mandatory field
      Then error message should be displayed saying "Required"

    @EmptyFieldsMembership
    Scenario: Show error when required fields are missing
      When user clicks on Add Membership button
      And user selects "Membership1" from Membership dropdown
      And user leaves Subscription Paid By, Subscription Amount, Currency, Subscription Commence Date, and Subscription Renewal Date blank
      And employee clicks on Save button





