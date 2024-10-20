@FeatureSetup
Feature: User Profile Validation and Editing
  After successfully logging into the application, this feature tests the ability to validate and edit the user profile, ensuring users can effectively manage their information.

  Scenario: Edit user profile
    Given I am on the Homepage
    When I click on the "SignIn" button
    And I enter a valid "username"
    And I enter a valid "password"
    And I click on the "submitsignin" button
    Then I should be redirected to the Dashboard
    And I navigate to the Profile section and click on the Edit Profile icon
    And I click on the "saveprofile" button
    Then I validate that the updated name is visible after refreshing the page
