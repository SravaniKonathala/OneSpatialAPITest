package com.onespatial.reqrestest.testrunner;

import com.onespatial.reqrestest.apicalls.APICalls;
import com.onespatial.reqrestest.util.Constants;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReqResTest {

    APICalls APICallsObj = new APICalls();

    //Test to retrieve the data of all the list of user and validate the status code and response
    @Test
    public void testGetListOfUsers(){
        RestAssured.baseURI = Constants.Base_URL;

        // Get the RequestSpecification of the request that you want to sent
        // to the server. The server is specified by the BaseURI that we have
        // specified in the above step.
        RequestSpecification httpRequest = RestAssured.given();

        // Make a request to the server by specifying the method Type and the method URL.
        // This will return the Response from the server. Store the response in a variable.
        Response response = httpRequest.request(Method.GET, Constants.User_URI);

        //Assert the status code
        Assert.assertEquals(Constants.GET_SUCCESS_STATUS_CODE, response.getStatusCode());

        // Now let us print the body of the message to see response
        System.out.println("Response Body: " + response.getBody().asString());
    }

    //Test to get details of single user and validate the status code the response
    @Test
    public void testGetListOfSingleUser(){
        RestAssured.baseURI = Constants.Base_URL;

       /*  Get the RequestSpecification of the request that you want to sent
           to the server. The server is specified by the BaseURI that we have specified in the above step.*/
        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET, Constants.Single_User_URI);

        System.out.println("Response: "+response.getStatusCode());

        //Assert the status code
        Assert.assertEquals(Constants.GET_SUCCESS_STATUS_CODE, response.getStatusCode());

        // Now let us print the body of the message to see what response we have received from the server
        System.out.println("Response Body: " + response.getBody().asString());
    }

    //Test to retrieve the user doesn't exists and validate the status code and response
    @Test
    public void testSingleUserNotFound(){
        RestAssured.baseURI = Constants.Base_URL;

        /*Get the RequestSpecification of the request that you want to sent
        to the server. The server is specified by the BaseURI that we have specified in the above step.*/
        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET, Constants.User_NOTFOUND);

        //Assert the status code
        Assert.assertEquals(Constants.NotFound_STATUS_CODE, response.getStatusCode());

        // Print the body of the message to see what response we have received from the server
        System.out.println("Response body: "+response.getBody().asPrettyString());
    }

    //test to retrieve list of all resources data
    @Test
    public void testListOfResources(){
        RestAssured.baseURI = Constants.Base_URL;

        Response response = APICallsObj.getRequest(Constants.List_Of_Resources, Constants.GET_SUCCESS_STATUS_CODE);

        // Print the body of the message to see what response we have received from the server
        System.out.println("Response body: "+response.getBody().asPrettyString());
    }

    // Test to get data of single resource
    @Test
    public void testSingleResource(){
        RestAssured.baseURI = Constants.Base_URL;

        Response response = APICallsObj.getRequest(Constants.List_Of_Single_Resource, Constants.GET_SUCCESS_STATUS_CODE);

        // Print the body of the message to see what response we have received from the server
        System.out.println("Response body: "+response.getBody().asPrettyString());
    }

    //Test to find single resource is not found
    @Test
    public void testSingleResourcesNotFound(){
        RestAssured.baseURI = Constants.Base_URL;

        Response response = APICallsObj.getRequest(Constants.List_Of_Unknown_Resource, Constants.NotFound_STATUS_CODE);

        // Print the body of the message to see what response we have received from the server
        System.out.println("Response body: "+response.getBody().asPrettyString());
    }

    //Test to create new user using POST
    @Test
    public void testCreateNewUser(){
        RestAssured.baseURI = Constants.Base_URL;

        // Json Body
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","morpheus");
        jsonObject.put("job","zion resident");

        Response response = APICallsObj.postRequest(jsonObject, Constants.Post_Success_Status_Code, Constants.Users_URI);

        System.out.println("Response body: "+response.getBody().asPrettyString());

        //Validate the response body
        Assert.assertEquals("morpheus",response.jsonPath().getString("name"));
        Assert.assertEquals("zion resident",response.jsonPath().getString("job"));
    }

    //Test to update the user information using PUT
    @Test
    public void testUpdateNewUser(){
        RestAssured.baseURI = Constants.Base_URL;

        // Json Body
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","morpheus");
        jsonObject.put("job","zion resident");

        Response response = APICallsObj.putRequest(jsonObject, Constants.GET_SUCCESS_STATUS_CODE, Constants.Single_User_URI);

        System.out.println("Response body: "+response.getBody().asPrettyString());

        //Validate the response body
        Assert.assertEquals("morpheus",response.jsonPath().getString("name"));
        Assert.assertEquals("zion resident",response.jsonPath().getString("job"));
    }

    //Test patch data to user using PATCH and validate the status code
    @Test
    public void testPatchDataToUser(){
        RestAssured.baseURI = Constants.Base_URL;
        // Json Body
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("job","zion resident");

        Response response = APICallsObj.patchRequest(jsonObject, Constants.GET_SUCCESS_STATUS_CODE, Constants.Single_User_URI);

        System.out.println("Response body: "+response.getBody().asPrettyString());

        //Validate the response body
        Assert.assertEquals("zion resident",response.jsonPath().getString("job"));
    }

    //Test to Delete an existing user and validate the status code
    @Test
    public void testDeleteUser(){
        RestAssured.baseURI = Constants.Base_URL;

        Response response = APICallsObj.deleteRequest(Constants.Delete_Status_Code, Constants.Single_User_URI);

        System.out.println(response.getStatusCode());
    }

    //Test to register new users successful and validate the status code
    @Test
    public void testRegisterNewUserSuccessful(){
        RestAssured.baseURI = Constants.Base_URL;

        /*Get the RequestSpecification of the request that you want to sent
        to the server. The server is specified by the BaseURI that we have specified in the above step.*/


        /*
          Json Body
         */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email","eve.holt@reqres.in");
        jsonObject.put("password","pistol");

     /*   Response response = httpRequest
                .header("Content-type", "application/json")
                .and()
                .body(jsonObject.toJSONString())
                .when()
                .post("/api/register")
                .then()
                .assertThat()
                .statusCode(com.reqres.util.Constants.GET_SUCCESS_STATUS_CODE)
                .extract().response();*/

        Response response = APICallsObj.postRequest(jsonObject, Constants.GET_SUCCESS_STATUS_CODE, Constants.Registration_URI);

        System.out.println("Response body: "+response.getBody().asPrettyString());

        // Validate the id and token from response
        Assert.assertEquals("4",response.jsonPath().getString("id"));
    }

    //Test to register new user successful and validate the status code
    @Test
    public void testRegisterNewUserUnSuccessful(){
        RestAssured.baseURI = Constants.Base_URL;

        // Json Body
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email","sydney@fife");

        //Call Post method in Util and get response as return object
        Response response = APICallsObj.postRequest(jsonObject, Constants.ERROR_STATUS_CODE, Constants.Registration_URI);

        System.out.println("Response body: "+response.getBody().asPrettyString());

        //Assert message 'Missing Password'
        Assert.assertEquals(Constants.Missing_Password_Error_Message,response.jsonPath().getString("error"));
    }

    //Test to login functionality with valid credentials and validate the response code
    @Test
    public void testLoginUserSuccessful(){
        RestAssured.baseURI = Constants.Base_URL;

        //JSon body
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email","eve.holt@reqres.in");
        jsonObject.put("password","cityslicka");

        Response response = APICallsObj.postRequest(jsonObject, Constants.GET_SUCCESS_STATUS_CODE, Constants.Login_URI);

        System.out.println("Response body: "+response.getBody().asPrettyString());

        //Validate the response token - Assert the error message
        Assert.assertEquals(Constants.Token_Message,response.jsonPath().getString("token"));
    }

    // Test to try login without providing Password and validate the response status code
    @Test
        public void testLoginUserUnSuccessful(){
        RestAssured.baseURI = Constants.Base_URL;

        //Json Body to login
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email","peter@klaven");

        Response response = APICallsObj.postRequest(jsonObject, Constants.ERROR_STATUS_CODE, Constants.Login_URI);

        System.out.println("Response body: "+response.getBody().asPrettyString());

        //Assert the error message
        Assert.assertEquals(Constants.Missing_Password_Error_Message,response.jsonPath().getString("error"));
    }

    //Test the Delayed Response result of the API, I am validating the response status code and the response data in json formate
    @Test
    public void testGetDelayedResponse(){
        RestAssured.baseURI = Constants.Base_URL;

        Response response = APICallsObj.getRequest(Constants.Delay_Response_URI, Constants.GET_SUCCESS_STATUS_CODE);
        System.out.println("Response Body:  " + response.getBody().asPrettyString());

        //Validate the response
        //Assert.assertEquals(12,response.jsonPath().getString("total"));
    }
}
