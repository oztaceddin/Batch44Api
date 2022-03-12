package get_http_request.day12;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PatchRequest0102 extends JsonPlaceHolderBaseUrl {

       ///*
    //   https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
    //  {
    //
    //     "title": "Batch44"
    //
    //    }
    //Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
    //{
    //"userId": 10,
    //"title": "Batch44"
    //"completed": true,
    //"id": 198
    //}
    //    */




    @Test
    public void test01(){

        //1 url olştur

        spec04.pathParams("bir","todos","iki",198);

        //2-expected oluş

        JsonPlaceHolderTestData obj =new JsonPlaceHolderTestData();

        JSONObject requestata=obj.SetUpPatchRequestData();
        System.out.println("requestata = " + requestata);

        JSONObject expectedData=obj.setUpPatcEcpectedData();

        System.out.println("expectedData = " + expectedData);


        //3--request ve response olştur

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec04).
                body(expectedData.toString()).
                when().
                put("/{bir}/{iki}");

        response.prettyPrint();

        //jsonpath

        JsonPath json = response.jsonPath();
        Assert.assertEquals(expectedData.getBoolean("completed"),json.getBoolean("completed"));
        Assert.assertEquals(expectedData.getString("title"),json.getString("title"));
        Assert.assertEquals(expectedData.getInt("userId"),json.getInt("userId"));
        Assert.assertEquals(expectedData.getInt("id"),json.getInt("id"));


        ////  De-Serialization

        HashMap<String,Object>actualData=response.as(HashMap.class);

        Assert.assertEquals(200,response.getStatusCode());

        Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedData.get("id"),actualData.get("id"));


        //Matcher
        response.then().statusCode(200);
        response.then().body("title",equalTo(expectedData.getString("title"))
                ,"completed",equalTo(expectedData.getBoolean("completed"))
                ,"userId",equalTo(expectedData.getInt("userId"))
                ,"id",equalTo(expectedData.getInt("id")));


    }
}
