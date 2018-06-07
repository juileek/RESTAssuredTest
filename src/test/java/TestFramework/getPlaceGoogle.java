package TestFramework;

import googleAPI.Resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class getPlaceGoogle {
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
        given().param("location", prop.getProperty("LOCATION")).
                param("radius", prop.getProperty("RADIUS")).
                param("key", prop.getProperty("KEY")).
                when().
                get(Resources.nearbyPlaces()).
                then().
                assertThat().statusCode(200).and().
                contentType(ContentType.JSON).and().
                body("results[0].name",equalTo(prop.getProperty("NAME"))).and().
                body("results[0].place_id",equalTo(prop.getProperty("PLACEID"))).and().header("server",equalTo(prop.getProperty("SERVER")));



    }
}
