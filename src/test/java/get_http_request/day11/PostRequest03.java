package get_http_request.day11;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequest03 extends JsonPlaceHolderBaseUrl {

    /*
   https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
    {
    "userId": 55,
    "title": "Tidy your room",
    "completed": false
  }
    Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
  {
    "userId": 55,
    "title": "Tidy your room",
    "completed": false,
    "id": …
   }
*/

    @Test
    public void test03() {

        //1url oluştur

        spec04.pathParam("bir", "todos");

        //2 expected oluştur

        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();

        JSONObject expectedRequestData = obj.setUpPostData();

        System.out.println("expectedData = " + expectedRequestData);

        //3-request ve response

        Response response = given().
                contentType(ContentType.JSON).
                spec(spec04).
                body(expectedRequestData.toString()).
                when().
                post("/{bir}");

        response.prettyPrint();

        //dogrulama

        //Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
        //  {
        //    "userId": 55,
        //    "title": "Tidy your room",
        //    "completed": false,
        //    "id":201
        //   }

        //matchers class ile

        response.then().assertThat().statusCode(201).
                body("userId", equalTo(expectedRequestData.get("userId")),
                        "title", equalTo(expectedRequestData.get("title")),
                        "completed", equalTo(expectedRequestData.get("completed")),
                        "id", equalTo(expectedRequestData.get("id")));


        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(expectedRequestData.get("statusCode"), response.statusCode());

        Assert.assertEquals(expectedRequestData.get("userId"), jsonPath.getInt("userId"));
        Assert.assertEquals(expectedRequestData.get("title"), jsonPath.getString("title"));
        Assert.assertEquals(expectedRequestData.get("completed"), jsonPath.getBoolean("completed"));
        Assert.assertEquals(expectedRequestData.get("id"), jsonPath.getInt("id"));


        //de de serialization

        Map<String, Object> actualMap = response.as(Map.class);

        Assert.assertEquals(expectedRequestData.get("statusCode"),actualMap.get("statusCode"));

        Assert.assertEquals(expectedRequestData.getInt("userId"), actualMap.get("userId"));
        Assert.assertEquals(expectedRequestData.getString("title"), actualMap.get("title"));
        Assert.assertEquals(expectedRequestData.getBoolean("completed"), actualMap.get("completed"));
        Assert.assertEquals(expectedRequestData.getInt("id"), actualMap.get("id"));


    }
}
