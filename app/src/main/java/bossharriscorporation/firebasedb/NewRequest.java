package bossharriscorporation.firebasedb;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class NewRequest extends AppCompatActivity {

    String user_destination, user_depart, user_time;
    AutoCompleteTextView goto_ac, from_ac;
    static final int dialog_id = 0;
    int hour_x, minute_x;
    TextView time_textView2;
    final Firebase max_rid = new Firebase("https://incandescent-heat-5066.firebaseio.com/common/rid_counter");
    long ridCounter;
    Button button_submit, button_time;
    String[] places = {
            "Kolej Tun Razak - KTR",                            //Kolej
            "Kolej Tun Hussein Onn - KTHO",
            "Kolej Tun Ghafar Baba - KTGB",
            "Kolej Tunku Canselor - KTC",
            "Kolej Tun Dr. Ismail - KTDI",
            "Kolej Dato' Onn Jaafar - KDOJ",
            "Kolej 9 - K9",
            "Kolej 10 - K10",
            "Faculty of Computing - FC",                        //Faculties
            "Fakulti Komputeran - FK",
            "Fakulti Alam Bina - FAB",
            "Stadium UTM",                                      //UTM places
            "Arked Meranti",
            "Arked Cengal",
            "Scholar's Inn",
            "Senai International Airport",                      //Getaways
            "Larkin",
            "Sri Putri",
            "City Square Johor Bahru - CS",                          //Malls
            "Aeon Taman University",
            "Danga Bay",

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_request);
        time_textView2 = (TextView) findViewById(R.id.time_textView2);

        goto_ac = (AutoCompleteTextView)findViewById(R.id.goto_ac);
        from_ac = (AutoCompleteTextView)findViewById(R.id.from_ac);

        showTimePickerDialog();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, places);

        goto_ac.setThreshold(2);
        goto_ac.setAdapter(adapter);

        from_ac.setThreshold(2);
        from_ac.setAdapter(adapter);

        button_submit = (Button)findViewById(R.id.button_submit);                                   //declare button
        button_submit.setOnClickListener(new View.OnClickListener() {                               //sets it to listen to events
            @Override
            public void onClick(View v) {
                Firebase mref = new Firebase("https://incandescent-heat-5066.firebaseio.com/");
                //New Request
                AuthData authData = mref.getAuth();
                if(authData == null) {
                    Intent intent1 = new Intent(".MainActivity");
                    startActivity(intent1);
                    Toast.makeText(getApplicationContext(),
                            "Please Login First", Toast.LENGTH_LONG).show();
                } else {
                    String uid = authData.getUid();
                    //Add new request data for the request id
                    Firebase request = new Firebase("https://incandescent-heat-5066.firebaseio.com/request");
                    user_destination = goto_ac.getText().toString();
                    user_depart = from_ac.getText().toString();
                    user_time = time_textView2.getText().toString();
                    request = request.push();
                    Request r = new Request(request.getKey(), user_depart, user_destination, user_time, uid);
                    request.setValue(r);
                    finish();
                }

            }

        });
    }

    public void showTimePickerDialog(){
        button_time = (Button)findViewById(R.id.button_time);
        button_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(dialog_id);

            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id){
        if (id == dialog_id)
            return new TimePickerDialog(NewRequest.this, kTimePickerListener, hour_x, minute_x, true);
        return null;
    }

    public TimePickerDialog.OnTimeSetListener kTimePickerListener =
            new TimePickerDialog.OnTimeSetListener(){
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                    hour_x = hourOfDay;
                    minute_x = minute;

                    user_time = String.format("%02d", hour_x) + ":"
                            + String.format("%02d", minute_x);
                   // button_time = setText(user_time);
                    time_textView2.setText(user_time);
                }
            };
    @Override
    public void onStart() {
        super.onStart();
    }

}
