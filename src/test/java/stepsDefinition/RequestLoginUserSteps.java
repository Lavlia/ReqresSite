package stepsDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.ApiUtils;
import utils.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RequestLoginUserSteps {
    Response response;
    RequestSpecification requestSpecification = new ApiUtils().getRequestSpecification();

    //First scenario - TC15
    @Given("User is on Reqres site on Login page and filling the fields with valid data")
    public void userIsOnReqresSiteOnLoginPageAndFillingTheFieldsWithValidData(DataTable table) {
        List<Map<String, String>> data = table.asMaps();

        Map<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("email", data.get(0).get("email"));
        jsonObject.put("password", data.get(0).get("password"));

        requestSpecification.body(jsonObject);
    }

    @When("User sends the request to login")
    public void userSendsTheRequestToLogin() {
        response = requestSpecification.post(Constants.LOGIN_RESOURCE);
        response.then().log().all();
    }

    @Then("The status code, for login, should be (\\d+) Ok")
    public void theStatusCodeForLoginShouldBeOk(int statusCodeOK) {
        Assert.assertEquals(response.statusCode(), statusCodeOK);

        Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS) <= 10, "Response time is not within limit");

        SoftAssert softAssert = new SoftAssert();
        JsonPath json = ApiUtils.rawToJson(response);
        String token = json.get("token");
        softAssert.assertTrue(!token.equalsIgnoreCase(" "), "Token is not blank");
        softAssert.assertAll();
    }

    //Second scenario - TC16
    @Given("User is on Reqres site on Login page and filling only the password field")
    public void userIsOnReqresSiteOnLoginPageAndFillingOnlyThePasswordField(DataTable table) {
        List<Map<String, String>> data = table.asMaps();

        Map<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("email", data.get(0).get("email"));
        jsonObject.put("password", data.get(0).get("password"));

        requestSpecification.body(jsonObject);

    }

    @Then("The status code, for login, should be (\\d+) Bad Request")
    public void theStatusCodeForLoginShouldBeBadRequest(int statusCodeBadRequest) {
        Assert.assertEquals(response.statusCode(), statusCodeBadRequest);

        Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS) <= 10, "Response time is not within limit");

        SoftAssert softAssert = new SoftAssert();
        JsonPath json = ApiUtils.rawToJson(response);
        String error = json.get("error");
        softAssert.assertTrue(error.contains("Missing"), "Error message is displayed");
        softAssert.assertAll();
    }

    //Third scenario - TC17
    @Given("User is on Reqres site on Login page and filling only the email field")
    public void userIsOnReqresSiteOnLoginPageAndFillingOnlyTheEmailField(DataTable table) {
        List<Map<String, String>> data = table.asMaps();

        Map<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("email", data.get(0).get("email"));
        jsonObject.put("password", data.get(0).get("password"));

        requestSpecification.body(jsonObject);
    }

    //Fourth scenario - TC18
    @Given("User is on Reqres site on Login page and not filling the required fields")
    public void userIsOnReqresSiteOnLoginPageAndNotFillingTheRequiredFields(DataTable table) {
        List<Map<String, String>> data = table.asMaps();

        Map<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("email", data.get(0).get("email"));
        jsonObject.put("password", data.get(0).get("password"));

        requestSpecification.body(jsonObject);
    }

    //Fifth scenario - TC19
    @Given("User is on Reqres site on Login page and filling the fields with valid data for an invalid user")
    public void userIsOnReqresSiteOnLoginPageAndFillingTheFieldsWithValidDataForAnInvalidUser(DataTable table) {
        List<Map<String, String>> data = table.asMaps();

        Map<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("email", data.get(0).get("email"));
        jsonObject.put("password", data.get(0).get("password"));

        requestSpecification.body(jsonObject);
    }

    @Then("The status code, for login an invalid user, should be (\\d+) Bad Request")
    public void theStatusCodeForLoginAnInvalidUserShouldBeBadRequest(int statusCodeBadRequest) {
        Assert.assertEquals(response.statusCode(), statusCodeBadRequest);

        Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS) <= 10, "Response time is not within limit");

        SoftAssert softAssert = new SoftAssert();
        JsonPath json = ApiUtils.rawToJson(response);
        String error = json.get("error");
        softAssert.assertTrue(error.equalsIgnoreCase("user not found"), "Error message is displayed");
        softAssert.assertAll();
    }
}
