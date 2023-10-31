package api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.UserModel;
import org.json.simple.JSONArray;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class User{

    public String baseUrl;

    public User(String baseUrl){
        this.baseUrl = baseUrl;
    }

    public String callingLoginApi(UserModel model){
        RestAssured.baseURI=baseUrl;

        Response res = given().contentType("application/json")
                .body(model)
                .when()
                .post("/user/login")
                .then().assertThat()
                .statusCode(200)
                .extract().response();

        System.out.println(res.asString());

        JsonPath jsonPath =res.jsonPath();
        String token = jsonPath.get("token");

        return  token;
    }

    public JsonPath createUser(String token, String partnerKey, UserModel userModel){
        RestAssured.baseURI=baseUrl;

        Response res =given().contentType("application/json")
                .header("Authorization",token)
                .header("X-AUTH-SECRET-KEY",partnerKey)
                .body(userModel)
                .when()
                .post("/user/create")
                .then().assertThat()
                .statusCode(201)
                .extract().response();

        System.out.println(res.asString());

        JsonPath jsonPath =res.jsonPath();

        return jsonPath;
    }
}