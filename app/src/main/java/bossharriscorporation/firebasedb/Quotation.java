package bossharriscorporation.firebasedb;

/**
 * Created by Kiwi on 25/8/2015.
 */
public class Quotation {
    private String qid;
    private String price;
    private String pickUpTime;

    Quotation(String qid, String price, String pickUpTime) {
        this.qid = qid;
        this.price = price;
        this.pickUpTime = pickUpTime;
    }

    public String getQid() { return qid; }

    public String getPrice() { return price; }

    public String getPickUpTime() { return pickUpTime; }
}
