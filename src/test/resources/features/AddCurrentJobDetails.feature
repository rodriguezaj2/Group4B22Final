Feature: Add or Update Current Job Details for Employees

  As an HRMS user,
  I want to be able to add and update current job details for employees,
  So that employee records are complete and reflect accurate job information.


    Background:
       #Given user is able to access HRMS application
        When user enters valid username and password
        And user clicks login button
        Then user is able to see the dashboard page
        When user clicks on PIM option
        And user clicks on employee list option
        When user enters valid employee id
        And user clicks on search button
        Then user is able to see employee details

      @addCurrentJobDetails
      Scenario: Add valid current job details for an employee
      When the user navigates to the Job section and clicks
      Then the user clicks on Edit button to update current job details
      When the Admin user selects "Software Engineer" from Job Title dropdown
      And the Admin user selects "remote" from Employment Status dropdown
      And the Admin user selects "IT" from Job Category dropdown
      And the Admin user selects "2022-01-15" from Joined Date calendar
      And the Admin user selects "Team Leaders" from Sub Unit dropdown
      And the Admin user selects "Chicago" from Location dropdown
      And the Admin user selects "2022-01-20" from Start Date calendar
      And the Admin user leaves End Date calendar empty
        And the Admin user uploads a valid contract file "EMP_CONTRACT_FILE_PATH"
      And the user clicks the save button
      Then the Current job details should be updated successfully
      And the changes should be reflected in the database



