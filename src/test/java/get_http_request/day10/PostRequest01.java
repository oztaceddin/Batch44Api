package get_http_request.day10;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends HerOkuAppBaseUrl {

    //https://restful-booker.herokuapp.com/booking
    //   { "firstname": "Ali",
    //              "lastname": "Can",
    //              "totalprice": 500,
    //              "depositpaid": true,
    //              "bookingdates": {
    //                  "checkin": "2022-03-01",
    //                  "checkout": "2022-03-11"
    //               }
    //}
    //gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
    //}
    //   "booking": {
    //       "firstname": "Ali",
    //       "lastname": "Can",
    //       "totalprice": 500,
    //       "depositpaid": true,
    //       "bookingdates": {
    //                           "checkin": "2022-03-01",
    //                            "checkout": "2022-03-11"
    //       }
    //   }
    //}
    //olduğunu test edin
    //    */

    //  "username" : "admin",
    //    "password" : "password123"

    @Test
    public void test01(){

        //1) URL OLUSTUR
        spec05.pathParam("parametre1", "booking");

        //2) EXPECTED DATA
        HerOkuAppTestData testData = new HerOkuAppTestData();
        JSONObject expectedRequestData = testData.setUpTestAndRequestData();
        System.out.println(expectedRequestData);

        //3) REQUEST VE RESPONSE

        Response response = given()
                .contentType("application/json; charset=utf-8")
                .auth()
                .basic("admin","password123")
                .spec(spec05)
                .body(expectedRequestData.toString())
                .when()
                .post("/{parametre1}");

        response.prettyPrint();

        // JSONObject'te toString() kullanmalıyız => body(expectedRequestData.toString())



        //4--->dogrulama

        //a----jsonpath

        JsonPath jsonPath=response.jsonPath();

        Assert.assertEquals(200,response.statusCode());
        response.then().assertThat().statusCode(200);

        // Expected = Java formatındaki oluşturduğumuz data
        //Actual Data = APİ den Json formatında gelen data  (edited)


        Assert.assertEquals(expectedRequestData.getString("firstname"),jsonPath.getString("booking.firstname"));
        Assert.assertEquals(expectedRequestData.getString("lastname"),jsonPath.getString("booking.lastname"));
        Assert.assertEquals(expectedRequestData.getInt("totalprice"),jsonPath.getInt("booking.totalprice"));
        Assert.assertEquals(expectedRequestData.getBoolean("depositpaid"),jsonPath.getBoolean("booking.depositpaid"));

        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin"),
                jsonPath.getString("booking.bookingdates.checkin"));

        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").get("checkout"),
                jsonPath.getString("booking.bookingdates.checkout"));














    }



    }





