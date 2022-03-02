package get_http_request;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest03 {

    @Test
    public void test01(){

        String url="https://restful-booker.herokuapp.com/booking/7";

     Response response=  given().when().get(url);

     response.prettyPrint();
     response.prettyPeek();

     /*
    https://restful-booker.herokuapp.com/booking/7 url'ine
    GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    ve content type'inin "application/json"
    "firstname": "Mary",
    "lastname": "Jones",
    "checkin": "2015-06-02",
        "checkout": "2018-09-29" oldugunu test edin
     */

        //headers
        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json; charset=utf-8").
                statusLine("HTTP/1.1 200 OK");


        //body
        response.then().assertThat().body("firstname",equalTo("Mary"),
                "lastname",equalTo("Jones"),
                "bookingdates.checkin",equalTo("2015-06-02"),
                "bookingdates.checkout",equalTo("2018-09-29"));





    }
}
