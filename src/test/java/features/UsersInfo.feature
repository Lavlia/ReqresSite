Feature: Check the functionality of the Users feature
  As an user
  I want to explore the site
  So i can become an user, search my friends and delete my account

#First scenario
  @TC01
  Scenario Outline: Check the response on GET operation for retrieving details for a list of users
    Given User is on Reqres site on different <pages>
    When User wants to retrieve a list of users
    Then The status code should be 200 Ok
    Examples:
      | pages |
      | 1     |
      | 2     |
      | 3     |
      | 4     |

#Second scenario
  @TC02
  Scenario: Check the response on GET operation for retrieving details for an user
    Given User is on Reqres site on user page with id 6
    When User wants to retrieve details for an user with that id
    Then The status code should be 200 Ok

#Third scenario
  @TC03
  Scenario: Check the response on POST operation for creating an user
    Given User is on Reqres site and filling an user form
      | email                | first_name | last_name | job    |
      | may_maymay@reqres.in | may        | maymay    | tester |
    When User sends the user form filled out
    Then The status code should be 201 Created

#Fourth scenario
  @TC04
  Scenario: Check the response on PUT operation for updating an user
    Given User is on Reqres site and updating an user
      | name  | job           | id |
      | April | manual tester | 6  |
    When User sends a request to update his/her details
    Then The status code should be 200 Ok

#Fifth scenario
  @TC05
  Scenario: Check the response on DELETE operation for deleting an user
    Given User is on Reqres site on user page
      | id |
      | 6  |
    When User wants to delete his/her account
    Then The status code should be 204 No Content

#Sixth scenario
  @TC06
  Scenario: Check the response on GET operation for retrieving details for an user and using invalid field
    Given User is on Reqres site on user page with email "george.bluth@reqres.in"
    When User wants to retrieve details for an user with that email
    Then The status code should be 404 Not Found

#Seventh scenario
  @TC07
  Scenario: Check the response on POST operation for creating an user with required fields not filled out
    Given User is on Reqres site and filling an user form for invalid user
      | job    |
      | tester |
    When User sends the user form filled out for invalid user
    Then The status code should be 400 Bad Request

#Eighth scenario
  @TC08
  Scenario: Check the response on PUT operation for updating an user with no payload send
    Given User is on Reqres site and updating an user with no payload
      | name | job | id |
      |      |     | 6  |
    When User sends a request to update his/her details
    Then The status code should be 403 Forbidden

#Ninth scenario
  @TC09
  Scenario: Check the response on DELETE operation for deleting an user using an invalid field
    Given User is on Reqres site on users page
      | first_name |
      | George     |
    When User wants to delete his/her account
    Then The status code should be 403 Forbidden
