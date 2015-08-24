package bossharriscorporation.firebasedb;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;
/**
 * Created by Kiwi on 25/8/2015.
 */
public class RequestListAdapter extends FirebaseListAdapter<Request> {
    // The mUsername for this client. We use this to indicate which messages originated from this user

    public RequestListAdapter(Query ref, Activity activity, int layout) {
        super(ref, Request.class, layout, activity);
    }

    @Override
    protected void populateView(View view, Request request) {
        // Map a Chat object to an entry in our listview
        String departure = request.getDeparture();
        String destination = request.getDestination();
        String time = request.getTime();;
        TextView timeText = (TextView) view.findViewById(R.id.time);
        timeText.setText(time + ": ");
        ((TextView) view.findViewById(R.id.departure)).setText(departure);
        ((TextView) view.findViewById(R.id.destination)).setText(destination);
    }
}
