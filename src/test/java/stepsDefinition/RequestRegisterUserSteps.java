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

public class RequestRegisterUserSteps {

    Response response;
    RequestSpecification requestSpecification = new ApiUtils().getRequestSpecification();

    //First Scenario - TC06
    @Given("User is on Reqres site on Register page and filling the field with valid data")
    public void userIsOnReqresSiteOnRegisterPageAndFillingTheFieldWithValidData(DataTable table) {
        List<Map<String, String>> data = table.asMaps();

        Map<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("email", data.get(0).get("email"));
        jsonObject.put("password", data.get(0).get("password"));

        requestSpecification.body(jsonObject);

    }

    @When("User sends the request to register")
    public void userSendsTheRequestToRegister() {
        response = requestSpecification.post(Constants.REGISTER_RESOURCE);
        response.then().log().all();
    }

    @Then("The status code, for register, should be (\\d+) Ok")
    public void theStatusCodeForRegisterShouldBeOk(int statusCodeOk) {
        Assert.assertEquals(response.statusCode(), statusCodeOk);

        Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS) <= 10, "Response time is not within limit");

        SoftAssert softAssert = new SoftAssert();
        JsonPath json = ApiUtils.rawToJson(response);
        String token = json.get("token");
        softAssert.assertTrue(!token.equalsIgnoreCase(""), "Token is not blank");
        softAssert.assertAll();
    }

    //Second scenario - TC07
    @Given("User is on Reqres site on Register page and filling only the password field")
    public void userIsOnReqresSiteOnRegisterPageAndFillingOnlyThePasswordField(DataTable table) {
        List<Map<String, String>> data = table.asMaps();

        Map<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("password", data.get(0).get("password"));

        requestSpecification.body(jsonObject);
    }

    @Then("The status code, for register, should be (\\d+) Bad Request")
    public void theStatusCodeForRegisterShouldBeBadRequest(int statusCodeBadRequest) {
        Assert.assertEquals(response.statusCode(), statusCodeBadRequest);

        Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS) <= 10, "Response time is not within limit");

        SoftAssert softAssert = new SoftAssert();
        JsonPath json = ApiUtils.rawToJson(response);
        String error = json.get("error");
        softAssert.assertTrue(error.contains("Missing"), "Error message is displayed");
        softAssert.assertAll();
    }

    //Third scenario - TC08
    @Given("User is on Reqres site on Register page and filling only the email field")
    public void userIsOnReqresSiteOnRegisterPageAndFillingOnlyTheEmailField(DataTable table) {
        List<Map<String, String>> data = table.asMaps();

        Map<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("email", data.get(0).get("email"));

        requestSpecification.body(jsonObject);
    }

    //Fourth scenario - TC09
    @Given("User is on Reqres site on Register page and not filling the fields required")
    public void userIsOnReqresSiteOnRegisterPageAndNotFillingTheFieldsRequired(DataTable table) {
        List<Map<String, String>> data = table.asMaps();

        Map<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("email", data.get(0).get("email"));
        jsonObject.put("password", data.get(0).get("password"));

        requestSpecification.body(jsonObject);
    }

    //Fifth scenario - TC10
    @Given("User is on Reqres site on Register page and filling the fields with valid data for an invalid user")
    public void userIsOnReqresSiteOnRegisterPageAndFillingTheFieldsWithValidDataForAnInvalidUser(DataTable table) {
        List<Map<String, String>> data = table.asMaps();

        Map<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("email", data.get(0).get("email"));
        jsonObject.put("password", data.get(0).get("password"));

        requestSpecification.body(jsonObject);

    }

    @Then("The status code, for register an invalid user, should be (\\d+) Bad Request")
    public void theStatusCodeForRegisterAnInvalidUserShouldBeBadRequest(int statusCodeBadRequest) {
        Assert.assertEquals(response.statusCode(), statusCodeBadRequest);

        Assert.assertTrue(response.getTimeIn(TimeUnit.SECONDS) <= 10, "Response time is not within limit");

        SoftAssert softAssert = new SoftAssert();
        JsonPath json = ApiUtils.rawToJson(response);
        String error = json.get("error");
        softAssert.assertTrue(error.contains("Note"), "Error message is displayed");
        softAssert.assertAll();
    }
}
