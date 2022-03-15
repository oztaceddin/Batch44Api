package get_http_request.day14;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.JsonUtil;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequestObjectMapper01 extends JsonPlaceHolderBaseUrl {


    //https://jsonplaceholder.typicode.com/todos/198 url’ine bir get request gönderildiğinde,
    //Dönen response ’un status kodunun 200 ve body kısmının
//     {
//     "userId": 10,
//     "id": 198,
//     "title": "quis eius est sint explicabo",
//     "completed": true
//     }
    //Olduğunu Object Mapper kullanarak test edin





    @Test
    public void test01(){

        //1---url oluştur

        spec04.pathParams("1","todos","2","198");

        //expecteddata oluştur

        String jsonData=" {\n" +
                "     \"userId\": 10,\n" +
                "     \"id\": 198,\n" +
                "     \"title\": \"quis eius est sint explicabo\",\n" +
                "     \"completed\": true\n" +
                "     }";

        System.err.println("jsonData = " + jsonData);//err kırmızı yaparrr



      Map<String,Object>expectedData=  JsonUtil.convertJsonToJava(jsonData, Map.class);

        System.out.println("expectedData = " + expectedData);


        //3--response ve request olştr

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec04).
                when().
                get("/{1}/{2}");

        response.prettyPrint();


        //4--dogrulama

        //önceki derste ögrendigimiz yöntem
      //  Map<String,Object>actualData=response.as(Map.class);


        //     "userId": 10,
//     "id": 198,
//     "title": "quis eius est sint explicabo",
//     "completed": true
//     }



        Map<String,Object>actualData=JsonUtil.convertJsonToJava(response.asString(),Map.class);

        System.out.println("actualData = " + actualData);

        Assert.assertEquals(200,response.getStatusCode());

        Assert.assertEquals(expectedData.get("userId"),expectedData.get("userId"));
        Assert.assertEquals(expectedData.get("id"),expectedData.get("id"));
        Assert.assertEquals(expectedData.get("title"),expectedData.get("title"));
        Assert.assertEquals(expectedData.get("completed"),expectedData.get("completed"));


        Assert.assertEquals(expectedData,actualData);



        //matcher

        response.then().assertThat().statusCode(200).body("userId",equalTo(expectedData.get("userId")),
                "id",equalTo(expectedData.get("id")),
                "title",equalTo(expectedData.get("title")),
                "completed",equalTo(expectedData.get("completed")));


        //JsonPath ile
        JsonPath json=response.jsonPath();
        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedData.get("userId"),json.getInt("userId"));
        Assert.assertEquals(expectedData.get("id"),json.getInt("id"));
        Assert.assertEquals(expectedData.get("title"),json.getString("title"));
        Assert.assertEquals(expectedData.get("completed"),json.getBoolean("completed"));








        












    }

}
