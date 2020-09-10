@Search
Feature: In order to test SearchBox element works fine in MAX Home page
         As a user

  Scenario Outline: In order to validate if for every search three sections Departments,Products and See Matching All options are present in the list
    Given User opens a "browserChrome"
    And User navigates to Max Home "url"
    When User searches for product "<productname>"
    Then User sees first section "Departments" list
    And User sees second section "Products" list
    And User sees "matching products" link

    Examples: 
      | productname    |
      | jacket in kids |

  Scenario Outline: In order to validate that user can add product into basket from See Matching All Products link
    Given User opens a "browserChrome"
    And User navigates to Max Home "url"
    When User searches for product "<productname>"
    And User clicks on "matching products" link
    Then User is navigated to the "<searchedPageTitle>" page
    And User checks for the available filters
      | Price         |
      | Size          |
      | Color Family  |
      | Design        |
      | Type          |
      | Style         |
      | Sleeve Length |
      | Browser       |
      | Promotions    |
    And User and clicks on the "ordinal" product
    Then User adds the product to basket

    Examples: 
      | productname    | searchedPageTitle |
      | jacket in kids | jacket in kids    |
