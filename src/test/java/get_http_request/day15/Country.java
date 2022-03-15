package get_http_request.day15;

public class Country {

    //    “country”: {
    //        “id”: 3,
    //        “name”: “USA”,
    //        “states”: null
    //    },

    //degişkenleri private yap
    private int id;
    private String name;
    private String states;


    //getter setter


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    //parametreli ve parametresiz constructor yap


    public Country() {
    }

    public Country(int id, String name, String states) {
        this.id = id;
        this.name = name;
        this.states = states;
    }


    //to string yap


    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", states='" + states + '\'' +
                '}';
    }
}
