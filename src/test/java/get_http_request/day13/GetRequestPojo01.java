package get_http_request.day13;

import base_url.DummybaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.DummyDataPojo;
import pojos.DummyPojo;

import static io.restassured.RestAssured.given;

public class GetRequestPojo01 extends DummybaseUrl {

    /*
GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/1
                           Status code is 200
{
 "status": "success",
 "data": {
   "id": 1,
   "employee_name": "Tiger Nixon",
   "employee_salary": 320800,
   "employee_age": 61,
   "profile_image": ""
   },
 "message": "Successfully! Record has been fetched."
 }

*/

    @Test
    public void test01(){
        //GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/1

        spec02.pathParams("1","api","2","v1","3","employee","4",1);


        DummyDataPojo data=new DummyDataPojo(1,"Tiger Nixon",320800,61,"");
        System.out.println("data = " + data);

        DummyPojo expectedData=new DummyPojo("success",data,"Successfully! Record has been fetched.");

        System.out.println("dummyPojo = " + expectedData);


        Response response=given().
                contentType(ContentType.JSON).
                spec(spec02).
                when().
                get("/{1}/{2}/{3}/{4}");

        response.prettyPrint();


        //dogrulama

       DummyPojo actual=response.as(DummyPojo.class);
        System.out.println("actual = " + actual);


//        Assert.assertEquals(expectedData.getStatus(),actual.getStatus());
//        Assert.assertEquals(expectedData.getDummydata().getId(),actual.getDummydata().getId());
//        Assert.assertEquals(expectedData.getDummydata().getEmployee_name(),actual.getDummydata().getEmployee_name());
//        Assert.assertEquals(expectedData.getDummydata().getEmployee_salary(),actual.getDummydata().getEmployee_salary());
//        Assert.assertEquals(expectedData.getDummydata().getEmployee_age(),actual.getDummydata().getEmployee_age());
//        Assert.assertEquals(expectedData.getDummydata().getProfile_image(),actual.getDummydata().getProfile_image());
//        Assert.assertEquals(expectedData.getMessage(),actual.getMessage());

        






    }



}
