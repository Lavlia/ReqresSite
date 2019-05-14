Feature: Check the functionality of the Login feature
  As an user
  I want to login on the site
  So i can interact with other users

#First scenario
  @TC15
  Scenario: Check the response on POST operation for user Login with valid data
    Given User is on Reqres site on Login page and filling the fields with valid data
      | email              | password   |
      | eve.holt@reqres.in | cityslicka |
    When User sends the request to login
    Then The status code, for login, should be 200 Ok

#Second scenario
  @TC16
  Scenario: Check the response on POST operation for user Login with no email send
    Given User is on Reqres site on Login page and filling only the password field
      | email | password |
      |       | bullet   |
    When User sends the request to login
    Then The status code, for login, should be 400 Bad Request

#Third scenario
  @TC17
  Scenario: Check the response on POST operation for user Login with no password send
    Given User is on Reqres site on Login page and filling only the email field
      | email              | password |
      | eve.holt@reqres.in |          |
    When User sends the request to login
    Then The status code, for login, should be 400 Bad Request

#Fourth scenario
  @TC18
  Scenario: Check the response on POST operation for user Login with no user credentials send
    Given User is on Reqres site on Login page and not filling the required fields
      | email | password |
      |       |          |
    When User sends the request to login
    Then The status code, for login, should be 400 Bad Request

#Fifth scenario
  @TC19
  Scenario: Check the response on POST operation for Login with invalid user credentials
    Given User is on Reqres site on Login page and filling the fields with valid data for an invalid user
      | email                  | password |
      | April_spring@reqres.in | bullet   |
    When User sends the request to login
    Then The status code, for login an invalid user, should be 400 Bad Request