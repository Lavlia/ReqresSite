Feature: Check the functionality of the Register feature
  As an user
  I want to register
  So i can become a member of the site

#First scenario
  @TC06
  Scenario: Check the response on POST operation for Register an user with valid data
    Given User is on Reqres site on Register page and filling the field with valid data
      | email              | password |
      | eve.holt@reqres.in | pistol   |
    When User sends the request to register
    Then The status code, for register, should be 200 Ok

#Second scenario
  @TC07
  Scenario: Check the response on POST operation for Register an user with no email send
    Given User is on Reqres site on Register page and filling only the password field
      | password |
      | pistol   |
    When User sends the request to register
    Then The status code, for register, should be 400 Bad Request

#Third scenario
  @TC08
  Scenario: Check the response on POST operation for Register an user with no password send
    Given User is on Reqres site on Register page and filling only the email field
      | email              |
      | eve.holt@reqres.in |
    When User sends the request to register
    Then The status code, for register, should be 400 Bad Request

#Fourth scenario
  @TC09
  Scenario: Check the response on POST operation for Register an user with no user credentials send
    Given User is on Reqres site on Register page and not filling the fields required
      | email | password |
      |       |          |
    When User sends the request to register
    Then The status code, for register, should be 400 Bad Request

#Fifth scenario
  @TC10
  Scenario: Check the response on POST operation for Register an user with invalid user credentials
    Given User is on Reqres site on Register page and filling the fields with valid data for an invalid user
      | email                  | password |
      | April_spring@reqres.in | pistol   |
    When User sends the request to register
    Then The status code, for register an invalid user, should be 400 Bad Request