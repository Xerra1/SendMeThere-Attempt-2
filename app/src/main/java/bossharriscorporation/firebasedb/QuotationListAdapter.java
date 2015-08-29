package bossharriscorporation.firebasedb;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;

/**
 * Created by Kiwi on 25/8/2015.
 */
public class QuotationListAdapter extends FirebaseListAdapter<Quotation> {
    // The mUsername for this client. We use this to indicate which messages originated from this user

    public QuotationListAdapter(Query ref, Activity activity, int layout) {
        super(ref, Quotation.class, layout, activity);
    }

    @Override
    protected void populateView(View view, Quotation quotation) {
        // Map a Chat object to an entry in our listview
        String price = quotation.getPrice();
        String pickUpTime = quotation.getPickUpTime();
        String contactNo = quotation.getContactNo();
        TextView priceText = (TextView) view.findViewById(R.id.price);
        priceText.setText(price);
        ((TextView) view.findViewById(R.id.pickUpTime)).setText("Pick Up Time: " + pickUpTime);
        ((TextView) view.findViewById(R.id.contactNo)).setText("Contact: " + contactNo);
    }
}
