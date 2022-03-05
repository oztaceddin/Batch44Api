package get_http_request.day06;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import base_url.GMIBankBaseUrl;

public class GetRequest15 extends GMIBankBaseUrl {


    @Test
    public void test15() {

        /*
        https://www.gmibank.com/api/tp-customers/85694
        "login": "dino.kohler",
        "firstName": "Winona",
        "lastName": "Abernathy",
        "email": "winonaabernathy@gmail.com"

 */
        // https://www.gmibank.com/api/tp-customers/85694
        spec03.pathParams("bir", "tp-customers", "iki", "85694");
        Response response =
                given()
                        .spec(spec03)
                        .header("Authorization", "Bearer " + generateToken())
                        .when()
                        .get("/{bir}/{iki}");
        response.prettyPeek();


        //matchers ile
        response
                .then()
                .body(
                        "firstName",
                        equalTo("Winona"),
                        "lastName",
                        equalTo("Abernathy"),
                        "email",
                        equalTo("winonaabernathy@gmail.com"));



        //jsonpath ile


        JsonPath json = response.jsonPath();

        Assert.assertEquals("Winona", json.getString("firstName"));
        Assert.assertEquals("Abernathy", json.getString("lastName"));
        Assert.assertEquals("winonaabernathy@gmail.com", json.getString("email"));
    }
}
