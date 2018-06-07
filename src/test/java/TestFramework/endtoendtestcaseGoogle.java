package TestFramework;

import googleAPI.Resources;
import googleAPI.payLoad;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class endtoendtestcaseGoogle {
    private static Logger log = LogManager.getLogger(endtoendtestcaseGoogle.class.getName());
    Properties prop = new Properties();
    @BeforeTest
    public void setup() throws IOException {

        FileInputStream fis =  new FileInputStream(System.getProperty("user.dir") + "//Env.properties");
        prop.load(fis);

    }
    @Test
    public void addandDeletePlace(){


        //Task 1 grab the response
        RestAssured.baseURI = prop.getProperty("HOST");
        Response res = given().queryParam("key",prop.getProperty("KEY")).
                body(payLoad.getPostData()).when().post(Resources.placePostData()).then().assertThat().contentType(ContentType.JSON).and().statusCode(200).and()
                .body("status",equalTo("OK")).and()
                .extract().response();
        String responseString = res.asString();
        System.out.println(responseString);

        //Task grab placeid from response
        JsonPath js =   new JsonPath(responseString);
        String placeid = js.get("place_id");
        log.info("AddPlaceid : " + placeid);
        System.out.println(placeid);

        //Task 3 place this placeid in delete request

         given().queryParam("key",prop.getProperty("KEY"))
                .body("{\"place_id\": \"" + placeid+"\""+"}")
                .when()
                .post(Resources.placeDeleteData())
                .then()
                .assertThat().statusCode(200)
                .body("status",equalTo("OK"));








    }
}
