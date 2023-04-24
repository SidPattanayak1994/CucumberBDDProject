Feature: Login Feature
  
  @Sanity 
  Scenario: Successful login with valid credentials
    Given User launch Chrome browser
    When User opens URL "https://admin-demo.nopcommerce.com/login"
    And User enters Email as "admin@yourstore.com" and password as "admin"
    And click on Login button
    Then Page Title should be "Dashboard / nopCommerce administration"
    When click on logout link
    Then Page Title should be "Your store. Login"
    And close browser
    
   @regression 
   Scenario Outline: Successful login with valid credentials DDT
    Given User launch Chrome browser
    When User opens URL "https://admin-demo.nopcommerce.com/login"
    And User enters Email as "<email>" and password as "<password>"
    And click on Login button
    Then Page Title should be "Dashboard / nopCommerce administration"
    When click on logout link
    Then Page Title should be "Your store. Login"
    And close browser
Examples:
|email|password|
|admin@yourstore.com|admin|
|test@yourstore.com|admin|