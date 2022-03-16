package get_http_request.day15.GMIBank;

import base_url.GMIBankBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import utilities.ReadText;
import utilities.WriteToText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GMIBank02 extends GMIBankBaseUrl {
       //   /*
    //http://www.gmibank.com/api/tp-customers end point'ine
    //request gönderin
    // 1) Tüm Customer bilgilerini ekrana yazdırırn.

    // 2) Tüm Customer emaillerini text dosyası olarak kaydedin
    //
    // 3) dönen reponse'ta winonaabernathy@gmail.com, MerrillPrice@gmail.com, LesleyKing@gmail.com
    //    E-maillerinin olduğunu doğrulayın
    // */

    @Test
    public void test2() throws IOException {

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

        ObjectMapper obj=new ObjectMapper();
        customers=obj.readValue(response.asString(),Customer[].class);

      //  response.prettyPrint();


        // 1) Tüm Customer bilgilerini ekrana yazdırırn.

        for (int i = 0; i < customers.length; i++) {
            System.out.println(i+1+". customers email = " + customers[i].getEmail());
        }

        // 2) Tüm Customer emaillerini text dosyası olarak kaydedin

        String emailFile="src/test/java/get_http_request/day15/GMIBank/email kayıt dosyası.txt";

        WriteToText.saveEmailData(emailFile,customers);


        // 3) dönen reponse'ta winonaabernathy@gmail.com, MerrillPrice@gmail.com, LesleyKing@gmail.com
        //    E-maillerinin olduğunu doğrulayın

        List<String> expectedEmail = new ArrayList<>();

        expectedEmail.add("winonaabernathy@gmail.com");
        expectedEmail.add("MerrillPrice@gmail.com");
        expectedEmail.add("LesleyKing@gmail.com");


        List<String> actualEmail = ReadText.readCustomerEmailList(emailFile);

        Assert.assertTrue("emailler ler eslesmiyor", actualEmail.containsAll(expectedEmail));






    }
}
