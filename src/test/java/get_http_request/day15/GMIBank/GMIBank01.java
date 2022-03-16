package get_http_request.day15.GMIBank;

import base_url.GMIBankBaseUrl;
import get_http_request.day15.GMIBank.Customer;
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

public class GMIBank01 extends GMIBankBaseUrl {

    // /*
    //    http://www.gmibank.com/api/tp-customers end point'ine
    //    request gönderin
    //     1) Tüm Customer bilgilerini ekrana yazdırırn.
    //
    //     2) Tüm Customer SSN lerini ekrana yazdırın.
    //
    //     3) Tüm Customer SSN lerini text dosyası olarak kaydedin
    //
    //     4) Olusturduğunuz text dosyasından  SSNleri okuyarak
    //        "531-95-8437", "049-43-2360", "123-34-3434" SSNlerinin olduğunu doğrulayın
    //     */

    @Test
    public void test1() throws IOException {


        Customer[] customers;//custumer clasına ulaşmak için yaptık

        spec03.pathParam("1", "tp-customers");


        Response response = given()
                .spec(spec03)
                .header("Authorization", "Bearer " + generateToken())
                .when()
                .get("/{1}").
                then().
                contentType(ContentType.JSON).
                extract().response();

        //  response.prettyPrint();


        //objectMapper ı de-seriliazation yapmak içi
        //Json veya java data okumak için

        ObjectMapper obj = new ObjectMapper();

        customers = obj.readValue(response.asString(), Customer[].class);


        //     1) Tüm Customer bilgilerini ekrana yazdırırn.

        for (int i = 0; i < customers.length; i++) {

            System.out.println(i + 1 + ". customers = " + customers[i]);

        }

        System.out.println("*************************************");

        //     2) Tüm Customer SSN lerini ekrana yazdırın.


        for (int i = 0; i < customers.length; i++) {

            System.out.println(i + 1 + ". customers ssn = " + customers[i].getSsn());

        }


        //     3) Tüm Customer SSN lerini text dosyası olarak kaydedin

        String fileName = "src/test/java/get_http_request/day15/GMIBank/ssn kayıt dosyası.txt";

        WriteToText.saveSSNData(fileName, customers);

        System.out.println("");
        System.out.println("*************************************");


        //     4) Olusturduğunuz text dosyasından  SSNleri okuyarak
        //        "531-95-8437", "049-43-2360", "123-34-3434" SSNlerinin olduğunu doğrulayın


        List<String> expectedSsnIds = new ArrayList<>();

        expectedSsnIds.add("531-95-8437");
        expectedSsnIds.add("049-43-2360");
        expectedSsnIds.add("123-34-3434");


        List<String> actualSsnId = ReadText.readCustomerSSNList(fileName);

        Assert.assertTrue("ssn ler eslesmiyor", actualSsnId.containsAll(expectedSsnIds));


    }

}
