package bossharriscorporation.firebasedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class NewRequest extends AppCompatActivity {

    AutoCompleteTextView goto_ac, from_ac;
    Button button_submit;
    String[] places = {
            "Kolej Tun Razak - KTR",                            //Kolej
            "Kolej Tun Hussein Onn - KTHO",
            "Kolej Tun Ghafar Baba - KTGB",
            "Kolej Tunku Canselor - KTC",
            "Kolej Tun Dr. Ismail - KTDI",
            "Faculty of Computing - FC",                        //Faculties
            "Fakulti Komputeran - FK",
            "Fakulti Alam Bina (FAB)",
            "Stadium UTM",                                      //UTM places
            "Arked Meranti",
            "Arked Cengal",
            "Scholar's Inn",
            "Senai International Airport",                      //Getaways
            "Larkin",
            "Sri Putri",
            "City Square Johor Bahru",                          //Malls
            "Aeon Taman University",
            "Danga Bay",

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_request);

        goto_ac = (AutoCompleteTextView)findViewById(R.id.goto_ac);
        from_ac = (AutoCompleteTextView)findViewById(R.id.from_ac);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, places);

        goto_ac.setThreshold(3);
        goto_ac.setAdapter(adapter);

        from_ac.setThreshold(3);
        from_ac.setAdapter(adapter);

        button_submit = (Button)findViewById(R.id.button_submit);                                   //declare button

        button_submit.setOnClickListener(new View.OnClickListener() {                               //sets it to listen to events
            @Override
            public void onClick(View v) {
                //retrieve current max rid
                final int[] ridCounter = new int[1];
                Firebase max_rid = new Firebase("https://incandescent-heat-5066.firebaseio.com/common/rid_counter");
                max_rid.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        ridCounter[0] = (int) snapshot.getValue();
                    }
                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        System.out.println("The read failed: " + firebaseError.getMessage());
                    }
                });

                //New Request
                String uid = "kiwi";
                Firebase request = new Firebase("https://incandescent-heat-5066.firebaseio.com/request");
                int newRidCounter = ridCounter[0] + 1;
                request = request.child(Integer.toString(newRidCounter));
                request.child("depart").setValue("KTHO");
                request.child("destination").setValue("K9");
                request.child("time").setValue("9:00AM");
                request.child("customer").setValue(uid);

                //Link with User
                Firebase newRequest = new Firebase("https://incandescent-heat-5066.firebaseio.com/user");
                newRequest = newRequest.child(uid).child("request");
                newRequest.child(Integer.toString(newRidCounter)).setValue(true);

                Toast.makeText(getApplicationContext(),
                        "Delivery request submitted!", Toast.LENGTH_LONG).show();
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_request, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
