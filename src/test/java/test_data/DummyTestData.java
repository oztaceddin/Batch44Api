package test_data;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyTestData {

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


    public HashMap<String, Object> setupTestData() {

        //40,21 ve 19 yaslarında
        List<Integer> yaslar = new ArrayList<>();

        yaslar.add(40);
        yaslar.add(21);
        yaslar.add(19);


        //10.calişanın bilgileri
        HashMap<String, Object> onuncuCalisan = new HashMap<>();


        onuncuCalisan.put("id", 10);
        onuncuCalisan.put("employee_name", "Sonya Frost");
        onuncuCalisan.put("employee_salary", 23);
        onuncuCalisan.put("profile_image", "");


        HashMap<String, Object> expectedData = new HashMap<>();

        expectedData.put("StatusCode", 200);
        expectedData.put("ondorduncuCalisan", "Haley Kennedy");
        expectedData.put("calisansayisi", 24);
        expectedData.put("sondan3calisaninmaasi", 675000);
        expectedData.put("arananyaslar", yaslar);
        expectedData.put("Onuncucalisan", onuncuCalisan);

        return expectedData;
    }

    //{
    //    "name":"Ali Can",
    //    "salary":"2000",
    //    "age":"40",

    public HashMap<String, Object> setUpRequestBody() {

        HashMap<String, Object> requestBody = new HashMap<>();

        requestBody.put("name", "Ali Can");
        requestBody.put("salary", "2000");
        requestBody.put("age", "40");


        return requestBody;

    }

    //gönderildiğinde,Status kodun 200 olduğunu ve dönen response body nin,
    //
    //{
    //    "status": "success",
    //    "data": {
    //    "id":…
    //},
    //    "message": "Successfully! Record has been added."

    public HashMap<String, Object> setUpExpectedData() {

        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("statusCode", 200);
        expectedData.put("status", "success");
        expectedData.put("message", "Successfully! Record has been added.");


        return expectedData;

    }

    //*****************DeleteRequest01

      //   // /*
    //    //  http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde
    //    //
    //    //Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
    //    //{
    //    //"status": "success",
    //    //"data": "2",
    //    //"message": "Successfully! Record has been deleted"
    //    //}
    //    //   */

    public JSONObject setUpDeleteExpectedData(){

        JSONObject expectedData=new JSONObject();
        expectedData.put("status","success");
        expectedData.put("data","2");
        expectedData.put("message","Successfully! Record has been deleted");



        return expectedData;
    }
}
