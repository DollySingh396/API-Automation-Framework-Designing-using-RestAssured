package api.test;

import api.endpoints.userEndPoints;
import api.payload.userPojo;
import api.utilities.dataProviders;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import lombok.Data;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataDrivenTests {



    // data is method name & since the method is present in different class so we need to mention the class as well
    @Test(priority =  1, dataProvider= "UserData", dataProviderClass = dataProviders.class)
    void testCreateUser(String userId, String userName, String firstName, String lastName, String userEmail, String password, String phone){

        userPojo userPayload = new userPojo();
        userPayload.setId(Integer.parseInt(userId));
        userPayload.setUsername(userName);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(userEmail);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);



        Response response = userEndPoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);


    }

    @Test(priority = 4, dataProvider = "UserNames", dataProviderClass = dataProviders.class)
    void testDeleteUser(String userName){

        userPojo userPayload = new userPojo();
        userPayload.setUsername(userName);

        Response response = userEndPoints.deleteUser(userPayload.getUsername());
        response.then().statusCode(200);
        Assert.assertEquals(response.getStatusCode(), 200);
    }


    @Test( priority = 2, dataProvider = "UserNames", dataProviderClass = dataProviders.class)
    void testGetUser(String userName){

        userPojo userPayload = new userPojo();

        userPayload.setUsername(userName);
        Response response = userEndPoints.getUser(userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("User details are :" +response.getBody().asString());

    }

    @Test(priority = 3, dataProvider = "UserData", dataProviderClass = dataProviders.class)
    void testUpdateUser(String userId, String userName, String firstName, String lastName, String userEmail, String password, String phone){

        userPojo userPayload = new userPojo();

        userPayload.setFirstName("Dolly");
        userPayload.setLastName("Singh");
        userPayload.setUsername("DS");
        userPayload.setId(Integer.parseInt(userId));
        userPayload.setEmail(userEmail);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);

        Response response = userEndPoints.updateUser(userPayload.getUsername(), userPayload);
        Assert.assertEquals(response.getStatusCode(), 200);

        Response res = userEndPoints.getUser(userPayload.getUsername());
        Assert.assertEquals(res.getStatusCode(), 200);
        System.out.println("User details are :" +res.getBody().asString());


    }

}
