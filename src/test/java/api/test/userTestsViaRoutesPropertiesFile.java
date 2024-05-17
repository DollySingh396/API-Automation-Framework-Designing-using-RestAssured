package api.test;

import api.endpoints.userEndPointsViaRoutesPropertiesFile;
import api.payload.userPojo;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class userTestsViaRoutesPropertiesFile {

    Faker faker;
    userPojo userPayload;

    // creating Logger class object
    public Logger logger;

    @BeforeClass
    public void setup(){

        faker = new Faker();
        userPayload = new userPojo();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        userPayload.setPassword(faker.internet().password(5, 10));

        //logs get logs of current execution
        logger = LogManager.getLogger(this.getClass());

    }

    @Test(priority =  1)
    void testPostUserAPI(){

        logger.info("************* POST USER API ****************");
       Response response = userEndPointsViaRoutesPropertiesFile.createUser(userPayload);
       response.then().log().all();

       Assert.assertEquals(response.getStatusCode(), 200);



    }

    @Test(priority = 2)
    void testGetUserAPI(){

        Response response = userEndPointsViaRoutesPropertiesFile.getUser(this.userPayload.getUsername());
        response
                .then()
                .log().all();

        Assert.assertEquals(response.getStatusCode(), 200);


    }

    @Test(priority = 3)
    void testUpdateUserAPI(){

        //update data
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = userEndPointsViaRoutesPropertiesFile.updateUser(this.userPayload.getUsername(), userPayload);

        //rest assured assertion
        response.then().log().body().statusCode(200);

        //testng assertion
        Assert.assertEquals(response.getStatusCode(), 200);

        //get response after update
        Response responsePostUpdate = userEndPointsViaRoutesPropertiesFile.getUser(this.userPayload.getUsername());
        Assert.assertEquals(responsePostUpdate.getStatusCode(), 200);

    }

    @Test(priority = 4)
    void testDeleteUser(){

        Response response = userEndPointsViaRoutesPropertiesFile.deleteUser(this.userPayload.getUsername());
        response.then().statusCode(200);
        Assert.assertEquals(response.getStatusCode(), 200);


    }


}
