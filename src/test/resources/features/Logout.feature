@FeatureSetup
Feature: GitHub Logout Functionality
  To ensure users can safely exit their accounts, this feature tests the logout process.

  Scenario: Successful logout
    Given I am on the Homepage
    When I click on the "SignIn" button
    And I enter a valid "username"
    And I enter a valid "password"
    And I click on the "submitsignin" button
    Then I should be redirected to the Dashboard
    When I click on the "profileImage" button
    Then I scroll to the element and click on it
