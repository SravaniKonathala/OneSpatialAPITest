package com.onespatial.reqrestest.util;

public interface Constants {

    // API URL
    public static final String Base_URL = "https://reqres.in/";

    //API URI
    public static final String Registration_URI = "/api/register";
    public static final String Login_URI = "/api/login";
    public static final String User_URI = "/api/users?page=2";
    public static final String Users_URI = "/api/users";
    public static final String Single_User_URI = "/api/users/2";
    public static final String User_NOTFOUND = "/api/users/23";
    public static final String Delay_Response_URI = "/api/users?delay=3";
    public static final String List_Of_Resources = "/api/unknown";
    public static final String List_Of_Single_Resource = "/api/unknown";
    public static final String List_Of_Unknown_Resource = "/api/unknown/23";

    //Error message and Token code
    public static final String Missing_Password_Error_Message =  "Missing password";
    public static final String Token_Message = "QpwL5tke4Pnpja7X4";

    // Status Codes
    public static final int GET_SUCCESS_STATUS_CODE = 200;
    public static final int Post_Success_Status_Code = 201;
    public static final int ERROR_STATUS_CODE = 400;
    public static final int NotFound_STATUS_CODE = 404;
    public static final int Delete_Status_Code = 204;
}
