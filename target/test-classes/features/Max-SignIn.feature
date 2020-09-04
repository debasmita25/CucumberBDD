@sanity
Feature: In order to test Max SignIn using chrome browser
         As an User

  Background: 
    Given User opens "browserChrome"
    And User navigates to URL "url"
    And User clicks on SignIn link

  @validchrome
  Scenario Outline: Verify that the SignIn is successful when correct email and correct password are provided
    When User enters "<Username>" and "<Password>" and clicks on SignIn
    Then User should be able to SignIn Successfully

    Examples: 
      | Username              | Password     |
      | debasmita25@gmail.com | friday@12345 |

  @valid
  Scenario Outline: Verify that the SignIn is successful when correct email and correct password are provided
    When User enters "<Username>" and "<Password>" and clicks on SignIn
    Then User should be able to SignIn Successfully

    Examples: 
      | Username              | Password     |
      | debasmita25@gmail.com | friday@12345 |

  @invalid
  Scenario Outline: Verify that the SignIn is unsuccessful when invalid email and invalid password are provided
    When User enters invalid "<Username>" and invalid "<Password>" and clicks on SignIn
    Then User should get error "<message>"

    Examples: 
      | Username              | Password     | message                                          |
      | debasmita25@gmail.com |              | Enter Your Password                              |
      |                       | friday@12345 | Enter your email address                         |
      | deba@gmail.com        | friday@12345 | Your Username or Password is incorrect           |
      | debasmita25@gmail.com | fgsh1234     | Your Username or Password is incorrect           |
      |                       |              | Enter your email address and Enter Your Password |
