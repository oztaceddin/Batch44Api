package test_data;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    public static Map<String,Object> setupTestData(){



        Map<String,Object> expectedData=new HashMap<>();

        expectedData.put("StatusCode",200);
        expectedData.put("completed",false);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("via","1.1 vegur");
        expectedData.put("userId",1);
        expectedData.put("Server","cloudflare");





        return expectedData;


    }

        //  Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
    //  {
    //    "userId": 55,
    //    "title": "Tidy your room",
    //    "completed": false,
    //    "id": …
    //   }
    //*/

    public JSONObject setUpPostData(){
        JSONObject expectedRequest = new JSONObject();
        expectedRequest.put("userId", 55);
        expectedRequest.put("title", "Tidy your room");
        expectedRequest.put("completed", false);
        expectedRequest.put("statusCode", 201);
        expectedRequest.put("id", 201);
        return expectedRequest;
    }

    //***********PutRequest01

    //{
    //"userId": 21,
    //"title": "Wash the dishes",
    //"completed": false
    //}


    public JSONObject setUpPutData(){

        JSONObject expectedRequest=new JSONObject();
        expectedRequest.put("userId",21);
        expectedRequest.put("title","Wash the dishes");
        expectedRequest.put("completed", false);


     return expectedRequest;

    }



    //*******PatchRequest01
    //  {
    //
    //     "title": "Batch44"
    //
    //    }

    public JSONObject SetUpPatchRequestData(){

        JSONObject requestData=new JSONObject();
        requestData.put("title","Batch44");

        return requestData;
    }

    //"userId": 10,
    //"title": "Batch44"
    //"completed": true,
    //"id": 198
    //}

    public JSONObject setUpPatcEcpectedData(){

        JSONObject expectedData=new JSONObject();
        expectedData.put("userId",10);
        expectedData.put("title","Batch44");
        expectedData.put("completed",true);
        expectedData.put("id",198);


        return expectedData;
    }









}
