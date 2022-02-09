Feature: Basic functionality

  Scenario: Check elements visibility
    When User opens "https://www.bookdepository.com/" page
    Then checks logo visibility
    And checks Sign-in button visibility
    And checks search field visibility
    And checks navigation menu visibility
    And checks banner visibility
    And checks cart button visibility

  Scenario: Displaying the number of products in the cart
    Given User opens "https://www.bookdepository.com/" page
    When search for "lord of the rings"
    And clicks Add to cart button for first product
    And clicks Close on confirmation popup
    Then Cart shows "1" item

  Scenario: User opens first search result product page
    Given  User opens "https://www.bookdepository.com/" page
    When search for "lord of the rings"
    And clicks on first result product title
    Then sees product description

  Scenario: User proceeds to checkout and input an email
    Given User opens "https://www.bookdepository.com/" page
    And search for "lord of the rings"
    And clicks Add to cart button for first product
    And clicks BasketCheckout button on confirmation popup
    And clicks Checkout button on Basket page
    Then Item price and Total price are equal in Order Summary
    And User enters test@user.com email address

