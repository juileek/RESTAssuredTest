package TestFramework;

import jiraAPI.Helper;
import jiraAPI.Resources;
import jiraAPI.payLoad;
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
import static io.restassured.RestAssured.sessionId;

public class basicJIRA {
    private static Logger log = LogManager.getLogger(basicJIRA.class.getName());
    Properties prop = new Properties();
    @BeforeTest
    public void setup() throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//JIRA.properties");
        prop.load(fis);
    }

    @Test
    public void deleteBug(){
        given()
                .header("Cookie","JSESSIONID=" + Helper.createJiraSession(sessionId))
                .when()
                .delete(Resources.deleteDefect()+ Helper.createJiraDefect());

    }

    @Test
    public void updateComment(){
        Response res =  given().log().all()
                //.header("Content-Type","application/json")
                //.header("Cookie","JSESSIONID=" + Helper.createJiraSession())
                .pathParam("commentid",Helper.addComment())
                .body(payLoad.updateComment())
                .when()
                .put(Resources.addComment()+"/" + "{commentid}")
                .then()
                .log()
                .all()
                .assertThat()
                .extract().response();
        JsonPath js = Helper.rawToJson(res);
//        String bodycomment = js.get("body");
               // String resp = js.get("body");
        System.out.println(js);


    }
}
