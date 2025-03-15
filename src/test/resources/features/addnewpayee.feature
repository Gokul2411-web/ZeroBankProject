Feature: Add a New Payee Functionality

  Scenario: Successfully Adding a New Payee
    Given User logs in and navigates to Add New Payee page
    When User enters payee name "Gokul"
    And User enters address "24 Main St, Chennai"
    And User enters account number "123456789"
    And User clicks add payee button
    Then User should see "Payee added successfully" message

  Scenario: Add Payee with Missing Details
    Given User logs in and navigates to Add New Payee page
    When User enters payee name "Sharath Kumar"
    And User enters address "24 Main St, Chennai"
    And User leaves account number empty
    And User clicks add payee button
    Then Error message "All fields are required." will be displayed
