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

}
