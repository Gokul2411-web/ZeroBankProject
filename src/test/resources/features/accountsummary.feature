Feature: Account Summary Module Functionality

  Scenario: Account Summary Page Loads Correctly
    Given User is on login page 
    When User logs into the application
    And User clicks on the Online Banking
    And User clicks on the Account Summary
    Then User should see all account types and balances displayed

  Scenario: Validate Account Types
    Given User is on login page 
    When User logs into the application
    And User navigates to the Account Summary page   
    Then User should see the following account types displayed:
      | Cash Accounts       |
      | Investment Accounts |
      | Credit Accounts     |
      | Loan Accounts       |