Tests 2

Meta:
@group google


Scenario: search query 1
Given I am on the homepage
When I search for <query>
Then <query> is in resultlist

Examples:
|query|
|testing|
|WebDriver|
|JBehave|

Scenario: search query 2
Given I am on the homepage
When I search for <query>
Then <query> is in resultlist

Examples:
|query|
|testing|
|WebDriver|
|JBehave|