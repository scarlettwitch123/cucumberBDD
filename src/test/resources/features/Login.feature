@FeatureSetup
Feature: GitHub Login Functionality
  To ensure users can access their accounts, this feature tests the login process with both valid and invalid credentials.

  Scenario: Unsuccessful login with invalid credentials
    Given I am on the Homepage
    When I click on the "SignIn" button
    And I enter an invalid "invalidUsername"
    And I enter an invalid "invalidPassword"
    And I click on the "submitsignin" button
    Then I should see an error message indicating invalid credentials

  Scenario: Successful login with valid credentials
    Given I am on the Homepage
    When I click on the "SignIn" button
    And I enter a valid "username"
    And I enter a valid "password"
    And I click on the "submitsignin" button
    Then I should be redirected to the Dashboard

