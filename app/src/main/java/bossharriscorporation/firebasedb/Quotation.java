package bossharriscorporation.firebasedb;

/**
 * Created by Kiwi on 25/8/2015.
 */
public class Quotation {
    private String qid;
    private String price;
    private String pickUpTime;
    private String contactNo;

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private Quotation() { }

    Quotation(String qid, String price, String pickUpTime, String contactNo) {
        this.qid = qid;
        this.price = price;
        this.pickUpTime = pickUpTime;
        this.contactNo = contactNo;
    }

    public String getQid() { return qid; }

    public String getPrice() { return price; }

    public String getPickUpTime() { return pickUpTime; }

    public String getContactNo() { return contactNo; }
}
