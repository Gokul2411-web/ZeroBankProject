Feature: Fund Transfer

  Scenario: Successful Fund Transfer
    Given User logs in to home page
    And User navigates to the Fund Transfer page
    When User transfers 1000 from "Savings" to "Checking"
    Then User should see a success message
    And Account balances should be updated accordingly

  #Scenario: Fund Transfer with Usernsufficient Balance
    #Given User logs in to home page
    #And User navigates to the Fund Transfer page
    #When User transfers 5000 from "Checking" to "Savings"
    #Then User should see an error message for insufficient funds
    #And Transfer should not be processed
#
  #Scenario: Fund Transfer with Negative Amount
    #Given User logs in to home page
    #And User navigates to the Fund Transfer page
    #When User transfer -100 from "Checking" to "Savings"
    #Then User should see an error message for invalid amount
