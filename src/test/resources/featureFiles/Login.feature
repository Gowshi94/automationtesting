Feature:Amazon Login
Scenario Outline:Verify login with valid and invalid user credentials
Given User should be on amazon login page
When User enters "<username>" and "<password>"
Then login result should be as "<expected>"

Examples:
| username   | password  | expected |
| 9566269275 | Gowshi@12 | valid    |
| wronguser  | wrongpass | invalid  |
