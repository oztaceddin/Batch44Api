package get_http_request.day07;

import base_url.GMIBankBaseUrl;
import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class GetRequest17 extends GMIBankBaseUrl {


    @Test
    public void test17(){


         /*
   http://www.gmibank.com/api/tp-customers/114351 adresindeki müşteri bilgilerini doğrulayın

{
   "firstName": "Della",
   "lastName": "Heaney",
   "email": "ricardo.larkin@yahoo.com",
   "mobilePhoneNumber": "123-456-7893",
}
    */

        //1--url oluştur
        spec03.pathParams("bir","tp-customers","iki","114351");

        //2--expecteddata oluştur

        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("firstName","Della");
        expectedData.put("lastName","Heaney");
        expectedData.put("email","ricardo.larkin@yahoo.com");
        expectedData.put("mobilePhoneNumber","123-456-7893");

        System.out.println("expecteddata bilgileri"+expectedData);

        //expecteddata bilgileri{firstName=Della, lastName=Heaney, mobilePhoneNumber=123-456-7893, email=ricardo.larkin@yahoo.com}

        //3) request ve response


        Response response= given()
                .spec(spec03)
                .header("Authorization", "Bearer " + generateToken())
                .when()
                .get("/{bir}/{iki}");

      //  response.prettyPrint();


        //Jsonı javaya(map)oluştur

        Map<String,Object> actualData=response.as(HashMap.class);//-->de serialization---

        System.out.println("actualDatabilgileri: "+actualData);

        //actualDatabilgileri: {lastName=Heaney, mobilePhoneNumber=123-456-7893, zipCode=53081, country={id=3, name=USA, states=null}, address=75164 McClure Stream, city=Gislasonburgh, zelleEnrolled=true, ssn=823-25-7239,
        // firstName=Della, middleInitial=Russell Homenick V, phoneNumber=213-456-7893, id=114351, state=New York43, accounts=[{id=107250, description=New Account_6thGenQA_01, balance=11190, accountType=CHECKING, accountStatusType=ACTIVE,
        // createDate=2021-11-24T23:00:00Z, closedDate=2022-11-24T23:00:00Z, employee=null, accountlogs=null}, {id=2333, description=musteri omer hesap3, balance=69700, accountType=CREDIT_CARD, accountStatusType=ACTIVE, createDate=2020-11-06T23:00:00Z,
        // closedDate=2024-11-06T23:00:00Z, employee=null, accountlogs=null}], user=null, email=ricardo.larkin@yahoo.com, createDate=2021-12-05T23:00:00Z}


        //dogrulama---de serialization---


        Assert.assertEquals(expectedData.get("firstName"),actualData.get("firstName"));
        Assert.assertEquals(expectedData.get("lastName"),actualData.get("lastName"));
        Assert.assertEquals(expectedData.get("mobilePhoneNumber"),actualData.get("mobilePhoneNumber"));
        Assert.assertEquals(expectedData.get("email"),actualData.get("email"));

        // MATCHERS CLASS
        response.then().assertThat().body("firstName", equalTo("Della"),
                "lastName", equalTo("Heaney"),
                "email",equalTo("ricardo.larkin@yahoo.com"),
                "mobilePhoneNumber",    equalTo("123-456-7893"));

        //JSONPATH ILE
        JsonPath json = response.jsonPath();
        Assert.assertEquals("Della",json.getString("firstName"));
        Assert.assertEquals("Heaney",json.getString("lastName"));
        Assert.assertEquals("ricardo.larkin@yahoo.com",json.getString("email"));
        Assert.assertEquals("123-456-7893",json.getString("mobilePhoneNumber"));






    }
}
