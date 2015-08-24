package bossharriscorporation.firebasedb;

/**
 * Created by Kiwi on 25/8/2015.
 */
public class Request {
    private String departure;
    private String destination;
    private String time;
    private String customer;

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private Request() {
    }

    Request(String departure, String destination, String time, String customer) {
        this.departure = departure;
        this.destination = destination;
        this.time = time;
        this.customer = customer;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public String getTime() {
        return time;
    }

    public String getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return time + ": " + departure + ", " + destination;
    }
}
