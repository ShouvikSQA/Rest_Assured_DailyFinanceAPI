package controller;

import config.UserModel;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Properties;

import static io.restassured.RestAssured.given;

public class RegistrationController {

    Properties properties;

    public RegistrationController(Properties properties) {
        this.properties = properties;
        RestAssured.baseURI = "https://dailyfinanceapi.roadtocareer.net";
    }

    public Response register(UserModel userModel) {

        Response response = given().contentType("application/json")
                .body(userModel).when().post("/api/auth/register");
        return response;

    }

}
