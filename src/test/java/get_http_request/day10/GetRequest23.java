package get_http_request.day10;

import base_url.DummybaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest23 extends DummybaseUrl {

    /*
      http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
      Status kodun 200 olduğunu,
      14. Çalışan isminin "Haley Kennedy" olduğunu,
      Çalışan sayısının 24 olduğunu,
      Sondan 3. çalışanın maaşının 675000 olduğunu
      40,21 ve 19 yaslarında çalışanlar olup olmadığını
      10. Çalışan bilgilerinin bilgilerinin aşağıdaki gibi

     {
        "id": 10,
        "employee_name": "Sonya Frost",
        "employee_salary": 103600,
        "employee_age": 23,
        "profile_image": ""
        }

           olduğunu test edin.
      */
    @Test
    public void test23(){

        //1---url oluştur
        spec02.pathParams("bir","api",
                     "iki","v1",
                             "uc","employees");

        //2-expekted data oluştur

        DummyTestData expectedObj =new DummyTestData();

        HashMap<String,Object> expectedTestDataMap=expectedObj.setupTestData();

        System.out.println("expected bilgileri "+expectedTestDataMap);
        //expected bilgileri {Onuncucalisan={profile_image=, employee_name=Sonya Frost, employee_salary=23, id=10},
        // sondan3calisaninmaasi=675000, arananyaslar=[40, 21, 19], calisansayisi=24, StatusCode=200, ondorduncuCalisan=Haley Kennedy}

        //3-request--response

        Response response=given().spec(spec02).when().get("/{bir}/{iki}/{uc}");
       // response.prettyPrint();

        //4--dogrulama

        //a--de serialization

        HashMap<String,Object> actualData=response.as((HashMap.class));
        System.out.println("actualData = " + actualData);


        //Status kodun 200 olduğunu,

        Assert.assertEquals(expectedTestDataMap.get("StatusCode"),response.getStatusCode());


        //      14. Çalışan isminin "Haley Kennedy" olduğunu,

        Assert.assertEquals(expectedTestDataMap.get("ondorduncuCalisan"),((Map)((List)actualData.get("data")).get(13)).get("employee_name"));
        //      Çalışan sayısının 24 olduğunu,

        Assert.assertEquals(expectedTestDataMap.get("calisansayisi"),((List) actualData.get("data")).size());



        //      Sondan 3. çalışanın maaşının 675000 olduğunu

        Assert.assertEquals(expectedTestDataMap.get("sondan3calisaninmaasi"),
                ((Map)((List)actualData.get("data")).get(((List)actualData.get("data")).size()-3)).get("employee_salary"));


        int dataSize=((List)actualData.get("data")).size();
        Assert.assertEquals(expectedTestDataMap.get("sondan3calisaninmaasi"),
                ((Map) ((List)actualData.get("data")).get(dataSize-3)).get("employee_salary"));

        //      40,21 ve 19 yaslarında çalışanlar olup olmadığını

        List<Integer> actualyaslarListesi=new ArrayList<>();

        for (int i = 0; i < dataSize; i++) {


                actualyaslarListesi.add(((Integer) ((Map)((List<?>) actualData.get("data")).get(i)).get("employee_age")));
        }
        System.out.println("actualyaslarListesi = " + actualyaslarListesi);
        //actualyaslarListesi = [61, 63, 66, 22, 33, 61, 59, 55, 39, 23, 30, 22, 36, 43, 19, 66, 64, 59, 41, 35, 30, 40, 21, 23]

            Assert.assertTrue(actualyaslarListesi.containsAll((Collection<?>) expectedTestDataMap.get("arananyaslar")));
            






        //      10. Çalışan bilgilerinin bilgilerinin aşağıdaki gibi

     /*
        {"id": 10,
               "employee_name": "Sonya Frost",
               "employee_salary": 103600,
               "employee_age": 23,
               "profile_image": ""}
        */

        Assert.assertEquals(((Map) expectedTestDataMap.get("Onuncucalisan")).get("employee_name"),
                ((Map)((List) actualData.get("data")).get(9)).get("employee_name"));

        Assert.assertEquals(((Map) expectedTestDataMap.get("Onuncucalisan")).get("employee_salary"),
                ((Map)((List) actualData.get("data")).get(9)).get("employee_salary"));

        Assert.assertEquals(((Map) expectedTestDataMap.get("Onuncucalisan")).get("employee_age"),
                ((Map)((List) actualData.get("data")).get(9)).get("employee_age"));

        Assert.assertEquals(((Map) expectedTestDataMap.get("Onuncucalisan")).get("profile_image"),
                ((Map)((List) actualData.get("data")).get(9)).get("profile_image"));








    }
}
