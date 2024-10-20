@FeatureSetup
Feature: Dashboard Validation Post-Login
  After successfully logging into the application, this feature tests the visibility and functionality of all buttons and links on the dashboard, ensuring that users can easily access key features and navigate the interface effectively.

  Scenario: Validate the visibility of all elements and button
    Given I am on the Homepage
    When I click on the "SignIn" button
    And I enter a valid "username"
    And I enter a valid "password"
    And I click on the "submitsignin" button
    Then I should be redirected to the Dashboard
    Then I validate all the links and button should be visible on Dashboard
