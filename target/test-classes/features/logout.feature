Feature: Logout Functionality

  Scenario: Logout Successfully
    Given User logs in with valid credentials
    When Verify the dashboard loads successfully
    And User clicks on the Logout button
    Then User should be redirected to the Login page

  Scenario: Verify Session Expiry After Logout
    Given User logs in with valid credentials
    When User clicks on the Logout button
    And User clicks the Back button in the browser
    Then User should not be able to access the previous page
