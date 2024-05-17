package api.endpoints;

import api.payload.userPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class userEndPointsViaRoutesPropertiesFile {




// method created for getting URLs from properties file
    static ResourceBundle getURL(){

        //Load the properties file from src/test/resources folder, only specify the properties file name
        ResourceBundle routes = ResourceBundle.getBundle("routes");
        return routes;
    }

    //Perform CRUD operations on USER API
    public static Response createUser(userPojo payload) {

        //get post url from routes object that has properties file data
       String postURL = getURL().getString("post_url");

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .body(payload)
                        .when()
                        .post(postURL);

        return response;

    }


    public static Response getUser(String userName) {

        String getURL = getURL().getString("get_url");
        Response response =
                given()
                        .pathParam("username", userName)
                        .when()
                        .get(getURL);
        return response;
    }


    public static Response updateUser(String userName, userPojo payload) {

        String updateURL = getURL().getString("update_url");

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .pathParam("username", userName)
                        .body(payload)
                        .when()
                        .put(updateURL);

        return response;

    }


    public static Response deleteUser(String userName) {

        String deleteURL = getURL().getString("delete_url");

        Response response =
                given()
                        .pathParam("username", userName)
                        .when()
                        .delete(deleteURL);

        return response;
    }

}