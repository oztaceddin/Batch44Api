package get_http_request.day05;

import base_url.DummybaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest09 extends DummybaseUrl {

    /*
     http://dummy.restapiexample.com/api/v1/employee/12 URL'E GiT.
     1) Matcher CLASS ile
     2) JsonPath ile dogrulayin.
     */

    @Test
    public void test09(){

        spec02.pathParams("birinci", "api", "ikinci", "v1", "ucuncu", "employee","dorduncu","12");

        Response response = given().spec(spec02).when().get("/{birinci}/{ikinci}/{ucuncu}/{dorduncu}");

        ///{birinci}/{ikinci}/{ucuncu}/{dorduncu}----->/api/v1/employee/12

        response.prettyPrint();

        /*
          "id": 12,
        "employee_name": "Quinn Flynn",
        "employee_salary": 342000,
        "employee_age": 22,
        "profile_image": ""
         */

        //matchers ile
        //headers
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

        //body
        response.
                then().
                assertThat()
                .body("data.employee_name", equalTo("Quinn Flynn"),
                        "data.employee_salary",equalTo(342000),
                        "data.employee_age",equalTo(22));


        //jsonpath ile

        JsonPath jsonPath=response.jsonPath();

//        System.out.println(jsonPath.getString("data.employee_name"));
//        System.out.println(jsonPath.getString("data.employee_salary"));
//        System.out.println(jsonPath.getString("data.employee_age"));


//        Assert.assertEquals("Quinn Flynn",jsonPath.getString("data.employee_name"));
//        Assert.assertEquals("342000",jsonPath.getString("data.employee_salary"));
//        Assert.assertEquals("22",jsonPath.getString("data.employee_age"));

        Assert.assertEquals("Quinn Flynn",jsonPath.getString("data.employee_name"));
        Assert.assertEquals(342000,jsonPath.getInt("data.employee_salary"));
        Assert.assertEquals(22,jsonPath.getInt("data.employee_age"));


    }








}
