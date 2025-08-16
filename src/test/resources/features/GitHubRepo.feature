Feature: GitHub Repository CRUD Operations

  Scenario: Get an existing repository
    Given user sets base URI
    When user sends GET request to retrieve repository
    Then repository details are returned successfully

  Scenario: Create a new repository
    Given user sets base URI
    When user sends POST request to create a new repository
    Then repository is created successfully

  Scenario: Update repository description
    Given user sets base URI
    When user sends PATCH request to update the description
    Then repository is updated successfully

  Scenario: Delete the created repository
    Given user sets base URI
    When user sends DELETE request to remove repository
    Then repository is deleted successfully