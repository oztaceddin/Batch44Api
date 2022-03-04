package get_http_request;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest04 {

    @Test
    public void test04() {

        /*
        http://dummy.restapiexample.com/api/v1/employees  url'ine
        GET request'i yolladigimda gelen response'un
        status kodunun 200 ve content type'inin "application/json"
        ve employees sayisinin 24
        ve employee'lerden birinin "Ashton Cox"
        ve gelen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin.
        */

        String url = "http://dummy.restapiexample.com/api/v1/employees";

        Response response = given().when().get(url);

        response.prettyPrint();
        // response.prettyPeek();

        //headrs
        response.
                then().
                statusCode(200).
                contentType("application/json");

        //response.then().contentType(ContentType.JSON).statusCode(200);


        //body
        response.
                then().
                assertThat().
                body("data", Matchers.hasSize(24),//data içinde kaçtane oldugunu arattırdık
                        "data.employee_name", Matchers.hasItem("Ashton Cox"),
                        "data.employee_age", Matchers.hasItems(21, 61, 23));

        //tek aratma yapmak istedigimizde---hasitem

        //çoklu aratma yaptıgımızda ---hasitems


    }
}