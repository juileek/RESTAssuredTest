package TestFramework;


import jiraAPI.Helper;
import googleAPI.Resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class basicPostXmlGoogle {
    Properties prop = new Properties();
    @BeforeTest
    public void setup() throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//basicPostXML.properties");
        prop.load(fis);

    }
    @Test
    public void Test2() throws IOException {
       String postData =GenerateStringFromResource("src\\test\\java\\TestFramework\\addplace.xml");
        RestAssured.baseURI = prop.getProperty("HOST");
        io.restassured.response.Response res = given().queryParam("key",prop.getProperty("KEY")).
                body(postData).when().post(Resources.addPlaceXMl()).then().assertThat().contentType(ContentType.XML).and().statusCode(200)
                .extract().response();//.and()
                //.body("status",equalTo("OK"));

      // String status =  );
        XmlPath x = Helper.rawToXml(res);
        System.out.println((String) x.get("PlaceAddResponse.place_id"));


    }
    public static String GenerateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

}
