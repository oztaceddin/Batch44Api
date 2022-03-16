package get_http_request.day15.GMIBank;

import base_url.GMIBankBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import utilities.WriteToText;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class GMIBank03 extends GMIBankBaseUrl {
      //http://www.gmibank.com/api/tp-customers end point'ine
    //request gönderin
    //First Name,
    //Last Name,
    //email,
    //mobilePhone,
    //Adres
    //city
    //Bilgilerini text dosyasına yazdırın

    @Test
    public void test3() throws IOException {

        Customer[] customers;

        //http://www.gmibank.com/api/tp-customers

        spec03.pathParam("1","tp-customers");

        Response response = given()
                .spec(spec03)
                .header("Authorization", "Bearer " + generateToken())
                .when()
                .get("/{1}").
                then().
                contentType(ContentType.JSON).
                extract().response();

        response.prettyPrint();

        ObjectMapper obj=new ObjectMapper();
        customers=obj.readValue(response.asString(),Customer[].class);


        //*************

        String customerFile="src/test/java/get_http_request/day15/GMIBank/customer kayıt dosyası.txt";

        WriteToText.saveCustomerData(customerFile,customers);





    }
}
