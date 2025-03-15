Feature: Pay Bills

  Scenario: Successful Bill Payment
    Given User logs in and User navigates to the Pay Bills page
    When User select a payee "Wells Fargo"
    And User enters valid amount 1500 and date "2025-03-15"
    And User clicks pay button
    Then User sees a success message

  Scenario: Schedule a Bill Payment for a Future Date
    Given User logs in and User navigates to the Pay Bills page
    When User select a payee "Bank of America"
    And User schedules a payment of 2000 on "2025-03-24"
    Then The payment should be scheduled successfully

  Scenario: Pay Bills Without Entering Amount
    Given User logs in and User navigates to the Pay Bills page
    When User select a payee "Sprint"
    And User	leaves the amount field empty.
    Then Error message "Amount field cannot be empty." should be displayed
