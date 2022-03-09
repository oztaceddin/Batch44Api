package get_http_request.day03;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest01 {

    @Test
    public void test01(){

        String url="https://restful-booker.herokuapp.com/booking";

        Response response=given().when().get(url);

        //given().when().get(url);-->REQUEST(istek)
        //Response response--->RESPONSE(istegime verilen cvp)


        response.prettyPrint();//responsen içine gelen bilgileri yazdırmak için

        System.out.println("status code : "+response.statusCode());
        System.out.println("content type : "+response.contentType());
        System.out.println("status line : "+response.statusLine());
        System.out.println("test zamanı : "+response.time());

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals("application/json; charset=utf-8",response.contentType());
        Assert.assertEquals("HTTP/1.1 200 OK",response.statusLine());

        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json; charset=utf-8").
                statusLine("HTTP/1.1 200 OK");

        System.out.println("peek"+response.peek());


    }


}
