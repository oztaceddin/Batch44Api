package get_http_request.day12;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PutRequest0101 extends JsonPlaceHolderBaseUrl {


       ///*
    //https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönerdiğimde
    //
    //{
    //"userId": 21,
    //"title": "Wash the dishes",
    //"completed": false
    //}
    //Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
    //{
    //"userId": 21,
    //"title": "Wash the dishes",
    //"completed": false,
    //"id": 198
    //}
    //*/


    @Test
    public void test01(){

        //https://jsonplaceholder.typicode.com/todos/198

        //1--url oluştur

        spec04.pathParams("bir","todos","iki",198);

        //2-expected oluş

        JsonPlaceHolderTestData obj =new JsonPlaceHolderTestData();

        JSONObject expectedData=obj.setUpPutData();///request

        System.out.println("expectedData = " + expectedData);


        //3--request ve response olştur

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec04).
                body(expectedData.toString()).
                when().
                put("/{bir}/{iki}");

        response.prettyPrint();

        //4---dogrulama

        //Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
        //{
        //"userId": 21,
        //"title": "Wash the dishes",
        //"completed": false,
        //"id": 198
        //}

        //matchers

        response.then().assertThat().statusCode(200).
                body("userId", equalTo(expectedData.get("userId")),
                        "title",equalTo(expectedData.get("title")),
                        "completed",equalTo(expectedData.get("completed")));


        //jsonpath

        JsonPath jsonPath=response.jsonPath();


        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(expectedData.get("userId"),jsonPath.get("userId"));
        Assert.assertEquals(expectedData.get("title"),jsonPath.get("title"));
        Assert.assertEquals(expectedData.get("completed"),jsonPath.get("completed"));


        // De-Serialization
        HashMap<String, Object> actuaalData = response.as(HashMap.class);

        Assert.assertEquals(expectedData.get("completed"), actuaalData.get("completed"));
        Assert.assertEquals(expectedData.get("title"), actuaalData.get("title"));
        Assert.assertEquals(expectedData.get("userId"), actuaalData.get("userId"));








    }
}
