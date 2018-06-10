package jiraAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;


import java.util.Properties;

public class Helper {
    Properties prop = new Properties();

    public static XmlPath rawToXml(Response res) {
        String respon = res.asString();
        System.out.println(respon);

        XmlPath x = new XmlPath(respon);
        return x;

    }

    public static JsonPath rawToJson(Response res) {
        String respon = res.asString();
        JsonPath x = new JsonPath(respon);
        return x;
    }


    public static String createJiraSession(String sessionid) {
        //create  session
        RestAssured.baseURI = "http://localhost:8080/";
        Response res = RestAssured.given().header("content-Type", "application/json")
                .body(payLoad.authentication())
                .when()
                .post(Resources.jiraResource())
                .then()
               // .assertThat()
                //.statusCode(200)
                .extract()
                .response();

        JsonPath jp = Helper.rawToJson(res);
        String sessionID = jp.get("session.value");
        System.out.println(sessionID);
        return sessionID;
    }

    public static String createJiraDefect() {

        Response res = RestAssured.given()
                .header("content-Type", "application/json")
                .header("Cookie", "JSESSIONID=" + Helper.createJiraSession(RestAssured.sessionId))
                .body(payLoad.createBug())
                .when()
                .post(Resources.createDefectResource())
                .then()
                .assertThat()
                .statusCode(201)
                .extract().response();
        JsonPath js = Helper.rawToJson(res);
        String defectid = js.get("id");
        System.out.println("Defect ID is : " + defectid);
        return defectid;
    }

    public static String addComment(){
        Response res = RestAssured.given()
                .header("Content-Type","application/json")
                .header("Cookie","JSESSIONID=" + Helper.createJiraSession(RestAssured.sessionId))
                .body(payLoad.addComment())
                .when()
                .post(Resources.addComment())
                .then()
                .assertThat()
                .statusCode(201)
                .extract().response();
        JsonPath js = Helper.rawToJson(res);
        String commentid = js.get("id");
        System.out.println("commentid : " + commentid);
        return commentid;
    }
}
