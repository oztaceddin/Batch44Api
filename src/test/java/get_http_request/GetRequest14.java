package get_http_request;

import base_url.GMIBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.Authentication;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest14 extends GMIBankBaseUrl {

    @Test
    public void test14() {

         /*
           http://www.gmibank.com/api/tp-customers/110472 adresindeki müşteri bilgilerini doğrulayın

            "firstName": "Melva",
            "lastName": "Bernhard",
            "email": "chas.kuhlman@yahoo.com"
            "zipCode": "40207"

            "country" "name": "San
            "login": "delilah.metz"
             */

        spec03.pathParams("first","tp-customers","second","110472");

        Response response=given().
                spec(spec03).header("Authorization","Bearer "+generateToken()).
                when().
                get("/{first}/{second}");

        response.prettyPrint();

        response
                .then()
                .body(
                        "firstName",
                        equalTo("Melva"),
                        "lastName",
                        equalTo("Bernhard"),
                        "email",
                        equalTo("chas.kuhlman@yahoo.com"),
                        "zipCode",
                        equalTo("40207"),
                        "country.name",
                        equalTo("San Jose"),
                        "user.login",
                        equalTo("delilah.metz"));




        // Json Path ile
        JsonPath json = response.jsonPath();
        Assert.assertEquals("Melva", json.getString("firstName"));
        Assert.assertEquals("Bernhard", json.getString("lastName"));
        Assert.assertEquals("chas.kuhlman@yahoo.com", json.getString("email"));
        Assert.assertEquals("40207", json.getString("zipCode"));
        Assert.assertEquals("San Jose", json.getString("country.name"));
        Assert.assertEquals("delilah.metz", json.getString("user.login"));








    }
}
