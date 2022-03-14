package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyPojo {



    //"status": "success",
    // "data": {

    // "message": "Successfully! Record has been fetched."
    // }

    private String status;
    private DummyDataPojo dummydata;
    private String message;


    //getter setter


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DummyDataPojo getDummydata() {
        return dummydata;
    }

    public void setDummydata(DummyDataPojo dummydata) {
        this.dummydata = dummydata;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //parametreli ve parametresiz consr


    public DummyPojo() {
    }

    public DummyPojo(String status, DummyDataPojo dummydata, String message) {
        this.status = status;
        this.dummydata = dummydata;
        this.message = message;
    }

    //tostring


    @Override
    public String toString() {
        return "DummyPojo{" +
                "status='" + status + '\'' +
                ", dummydata=" + dummydata +
                ", message='" + message + '\'' +
                '}';
    }
}
