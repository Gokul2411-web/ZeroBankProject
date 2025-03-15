 Feature: Login Functionality
 
  Scenario: Successful Login
    Given User is on the login page
    When User enters valid credentials "username" and "password"
    And User clicks on login button
    Then User should be redirected to the Account Summary page

  Scenario: Invalid Login
    Given User is on the login page
    When User enters invalid credentials "wrongUser" and "wrongPass"
    And User clicks on login button
    Then User should see an error message "Login and/or password are wrong."

  Scenario: Empty Username & Password Validation
    Given User is on the login page
    When User enters an empty username and password
    And User clicks on login button
    Then User should see an error message "Login and/or password are wrong."