package com.onespatial.reqrestest.apicalls;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

public class APICalls {

    public Response getRequest(String URI,int statusCode){
        /*Get the RequestSpecification of the request that you want to sent
        to the server. The server is specified by the BaseURI that we have specified in the above step.*/
        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest
                .contentType(ContentType.JSON)
                .when()
                .get(URI)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .extract().response();
        return response;
    }

    public Response postRequest(JSONObject jsonObject, int statusCode,String URI ){

        /*Get the RequestSpecification of the request that you want to sent
        to the server. The server is specified by the BaseURI that we have specified in the above step.*/
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest
                .header("Content-type", "application/json")
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .post(URI)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .extract().response();
        return response;
    }

    public Response putRequest(JSONObject jsonObject, int statusCode,String URI ){

        /*Get the RequestSpecification of the request that you want to sent
        to the server. The server is specified by the BaseURI that we have specified in the above step.*/
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest
                .header("Content-type", "application/json")
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .put(URI)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .extract().response();
        return response;
    }

    public Response patchRequest(JSONObject jsonObject, int statusCode,String URI ){

        /*Get the RequestSpecification of the request that you want to sent
        to the server. The server is specified by the BaseURI that we have specified in the above step.*/
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest
                .header("Content-type", "application/json")
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .patch(URI)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .extract().response();
        return response;
    }

    public Response deleteRequest(int statusCode,String URI){

        /*Get the RequestSpecification of the request that you want to sent
        to the server. The server is specified by the BaseURI that we have specified in the above step.*/
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest
                .header("Content-type", "application/json")
                .when()
                .delete(URI)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .extract().response();
        return response;
    }
}
