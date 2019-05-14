package stepsDefinition;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import utils.ApiUtils;
import utils.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RequestUsersInfoSteps extends ApiUtils {

    Response response;
    RequestSpecification requestSpecification = new ApiUtils().getRequestSpecification();

    //First scenario - TC01
    @Given("^User is on Reqres site on different ([^\"]*)$")
    public void userIsOnReqresSiteOnDifferentPages(String pageNumber) {
        requestSpecification.queryParam("page", pageNumber);
    }

    @When("^User wants to retrieve a list of users$")
    public void userWantsToRetrieveAListOfUsers() {
        response = requestSpecification.get(Constants.RESOURCE);
        response.then().log().all();
    }

    @Then("^The status code should be (\\d+) Ok$")
    public void theStatusCodeShouldBeOk(int statusCodeOk) {
        Assert.assertEquals(response.statusCode(), statusCodeOk);
        Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS) <= 10, "Response time is not within limit");
    }


    //Second scenario - TC02
    @Given("^User is on Reqres site on user page with id (\\d+)$")
    public void userIsOnReqresSiteOnUserPageWithId(int id) {
        requestSpecification.pathParam("id", id);
    }

    @When("User wants to retrieve details for an user with that id")
    public void userWantsToRetrieveDetailsForAnUserWithThatId() {
        response = requestSpecification.get(Constants.PATH_ID_RESOURCE);
        response.then().log().all();
    }

    //Third scenario - TC03
    @Given("User is on Reqres site and filling an user form")
    public void userIsOnReqresSiteAndFillingAnUserForm(DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("email", data.get(0).get("email"));
        jsonObject.put("first_name", data.get(0).get("first_name"));
        jsonObject.put("last_name", data.get(0).get("last_name"));
        jsonObject.put("job", data.get(0).get("job"));

        requestSpecification.body(jsonObject);
    }

    @When("User sends the user form filled out")
    public void userSendsTheUserFormFilledOut() {
        response = requestSpecification.post(Constants.RESOURCE);
        response.then().log().all();
    }

    @Then("The status code should be (\\d+) Created")
    public void theStatusCodeShouldBeCreated(int statusCodeCreated) {
        Assert.assertEquals(response.statusCode(), statusCodeCreated);
        Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS) <= 10, "Response time is not within limit");
    }

    //Fourth scenario - TC04
    @Given("^User is on Reqres site and updating an user$")
    public void userIsOnReqresSiteAndUpdatingAnUserWithId(DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        requestSpecification.pathParam("id", data.get(0).get("id"));

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("name", data.get(0).get("name"));
        jsonObject.put("job", data.get(0).get("job"));

        requestSpecification.body(jsonObject);
    }

    @When("^User sends a request to update his/her details$")
    public void userSendsARequestToUpdateHisHerDetails() {
        response = requestSpecification.put(Constants.PATH_ID_RESOURCE);
        response.then().log().all();
    }

    //Fifth scenario - TC05
    @Given("User is on Reqres site on user page")
    public void userIsOnReqresSiteOnUserPage(DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("id", data.get(0).get("id"));

        requestSpecification.body(jsonObject);
    }

    @When("^User wants to delete his/her account$")
    public void userWantsToDeleteHisHerAccount() {
        response = requestSpecification.delete(Constants.RESOURCE);
        response.then().log().all();
    }

    @Then("The status code should be (\\d+) No Content")
    public void theStatusCodeShouldBeNoContent(int statusCodeNoContent) {
        Assert.assertEquals(response.statusCode(), statusCodeNoContent);
        Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS) <= 10, "Response time is not within limit");
    }

    //Sixth scenario - TC06
    @Given("^User is on Reqres site on user page with email \"([^\"]*)\"$")
    public void user_is_on_reqres_site_on_user_page_with_email_something(String email) {
        requestSpecification.pathParam("email", email);

    }

    @When("User wants to retrieve details for an user with that email")
    public void userWantsToRetrieveDetailsForAnUserWithThatEmail() {
        response = requestSpecification.get(Constants.PATH_EMAIL_RESOURCE);
        response.then().log().all();
    }

    @Then("^The status code should be (\\d+) Not Found$")
    public void theStatusCodeShouldBeNotFound(int statusCodeNotFound) {
        Assert.assertEquals(response.statusCode(), statusCodeNotFound);
        Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS) <= 10, "Response time is not within limit");
    }

    //Seventh scenario - TC07
    @Given("User is on Reqres site and filling an user form for invalid user")
    public void userIsOnReqresSiteAndFillingAnUserFormForInvalidUser(DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("job", data.get(0).get("job"));

        requestSpecification.body(jsonObject);
    }

    @When("User sends the user form filled out for invalid user")
    public void userSendsTheUserFormFilledOutForInvalidUser() {
        response = requestSpecification.post(Constants.RESOURCE);
        response.then().log().all();
    }

    @Then("The status code should be (\\d+) Bad Request")
    public void theStatusCodeShouldBeBadRequest(int statusCodeBadRequest) {
        Assert.assertEquals(response.statusCode(), statusCodeBadRequest);
        Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS) <= 10, "Response time is not within limit");
    }

    //Eighth scenario - TC08
    @Given("User is on Reqres site and updating an user with no payload")
    public void userIsOnReqresSiteAndUpdatingAnUserWithNoPayload(DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        requestSpecification.pathParam("id", data.get(0).get("id"));

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("name", data.get(0).get("name"));
        jsonObject.put("job", data.get(0).get("job"));

        requestSpecification.body(jsonObject);
    }

    @Then("The status code should be (\\d+) Forbidden")
    public void theStatusCodeShouldBeForbidden(int statusCodeForbidden) {
        Assert.assertEquals(response.statusCode(), statusCodeForbidden);
        Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS) <= 10, "Response time is not within limit");
    }

    //Ninth scenario - TC09
    @Given("User is on Reqres site on users page")
    public void userIsOnReqresSiteOnUsersPage(DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("first_name", data.get(0).get("first_name"));

        requestSpecification.body(jsonObject);
    }

    @When("^User wants to delete his/her account with first name George$")
    public void userWantsToDeleteHisHerAccountWithFirstNameGeorge() {
        response = requestSpecification.delete(Constants.RESOURCE);
        response.then().log().all();
    }
}
