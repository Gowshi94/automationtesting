Feature:Amazon cart
Scenario Outline:Verify adding products to cart
Given User should be on home page
When User searches for "<product>"
And applies filter "<filter>"
And selects first available product
And selects "<quantity>"
And clicks add to cart button
Then cart count should be updated

Examples:
| product          | filter             | quantity |
| mens shirts      | Newest Arrivals    | 1        |
| womwens slippers | Price: Low to High | 1        |
