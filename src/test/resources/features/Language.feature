Feature: Add Language Proficiency Details

Background:
 # Given user is able to access HRMS application
  When employee enters valid username and password
  And user clicks login button
  Then user is able to see the dashboard page
  When the user navigates to the language section

  @addLanguageProficiency
  Scenario: Add a language proficiency with valid values
    When the user clicks the Add button
    And the user selects "Spanish" as Language
    And the user selects "Basic" as Fluency
    And the user selects "Speaking" as Competency
    And the user enters "I am good at Spanish" as comment
    And the user clicks on Save
    Then "Spanish" should be listed in the language table

    @editLanguageProficiency
    Scenario: Edit an existing language proficiency
      Given "Spanish" should be listed in the language table
      When the user clicks on the "Spanish"
      And the user changes competency to "Writing"
      And the user clicks on Save
      Then the updated competency for "Spanish" should be "Writing"

      @deleteLanguageProficiency
      Scenario: Delete an existing language proficiency
        Given "Spanish" should be listed in the language table
        When the user clicks on the "Spanish" checkbox
        And the user clicks on the Delete button
        Then the language entry for "Spanish" with fluency "Basic" and competency "Writing" should no longer be listed

        @emptyFields
  Scenario: Show error when required fields are missing
    When the user clicks the Add button
    And the user selects "Spanish" as Language
    And the user leaves Fluency and Competency blank
    And the user clicks on Save
    Then an error message should be displayed saying "Required"