package pojos;

public class BookingPojo {

     //"booking": {
    //            "firstname": "Ali",
    //            "lastname": "Can",
    //            "totalprice": 500,
    //            "depositpaid": true,
    //            "bookingdates": {
    //                            "checkin": "2022-03-01",
    //                            "checkout": "2022-03-11"
    //                      }
    //                  }
    //              }



    // 1---degişkenleri private olarak tanımlayacagız
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDatesPojo bookingdate;


    // 2---degişkenlerin degerlerine ulaşmak için get/ setter oluşturulur

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDatesPojo getBookingDatesPojo() {
        return bookingdate;
    }

    public void setBookingDatesPojo(BookingDatesPojo bookingDatesPojo) {
        this.bookingdate = bookingDatesPojo;
    }

    // 3---parametreli ve parametresiz constructor oluşturalm

    public BookingPojo() {
    }

    public BookingPojo(String firstname, String lastname, int totalprice, boolean depositpaid, BookingDatesPojo bookingDatesPojo) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdate = bookingDatesPojo;
    }


    //4---toString methodu oluşturalm


    @Override
    public String toString() {
        return "BookingPojo{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", bookingDatesPojo=" + bookingdate +
                '}';
    }
}
