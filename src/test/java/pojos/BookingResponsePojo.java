package pojos;

public class BookingResponsePojo {

    //{bookingis:11,
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

    private int bookingid;
    private BookingPojo bookingPojo;

    // 2---degişkenlerin degerlerine ulaşmak için get/ setter oluşturulur


    public int getBooking() {
        return bookingid;
    }

    public void setBooking(int booking) {
        this.bookingid = booking;
    }

    public BookingPojo getBookingPojo() {
        return bookingPojo;
    }

    public void setBookingPojo(BookingPojo bookingPojo) {
        this.bookingPojo = bookingPojo;
    }


    // 3---parametreli ve parametresiz constructor oluşturalm


    public BookingResponsePojo() {
    }

    public BookingResponsePojo(int booking, BookingPojo bookingPojo) {
        this.bookingid = booking;
        this.bookingPojo = bookingPojo;
    }



    //4---toString methodu oluşturalm


    @Override
    public String toString() {
        return "BookingResponsePojo{" +
                "booking=" + bookingid +
                ", bookingPojo=" + bookingPojo +
                '}';
    }
}
