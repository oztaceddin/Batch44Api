package get_http_request.day07;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.Request;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest16 extends JsonPlaceHolderBaseUrl {

        /*
   https://jsonplaceholder.typicode.com/todos/7

   {
   "userId": 1,
   "id": 7,
   "title": "illo expedita consequatur quia in",
   "completed": false
}
    */

    @Test
    public void test16(){

        //1)url oluşturma

        spec04.pathParams("bir","todos","iki","7");

        //2) expectes(beklenn) data oluştur

        Map<String,Object> expectedData =new HashMap<>();

        expectedData.put("userId",1);
        expectedData.put("id",7);
        expectedData.put("title","illo expedita consequatur quia in");
        expectedData.put("completed",false);

        System.out.println("expectedData degerleri :"+expectedData);
        //expectedData degerleri :{id=7, completed=false, title=illo expedita consequatur quia in, userId=1}

        //3) request ve response

        Response response =given().spec(spec04).when().get("/{bir}/{iki}");

        ///{bir}/{iki}---->/todos/7

        response.prettyPrint();

        //datayi Json dan>>Java(map)'a donusturme isi= de-serialization
        //datayi Java dan>>Json'a donusturme isi= serialization
        //expected ve actual datalarin ayni formatta karsilastirilmasi icin yapilan donusturme islemleri

        Map<String ,Object> actualData=response.as(HashMap.class);//--->de serialization---

        System.out.println("actualData bilgileri"+actualData);
        //actualData bilgileri{id=7, completed=false, title=illo expedita consequatur quia in, userId=1}

        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedData.get("id"),actualData.get("id"));
        Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));


    }
}
