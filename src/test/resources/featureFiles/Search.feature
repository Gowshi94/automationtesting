Feature:Amazon Search
Scenario Outline:Verify product search with filter
Given User should be on amazon home page
When user searches for product "<product>"
And applies "<filter>"
Then search results should be displayed

Examples:
| product     | filter             |
| mens shirts | Newest Arrivals    |
| mens shirts | Price: Low to High |
