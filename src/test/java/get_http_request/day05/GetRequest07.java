package get_http_request.day05;

import base_url.ReqresinBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest07 extends ReqresinBaseUrl {


    @Test
    public void test07() {


        /*
          https://reqres.in/api/users URL request olustur.
          body icerisindeki idsi 5 olan datayi
          1) Matcher CLASS ile
          2) JsonPath ile dogrulayin.
        */

        spec01.pathParams("parametre1", "api", "parametre2", "users");


        Response response = given().spec(spec01).when().get("/{parametre1}/{parametre2}");

        ///{parametre1}/{parametre2--->/api/users  aynısı

    //    response.prettyPrint();


        //Matchers class ile
        response.
                then().
                assertThat().body("data[4].email", equalTo("charles.morris@reqres.in"),
                        "data[4].first_name", equalTo("Charles"),
                        "data[4].last_name", equalTo("Morris"),
                        "data[4].avatar", equalTo("https://reqres.in/img/faces/5-image.jpg"));


        //jsonPath ile

        JsonPath jsonPath=response.jsonPath();

        System.out.println(jsonPath.getList("data.email"));

        System.out.println(jsonPath.getString("data.first_name"));

        System.out.println(jsonPath.getString("data.last_name"));


        Assert.assertEquals("charles.morris@reqres.in",jsonPath.getString("data[4].email"));
        Assert.assertEquals("Charles",jsonPath.getString("data[4].first_name"));
        Assert.assertEquals("Morris",jsonPath.getString("data[4].last_name"));


    }




}
