package get_http_request.day08;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest20 extends JsonPlaceHolderBaseUrl {

         /*
        https://jsonplaceholder.typicode.com/todos/2
        1) Status kodunun 200,
        2) respose body'de,
         "completed": değerinin false
         "title": değerinin "quis ut nam facilis et officia qui"
         "userId" sinin 1 ve
        header değerlerinden
         "via" değerinin "1.1 vegur" ve
         "Server" değerinin "cloudflare" olduğunu test edin…
*/
    @Test
    public void test20(){

        //1-url oluştur

        spec04.pathParams("bir","todos","iki",2);

        //2-expected data oluştur

        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("StatusCode",200);
        expectedData.put("completed",false);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("via","1.1 vegur");
        expectedData.put("userId",1);
        expectedData.put("Server","cloudflare");

        System.out.println("expected datalar"+expectedData);

        //3-response oluştur

        Response response=given().spec(spec04).when().get("/{bir}/{iki}");
      //  response.prettyPrint();
      //  response.prettyPeek();

        //4-dogrulama

        //Matcher ile

       response.then().assertThat().statusCode((Integer) expectedData.get("StatusCode")).
               headers("via",equalTo(expectedData.get("via")),
                       "Server",equalTo(expectedData.get("Server"))).
               body("userId",equalTo(expectedData.get("userId")),
                       "title",equalTo(expectedData.get("title")),
                       "completed",equalTo(expectedData.get("completed")));



//        response.then().headers("via",equalTo("1.1 vegur")
//                        ,"Server",equalTo("cloudflare")).
//                body("completed",equalTo(false)
//                        ,"userId",equalTo(1)
//                        ,"title",equalTo("quis ut nam facilis et officia qui"));


        //--Jsonpath ile

        JsonPath jsonPath=response.jsonPath();

        Assert.assertEquals(expectedData.get("StatusCode"),response.statusCode());
        Assert.assertEquals(expectedData.get("via"),response.getHeader("via"));
        Assert.assertEquals(expectedData.get("Server"),response.getHeader("Server"));
        Assert.assertEquals(expectedData.get("userId"),jsonPath.getInt("userId"));
        Assert.assertEquals(expectedData.get("title"),jsonPath.getString("title"));
        Assert.assertEquals(expectedData.get("completed"),jsonPath.getBoolean("completed"));


        // 3. yol De Serialization ile

        HashMap<String,Object>actualData=response.as(HashMap.class);

        System.out.println("actual data bilgileri :"+actualData);


        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));







    }



}




