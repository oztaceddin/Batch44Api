package get_http_request.day11;

import base_url.DummybaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class PostRequest02 extends DummybaseUrl {

    ///*
    //http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
    //{
    //    "name":"Ali Can",
    //    "salary":"2000",
    //    "age":"40",
    //}
    //gönderildiğinde,Status kodun 200 olduğunu ve dönen response body nin,
    //
    //{
    //    "status": "success",
    //    "data": {
    //    "id":…
    //},
    //    "message": "Successfully! Record has been added."
    //}
    //
    //olduğunu test edin
    // */

    @Test
    public void test02() {

        //1--url oluştur
        ////http://dummy.restapiexample.com/api/v1/create

        spec02.pathParams("bir", "api", "iki", "v1", "uc", "create");

        //2--expected data

        DummyTestData obj = new DummyTestData();


        //requestData için---bu gonderdigimiz datalar icin
        HashMap<String, Object> requestBodyMap = obj.setUpRequestBody();

        //expectedDta  İçin---bu gonderdigimiz datalardan dönen cevap icin
        HashMap<String, Object> expectedDataMap = obj.setUpExpectedData();

        System.out.println("expectedDataMap = " + expectedDataMap);


        //3--Request ve response

        Response response = given().accept(ContentType.JSON).
                spec(spec02).
                body(requestBodyMap).
                when().
                post("/{bir}/{iki}/{uc}");
        //post işleminde MAP kullandıgımızda body içinde tostringe gerek yok--sadece Jsonobject ihtiyaç var

        response.prettyPrint();

        //4--dugrulama

        //a--de serialization

        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);
        //    "status": "success",
        //    "data": {
        //    "id":…
        //},

        assertEquals(expectedDataMap.get("statusCode"), response.statusCode());
        assertEquals(expectedDataMap.get("message"), actualDataMap.get("message"));
        assertEquals(expectedDataMap.get("status"), actualDataMap.get("status"));


        //Jsonpat ile

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(expectedDataMap.get("statusCode"), response.statusCode());
        Assert.assertEquals(expectedDataMap.get("message"), jsonPath.getString("message"));
        Assert.assertEquals(expectedDataMap.get("status"), jsonPath.getString("status"));


        //Matcher ile

        response.then().assertThat().statusCode(200).
                body("message", equalTo(expectedDataMap.get("message")),
                        "status", equalTo(expectedDataMap.get("status")));


    }
}
