Feature: Edit Personal Details as ESS User

  As an ESS user,
  I want to be able to edit my personal information in the HRMS application,
  So that I can keep my records up to date.

  Background:
    Given the ESS user is on the HRMS login page
    When the user logs in with valid credentials
    Then the user should be redirected to the dashboard

  @essUser @editPersonalDetails
  Scenario: Verify that personal detail fields are editable and selectable
    Given the user navigates to the My Info section and clicks
    Then the user clicks Edit update personal information
    Then the First name "Fabiola",Middle Name "Jack" and Last Name "Lopez" field should be editable
    And the user should be able to select gender as "Female"
    And the user should be able to select nationality as "Spanish"
    And the user should be able to select marital status as "Married"
    And the user clicks the save button
    Then the personal details should be updated successfully
    And the updated details should be reflected in the database