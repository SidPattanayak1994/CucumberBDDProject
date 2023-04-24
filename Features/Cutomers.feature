Feature: Customer

  Background: Common steps for every Test Cases
    Given User launch Chrome browser
    When User opens URL "https://admin-demo.nopcommerce.com/login"
    And User enters Email as "admin@yourstore.com" and password as "admin"
    And click on Login button
    Then User can view dashboard

  @Sanity
  Scenario: Add new Customer
    When User clicks on Customers menu
    And click on customers menu Item
    And click on Add new button
    Then user can view Add new customer page
    When user enter cutomer info
    And click on save button
    Then user can view confirmation message "The new customer has been added successfully."
    And close browser

  @regression
  Scenario: Search Customer by EmailId
    When User clicks on Customers menu
    And click on customers menu Item
    And Enter customer Email
    When click on search button
    Then user should found Email in the search table
    And close browser

  @regression
  Scenario: Search Customer by name
    When User clicks on Customers menu
    And click on customers menu Item
    And Enter cutomer firstName
    And Enter cutomer lastName
    When click on search button
    Then User should found Name in search table
    And close browser
