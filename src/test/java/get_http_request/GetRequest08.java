package get_http_request;

import base_url.DummybaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest08 extends DummybaseUrl {


    @Test
    public void test08() {

        /*
       http://dummy.restapiexample.com/api/v1/employees url'inde bulunan

       1) Butun calisanlarin isimlerini consola yazdıralim
       2) 3. calisan kisinin ismini konsola yazdıralim
       3) Ilk 5 calisanin adini konsola yazdiralim
       4) En son calisanin adini konsola yazdiralim
        */

        spec02.pathParams("first", "api", "second", "v1", "third", "employees");

        Response response = given().spec(spec02).when().get("/{first}/{second}/{third}");

        ///{first}/{second}/{third}---->/api/v1/employees

        //   response.prettyPrint();


        //1) Butun calisanlarin isimlerini consola yazdıralim

        JsonPath jsonPath = response.jsonPath();

        System.out.println("çalışanların isimleri: " + jsonPath.getString("data.employee_name"));


        //       2) 3. calisan kisinin ismini konsola yazdıralim

        System.out.println("3.çalışanın ismi :" + jsonPath.getString("data[2].employee_name"));

        //       3) Ilk 5 calisanin adini konsola yazdiralim

        for (int i = 0; i < 5; i++) {
            System.out.println(i + 1 + ". calisan : " + jsonPath.getString("data[" + i + "].employee_name"));

        }


        System.out.println("*********2.yönteö***********");

        System.out.println(jsonPath.getString("data.employee_name[0,1,2,3,4]"));


        //3. Yol
        System.out.println(jsonPath.getList("data[0,1,2,3,4].employee_name"));




        //4) En son calisanin adini konsola yazdiralim

        System.out.println("son çalışan kişinin ismi :"+jsonPath.getString("data[-1].employee_name"));


    }
}
