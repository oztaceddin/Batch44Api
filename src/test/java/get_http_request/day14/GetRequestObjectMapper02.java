package get_http_request.day14;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.JsonUtil;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequestObjectMapper02  extends HerOkuAppBaseUrl {

    //https://restful-booker.herokuapp.com/booking/2 url’ine bir get request gönderildiğinde,
    // status kodun 200 ve response body’nin
//    {
//    "firstname": "Ali",
//    "lastname": "Can",
//    "totalprice": 500,
//    "depositpaid": true,
//    "bookingdates": {
//                      "checkin": "2022-03-01",
//                       "checkout": "2022-03-11"
//                        },
//    "additionalneeds": "Breakfast"
//                        }
    //Olduğunu Object Mapper kullanarak test edin


    @Test
    public void test02(){

        //1---url olştur

        spec05.pathParams("1","booking","2",13);

        //endpointedi son rakam sitede sürekli degiştiginden farklı yazıyoruz önce postmende post oluşturuyoruz

        //expected oluştur
        
        String jsonData="{\n" +
                "    \"firstname\": \"Ali\",\n" +
                "    \"lastname\": \"Can\",\n" +
                "    \"totalprice\": 500,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "                      \"checkin\": \"2022-03-01\",\n" +
                "                       \"checkout\": \"2022-03-11\"\n" +
                "                        },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "                        }";

        System.out.println("jsonData = " + jsonData);

        HashMap<String,Object>expected=   JsonUtil.convertJsonToJava(jsonData, HashMap.class);

        System.out.println("expected = " + expected);


        //3--request ve response

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec05).
                when().
                get("/{1}/{2}");

        response.prettyPrint();


        //4 dogrulama

        //de serializetion

        Map<String,Object> actual = JsonUtil.convertJsonToJava(response.asString(),Map.class);
        System.out.println("actual = " + actual);

        Assert.assertEquals(expected.get("firstname"),actual.get("firstname"));
        Assert.assertEquals(expected.get("lastname"),actual.get("lastname"));
        Assert.assertEquals(expected.get("totalprice"),actual.get("totalprice"));
        Assert.assertEquals(expected.get("depositpaid"),actual.get("depositpaid"));
        Assert.assertEquals(expected.get("bookingdates.checkin"),actual.get("bookingdates.checkin"));
        Assert.assertEquals(expected.get("bookingdates.checkout"),actual.get("bookingdates.checkout"));
        Assert.assertEquals(expected.get("additionalneeds"),actual.get("additionalneeds"));



        //Matcher ile
        response.then().
                assertThat().
                statusCode(200).
                body("firstname",equalTo(expected.get("firstname"))
                        ,"lastname",equalTo(expected.get("lastname"))
                        ,"totalprice",equalTo(expected.get("totalprice"))
                        ,"depositpaid",equalTo(expected.get("depositpaid"))

                        ,"bookingdates.checkin",equalTo(((Map)expected.get("bookingdates")).get("checkin"))
                        ,"bookingdates.checkout",equalTo(((Map)expected.get("bookingdates")).get("checkout")),

                        "additionalneeds",equalTo(expected.get("additionalneeds")));




        
        
        








    }
}
