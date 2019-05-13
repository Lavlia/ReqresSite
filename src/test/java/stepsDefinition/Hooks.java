package stepsDefinition;

import io.restassured.RestAssured;
import cucumber.api.java.Before;
import utils.Constants;

public class Hooks {

    @Before
    public void initialization() {
        RestAssured.baseURI = Constants.ENDPOINT;
    }

}
