package api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.TransactionModel;
import model.UserModel;

import static io.restassured.RestAssured.given;

public class Transaction {

    public String baseUrl;

    public Transaction(String baseUrl){
        this.baseUrl = baseUrl;
    }

    public JsonPath deposit(String token, String partnerKey, TransactionModel transactionModel){
        RestAssured.baseURI=baseUrl;

        Response res =given().contentType("application/json")
                .header("Authorization",token)
                .header("X-AUTH-SECRET-KEY",partnerKey)
                .body(transactionModel)
                .when()
                .post("/transaction/deposit")
                .then().assertThat()
                .statusCode(201)
                .extract().response();

        System.out.println(res.asString());

        JsonPath jsonPath =res.jsonPath();

        return jsonPath;
    }

    public JsonPath withdraw(String token, String partnerKey, TransactionModel transactionModel){
        RestAssured.baseURI=baseUrl;

        Response res =given().contentType("application/json")
                .header("Authorization",token)
                .header("X-AUTH-SECRET-KEY",partnerKey)
                .body(transactionModel)
                .when()
                .post("/transaction/withdraw")
                .then().assertThat()
                .statusCode(201)
                .extract().response();

        System.out.println(res.asString());

        JsonPath jsonPath =res.jsonPath();

        return jsonPath;
    }

    public JsonPath sendMoney(String token, String partnerKey, TransactionModel transactionModel){
        RestAssured.baseURI=baseUrl;

        Response res =given().contentType("application/json")
                .header("Authorization",token)
                .header("X-AUTH-SECRET-KEY",partnerKey)
                .body(transactionModel)
                .when()
                .post("/transaction/sendmoney")
                .then().assertThat()
                .statusCode(201)
                .extract().response();

        System.out.println(res.asString());

        JsonPath jsonPath =res.jsonPath();

        return jsonPath;
    }

    public JsonPath payment(String token, String partnerKey, TransactionModel transactionModel){
        RestAssured.baseURI=baseUrl;

        Response res =given().contentType("application/json")
                .header("Authorization",token)
                .header("X-AUTH-SECRET-KEY",partnerKey)
                .body(transactionModel)
                .when()
                .post("/transaction/payment")
                .then().assertThat()
                .statusCode(201)
                .extract().response();

        System.out.println(res.asString());

        JsonPath jsonPath =res.jsonPath();

        return jsonPath;
    }

    public void checkBalance(String token, String partnerKey, String phone){
        RestAssured.baseURI=baseUrl;

        Response res =given().contentType("application/json")
                .header("Authorization",token)
                .header("X-AUTH-SECRET-KEY",partnerKey)
                .when()
                .get("/transaction/balance/"+phone)
                .then().assertThat()
                .statusCode(200)
                .extract().response();

        System.out.println(res.asString());

    }

}