package get_http_request.day09;

import base_url.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest22 extends HerOkuAppBaseUrl {

    /*
https://restful-booker.herokuapp.com/booking/47
       {
           "firstname": "Ali",
           "lastname": "Can",
           "totalprice": 500,
           "depositpaid": true,
           "bookingdates": {
               "checkin": "2022-02-01",
               "checkout": "2022-02-11"
          }
       }
1) JsonPhat
2) De-Serialization
*/

    @Test
    public void test22(){

        //1-url oluştr

        spec05.pathParams("bir","booking","iki",33);

        //2--expected oluş

        HerOkuAppTestData expectedObj= new HerOkuAppTestData();

        HashMap<String,Object>expectedTestDataMap=expectedObj.setupTestData();

        System.out.println("expected data bilgileri "+ expectedTestDataMap);
        //expected data bilgileri {firstname=Ali, bookingdates={2022-02-01=checkin, 2022-02-11=checkout}, totalprice=500, depositpaid=true, lastname=Can}


        //3--request ve response oluştur

        Response response=given().spec(spec05).when().get("/{bir}/{iki}");
        response.prettyPrint();

        //4----dogrulama

        //1) JsonPhat

        JsonPath json = response.jsonPath();
        Assert.assertEquals(expectedTestDataMap.get("firstname"), json.getString("firstname"));
        Assert.assertEquals(expectedTestDataMap.get("lastname"), json.getString("lastname"));
        Assert.assertEquals(expectedTestDataMap.get("totalprice"), json.getInt("totalprice"));
        Assert.assertEquals(expectedTestDataMap.get("depositpaid"), json.getBoolean("depositpaid"));

        Assert.assertEquals(((Map<?, ?>) expectedTestDataMap.get("bookingdates")).get("checkin"),
                json.getString("bookingdates.checkin"));
        Assert.assertEquals(((Map<?, ?>) expectedTestDataMap.get("bookingdates")).get("checkout"),
                json.getString("bookingdates.checkout"));











        //2) De-Serialization

        HashMap<String,Object>actualData=response.as(HashMap.class);

        System.out.println("actualdata bilgileri:" +actualData);
        //actualdata bilgileri:{firstname=Ali, bookingdates={checkin=2022-02-01, checkout=2022-02-11}, totalprice=500, depositpaid=true, lastname=Can}


        Assert.assertEquals(expectedTestDataMap.get("firstname"), actualData.get("firstname"));
        Assert.assertEquals(expectedTestDataMap.get("lastname"), actualData.get("lastname"));
        Assert.assertEquals(expectedTestDataMap.get("totalprice"), actualData.get("totalprice"));
        Assert.assertEquals(expectedTestDataMap.get("depositpaid"), actualData.get("depositpaid"));

        Assert.assertEquals(((Map)expectedTestDataMap.get("bookingdates")).get("checkin"),
                ((Map)actualData.get("bookingdates")).get("checkin"));
        Assert.assertEquals(((Map<?, ?>) expectedTestDataMap.get("bookingdates")).get("checkout"),
                ((Map<?, ?>) actualData.get("bookingdates")).get("checkout"));






    }
}
