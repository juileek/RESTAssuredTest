package TestFramework;


import googleAPI.Helper;
import googleAPI.Resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.Logger ;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class basicJSONextractGoogle {
    private static Logger log = LogManager.getLogger(basicJSONextractGoogle.class.getName());
    Properties prop = new Properties();
    @BeforeTest
    public void setup() throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//basic.properties");
        prop.load(fis);
    }
    @Test
    public void Test(){
        //baseurl
        RestAssured.baseURI = prop.getProperty("HOST");
        Response res = given().log().ifValidationFails().param("location", prop.getProperty("LOCATION")).
                param("radius", prop.getProperty("RADIUS")).
                param("key", prop.getProperty("KEY")).
                when().
                get(Resources.nearbyPlaces()).
                then().log().ifValidationFails().
                assertThat().statusCode(200).and().
                contentType(ContentType.JSON).and().
                body("results[0].name",equalTo(prop.getProperty("NAME"))).and().
                body("results[0].place_id",equalTo(prop.getProperty("PLACEID"))).and()
                .header("server",equalTo(prop.getProperty("SERVER")))
                .extract().response();

        JsonPath js = Helper.rawToJson(res);
        int count =js.get("results.size()");
        for(int i = 0;i<count;i++){
            String resultname = js.get("results["+i+"].name");
            log.info(resultname);
            System.out.println(resultname);
        }





    }
}
