package get_http_request.day08;

import base_url.DummybaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest19 extends DummybaseUrl {


    /*
     http://dummy.restapiexample.com/api/v1/employees
     1) Status kodunun 200,
     2) 10’dan büyük tüm id'leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,
     3) 30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu
     ) Maası 350000 den büyük olan tüm employee name'leri ekrana yazdırın
     ve bunların içerisinde "Charde Marshall" olduğunu test edin
     */

    @Test
    public void test19(){

        // http://dummy.restapiexample.com/api/v1/employees

        spec02.pathParams("bir","api","iki","v1","uc","employees");

        Response response=given().spec(spec02).when().get("/{bir}/{iki}/{uc}");


        response.prettyPrint();



        //1) Status kodunun 200,

        Assert.assertEquals(200,response.statusCode());
        //response.then().assertThat().statusCode(200);


        //     2) 10’dan büyük tüm id'leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,

        JsonPath jsonPath=response.jsonPath();

        List<Integer> idList=jsonPath.getList("data.id.findAll{it>10}");

        //data.findAll{it.id>10}---buşekilde
        //data.findAll{it.id>10}.id---->GROOVY java platformu üzerinde çalışan bilgisayar dilirdir
        //GROOVY ile loop kullanmadan response dan gelen degerleri bir şarta göre alabiliriz

        System.out.println("idler: "+idList);


        //     3) 30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu

        List<Integer> yas= jsonPath.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println(yas);

        Collections.sort(yas);

        Assert.assertEquals(23,(int)yas.get(yas.size()-1));

        //    4) Maası 350000 den büyük olan tüm employee name'leri ekrana yazdırın

        List<Integer> salary= jsonPath.getList("data.findAll{it.employee_salary<350000}.employee_name");
        System.out.println("Maası 350000 den büyük :"+salary);

        Assert.assertFalse(salary.contains("Charde Marshall"));

        //     ve bunların içerisinde "Charde Marshall" olduğunu test edin







    }

}
