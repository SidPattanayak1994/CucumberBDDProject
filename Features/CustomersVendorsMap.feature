Feature: Customer Vendors 

  Background: Common steps for every Test Cases
    Given User launch Chrome browser
    When User opens URL 
    And User enters Credentials
    And click on Login button
    Then User can view dashboard
    When User clicks on Customers menu
    
    
    @Sanity
  Scenario: Search a vendor by entering vendor name   
    And click on Vendors menu Item 
    Then user can view Add vendors page
    When user enter Vendor name
    |Vendor name|
    |Vendor 1   |
    |Vendor 2   |
    When click on Vendors page search button  
    Then user should found Email in the search table in vendors page
    And close browser
    