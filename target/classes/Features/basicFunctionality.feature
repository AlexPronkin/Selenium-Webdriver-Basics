Feature: Basic functionality

  Scenario: Check elements visibility
    When User opens "https://www.bookdepository.com/" page
    And User checks logo visibility
    And User checks Sign-in button visibility
    And User checks search field visibility
    And User checks navigation menu visibility
    And User checks banner visibility
    And User checks cart button visibility

  Scenario: Displaying the number of products in the cart
    When User opens "https://www.bookdepository.com/" page
    And User search for "lord of the rings"
    And User click Add to cart button for first product
    And User closes confirmation popup
    Then Cart shows "1" item

    Scenario: User opens first search result product page
      When  User opens "https://www.bookdepository.com/" page
      And User search for "lord of the rings"
      And User clicks on first result product title
      Then User sees product description