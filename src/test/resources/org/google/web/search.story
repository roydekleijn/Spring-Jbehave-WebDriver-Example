Sportal advertisement positions

Meta:
@group google


Scenario: search query
Given I am on the homepage
When I search for <query>
Then <query> is shown in the searchresulttitles

Examples:
|query|
|testing|
|WebDriver|
|JBehave|