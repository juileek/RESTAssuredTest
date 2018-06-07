package TestFramework;

import googleAPI.Resources;
import io.restassured.RestAssured;
import org.apache.logging.log4j.*;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import googleAPI.payLoad;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class addPlacePost {
    private static Logger log =LogManager.getLogger(addPlacePost.class.getName());

    Properties prop = new Properties();
    @BeforeTest
    public void setup() throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//basicPost.properties");
        prop.load(fis);

    }
    @Test
    public void Test2(){
        log.info("Executing Test 2 on : " + prop.getProperty("HOST"));
        RestAssured.baseURI = prop.getProperty("HOST");
        given().queryParam("key",prop.getProperty("KEY")).
                body(payLoad.getPostData())
                 .when()
                 .post(Resources.addPlace())
                 .then().assertThat()
                 .contentType(io.restassured.http.ContentType.JSON)
                 .and()
                 .statusCode(200).and()
                 .body("status",equalTo("OK"));
        log.info("place_id");





    }
}
