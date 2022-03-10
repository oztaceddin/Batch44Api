package get_http_request.day05;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest06 {

    @Test
    public void test01() {

        /*
        https://restful-booker.herokuapp.com/booking/5 url’ine
        accept type’i “application/json” olan GET request’i yolladigimda
        gelen response’un
        status kodunun 200
        ve content type’inin “application/json”
        ve "firstname": "Susan"
        ve "totalprice": 958,
        ve "checkin": "2018-12-06",oldugunu test edin
         */


        String url = "https://restful-booker.herokuapp.com/booking/5";

        Response response = given().when().get(url);
        response.prettyPrint();

        // Response response = given().accept("application/json").when().get(url);
        //hata durumunda accept yapılarak düzeltile bilir

        //headers
        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json");

        //body
        response.
                then().
                assertThat().
                body("firstname", Matchers.equalTo("Susan"),
                        "totalprice", Matchers.equalTo(958),
                        "bookingdates.checkin", Matchers.equalTo("2018-12-06"));


    }
}
