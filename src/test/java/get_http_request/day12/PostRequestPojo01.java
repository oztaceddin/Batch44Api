package get_http_request.day12;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequestPojo01 extends JsonPlaceHolderBaseUrl {

        //   /*
    //   https://jsonplaceholder.typicode.com/todos url 'ine bir request gönderildiğinde
    //Request body{
    //"userId": 21,
    //"id": 201,
    //"title": "Tidy your room",
    //"completed": false
    //}
    //Status kodun 201, response body 'nin ise
    //
    //{
    //"userId": 21,
    //"id": 201,
    //"title": "Tidy your room",
    //"completed": false
    //}
    //*/

    @Test
    public void test01(){

        //1--url oluştur

        spec04.pathParam("bir","todos");


        //2 expected data oluştur

        JsonPlaceHolderPojo expectedData=new JsonPlaceHolderPojo(21,201,"Tidy your room",false);

        System.out.println("expectedData = " + expectedData);

        // request ve response

        Response response=given().
                spec(spec04).
                contentType(ContentType.JSON).
                body(expectedData).
                when().post("/{bir}");

        response.prettyPrint();


        //4 dogrulama

        //Status kodun 201, response body 'nin ise
        //
        //{
        //"userId": 21,
        //"id": 201,
        //"title": "Tidy your room",
        //"completed": false

        JsonPlaceHolderPojo actualData=response.as(JsonPlaceHolderPojo.class);


        Assert.assertEquals(201,response.getStatusCode());

        Assert.assertEquals(expectedData.getId(),actualData.getId());
        Assert.assertEquals(expectedData.getTitle(),actualData.getTitle());
        Assert.assertEquals(expectedData.getUserId(),actualData.getUserId());
        Assert.assertEquals(expectedData.isCompleted(),actualData.isCompleted());








    }

}
