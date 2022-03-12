package get_http_request.day12;

import base_url.DummybaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;


import java.util.TreeMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteRequest01 extends DummybaseUrl {

       // /*
    //  http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde
    //
    //Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
    //{
    //"status": "success",
    //"data": "2",
    //"message": "Successfully! Record has been deleted"
    //}
    //   */

    @Test
    public void test01(){

        //1url oluştur

        spec02.pathParams("bir","api","iki","v1","uc","delete","dort",2);

        //2--expected oluştur

        DummyTestData obj =new DummyTestData();

        JSONObject expectedRequest=obj.setUpDeleteExpectedData();

        //3request ve respons

        Response response=given().
                spec(spec02).
            //    body(expectedRequest.toString()).-----delete body olmaz
                when().delete("/{bir}/{iki}/{uc}/{dort}");

        response.prettyPrint();


        JsonPath jsonPath=response.jsonPath();

        Assert.assertEquals(expectedRequest.getString("status"),jsonPath.get("status"));
        Assert.assertEquals(expectedRequest.get("data"),jsonPath.get("data"));
        Assert.assertEquals(expectedRequest.get("message"),jsonPath.get("message"));



        response.then().statusCode(200).body("status", equalTo(expectedRequest.get("status")),
                "data",equalTo(expectedRequest.get("data")),
                "message",equalTo(expectedRequest.get("message")));



        //De-Serialization

        TreeMap<String,Object> actualData=response.as(TreeMap.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(200,response.getStatusCode());

        Assert.assertEquals(expectedRequest.getString("status"),actualData.get("status"));
        Assert.assertEquals(expectedRequest.getString("data"),actualData.get("data"));
        Assert.assertEquals(expectedRequest.getString("message"),actualData.get("message"));







    }
}
