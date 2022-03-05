package get_http_request.day07;

import base_url.GMIBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest18 extends GMIBankBaseUrl {


    @Test
    public void test18() {
             /*
http://www.gmibank.com/api/tp-customers/43703

"firstName": "Alda",
"lastName": "Monahan",
"middleInitial": "Nichelle Hermann Kohler",
"email": "com.github.javafaker.Name@7c011174@gmail.com",
"mobilePhoneNumber": "909-162-8114",
"city": "St Louis",
"ssn": "108-53-6655"

1) MATCHERS CLASS
2) JSON PATH
3) De-Serialization
 */

        //1--url oluştur
        spec03.pathParams("bir", "tp-customers", "iki", "43703");

        //2--expecteddata oluştur

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstName", "Alda");
        expectedData.put("lastName", "Monahan");
        expectedData.put("middleInitial", "Nichelle Hermann Kohler");
        expectedData.put("email", "com.github.javafaker.Name@7c011174@gmail.com");
        expectedData.put("mobilePhoneNumber", "909-162-8114");
        expectedData.put("city", "St Louis");
        expectedData.put("ssn", "108-53-6655");

        System.out.println("expecteddata bilgileri" + expectedData);

        //expecteddata bilgileri{firstName=Alda, lastName=Monahan, mobilePhoneNumber=909-162-8114, middleInitial=Nichelle Hermann Kohler, city=St Louis,
        // email=com.github.javafaker.Name@7c011174@gmail.com, ssn=108-53-6655}

        //3) request ve response


        Response response = given()
                .spec(spec03)
                .header("Authorization", "Bearer " + generateToken())
                .when()
                .get("/{bir}/{iki}");

      //  response.prettyPrint();


        //Jsonı javaya(map)oluştur

        Map<String, Object> actualData = response.as(HashMap.class);//-->de serialization---

        System.out.println("actualDatabilgileri: " + actualData);

        Assert.assertEquals(expectedData.get("firstName"), actualData.get("firstName"));
        Assert.assertEquals(expectedData.get("lastName"), actualData.get("lastName"));
        Assert.assertEquals(expectedData.get("middleInitial"), actualData.get("middleInitial"));
        Assert.assertEquals(expectedData.get("email"), actualData.get("email"));
        Assert.assertEquals(expectedData.get("mobilePhoneNumber"), actualData.get("mobilePhoneNumber"));
        Assert.assertEquals(expectedData.get("city"), actualData.get("city"));
        Assert.assertEquals(expectedData.get("ssn"), actualData.get("ssn"));


        //Matcher ile

        response.then().assertThat().body("firstName", equalTo("Alda"),
                "lastName", equalTo("Monahan"),
                "middleInitial", equalTo("Nichelle Hermann Kohler"),
                "email", equalTo("com.github.javafaker.Name@7c011174@gmail.com"),
                "mobilePhoneNumber", equalTo("909-162-8114"),
                "city", equalTo("St Louis"),
                "ssn", equalTo("108-53-6655"));


        //jSonpath ile

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals("Alda", jsonPath.getString("firstName"));
        Assert.assertEquals("Monahan", jsonPath.getString("lastName"));
        Assert.assertEquals("Nichelle Hermann Kohler", jsonPath.getString("middleInitial"));
        Assert.assertEquals("com.github.javafaker.Name@7c011174@gmail.com", jsonPath.getString("email"));
        Assert.assertEquals("909-162-8114", jsonPath.getString("mobilePhoneNumber"));
        Assert.assertEquals("St Louis", jsonPath.getString("city"));
        Assert.assertEquals("108-53-6655", jsonPath.getString("ssn"));


    }
}
