package bossharriscorporation.firebasedb;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

public class Activity_driver extends AppCompatActivity {

    Button button_submit, button_time;
    EditText price_editText, contact_editText;
    String driver_price, driver_time, driver_contact;
    static final int dialog_id = 0;
    int hour_x, minute_x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        button_submit = (Button) findViewById(R.id.button_submit);
        button_time = (Button) findViewById(R.id.button_time);

        price_editText = (EditText) findViewById(R.id.price_editText);
        contact_editText = (EditText) findViewById(R.id.contact_editText);

        showTimePickerDialog();

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Firebase mref = new Firebase("https://incandescent-heat-5066.firebaseio.com/user");
                AuthData authData = mref.getAuth();
                if (authData == null) {
                    Intent intent1 = new Intent(".MainActivity");
                    startActivity(intent1);
                    Toast.makeText(getApplicationContext(),
                            "Please Login First", Toast.LENGTH_LONG).show();
                } else {
                    String uid = authData.getUid();
                    mref = mref.child(uid).child("request");
                    String rid = "1", qid = "1";
                    mref = mref.child(rid).child("quotation").child(qid);
                    driver_price = price_editText.getText().toString();
                    driver_contact = contact_editText.getText().toString();
                    mref.child("price").setValue("RM " + driver_price);
                    mref.child("time").setValue(driver_time);
                    mref.child("contact no").setValue(driver_contact);

                    Toast.makeText(getApplicationContext(),
                            "Offer submitted!", Toast.LENGTH_LONG).show();

                }
            }
        });
    }


    public void showTimePickerDialog(){
        button_time = (Button)findViewById(R.id.button_time);
        button_time.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                showDialog(dialog_id);

            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id){
        if (id == dialog_id)
            return new TimePickerDialog(Activity_driver.this, kTimePickerListener, hour_x, minute_x, true);
        return null;
    }

    public TimePickerDialog.OnTimeSetListener kTimePickerListener =
            new TimePickerDialog.OnTimeSetListener(){
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                    hour_x = hourOfDay;
                    minute_x = minute;

                    driver_time = String.format("%02d", hour_x) + ":"
                            + String.format("%02d", minute_x);


                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_driver, menu);
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
