Feature: Statements & Documents Module

  Scenario: Download Account Statement
    Given User logs in and navigates to Statements & Documents page
    When User selects account "Checking"
    And User selects year
    And User clicks on PDF link
    Then The statement should be downloaded successfully
