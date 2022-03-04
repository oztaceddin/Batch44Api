package get_http_request;

import base_url.GMIBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest13 extends GMIBankBaseUrl {

    @Test
    public void test13(){

        //http://www.gmibank.com/api/tp-customers/114351 adresindeki müşteri bilgilerini doğrulayın
        //    “firstName”: “Della”,
        //    “lastName”: “Heaney”,
        //    “mobilePhoneNumber”: “123-456-7893”,
        //    “address”: “75164 McClure Stream”,
        //    “country” : “USA”
        //    “state”: “New York43"
        //    “CREDIT_CARD”,hesabında 69700$ ,
        //    “CHECKING” hesabında 11190$

        spec03.pathParams("first","tp-customers","second","114351");

        Response response=given().
                spec(spec03).header("Authorization","Bearer "+generateToken()).
                when().
                get("/{first}/{second}");

        response.prettyPrint();

        //MATCHERS İLE

        response.then().assertThat().body("firstName",equalTo("Della"),
                "lastName",equalTo("Heaney"),
               "mobilePhoneNumber",equalTo("123-456-7893"),
                "address",equalTo("75164 McClure Stream"),
                "country.name",equalTo("USA"),
                "state",equalTo("New York43"),
                "accounts[0].balance",equalTo(69700),
                "accounts[1].balance",equalTo(11190));



        //jsonpath ile

        JsonPath jsonPath=response.jsonPath();

        //    “firstName”: “Della”,
        Assert.assertEquals("Della",jsonPath.getString("firstName"));

        //    “lastName”: “Heaney”,
        Assert.assertEquals("Heaney",jsonPath.getString("lastName"));
        //    “mobilePhoneNumber”: “123-456-7893”,
        Assert.assertEquals("123-456-7893",jsonPath.getString("mobilePhoneNumber"));
        //    “address”: “75164 McClure Stream”,
        Assert.assertEquals("75164 McClure Stream",jsonPath.getString("address"));
        //    “country” : “USA”
        Assert.assertEquals("USA",jsonPath.getString("country.name"));
        //    “state”: “New York43"
        Assert.assertEquals("New York43",jsonPath.getString("state"));
        //    “CREDIT_CARD”,hesabında 69700$ ,
        Assert.assertEquals(11190,jsonPath.getInt("accounts[0].balance"));
        //    “CHECKING” hesabında 11190$
        Assert.assertEquals(69700,jsonPath.getInt("accounts[1].balance"));






    }
}
