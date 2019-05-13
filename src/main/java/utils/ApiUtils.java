package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiUtils {
    public static RequestSpecification getRequestSpecification() {
        return RestAssured.given().contentType(ContentType.JSON);
    }
}
