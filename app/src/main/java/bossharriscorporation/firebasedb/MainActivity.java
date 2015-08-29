package bossharriscorporation.firebasedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Firebase mref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        Button myRequests = (Button) findViewById(R.id.myRequests);
        //Button allRequests = (Button) findViewById(R.id.allRequests);
        Button mbuttonNew = (Button) findViewById(R.id.buttonNew);
       // Button button_test1 = (Button)findViewById(R.id.button_test1);
        final TextView welcomeMsg = (TextView)findViewById(R.id.textViewDashboard);
       // final TextView mTextCondition = (TextView) findViewById(R.id.textViewDashboard);                  //Text and button declarations

        mref = new Firebase("https://incandescent-heat-5066.firebaseio.com/user");                      //Links our database
        final AuthData authData = mref.getAuth();
        if(authData == null) {
            Intent intent1 = new Intent("android.intent.action.MAIN");
            startActivity(intent1);
            Toast.makeText(getApplicationContext(),
                    "Please Login First", Toast.LENGTH_LONG).show();
        } else {
        }

        myRequests.setOnClickListener(new View.OnClickListener() {                                 //mButtonSunny button set value "Sunny" to database
            @Override
            public void onClick(View v) {
                // mref.setValue("View your requests");
                Intent intent2 = new Intent("bossharriscorporation.firebasedb.UserRequestList");
                startActivity(intent2);
            }

        });

        /*allRequests.setOnClickListener(new View.OnClickListener() {                                 //mButtonSunny button set value "Sunny" to database
            @Override
            public void onClick(View v) {
                // mref.setValue("View your requests");
                Intent intent2 = new Intent("bossharriscorporation.firebasedb.RequestList");
                startActivity(intent2);
            }

        });

        button_test1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent("bossharriscorporation.firebasedb.Activity_driver");
                startActivity(intent3);
            }
        }); */

        mbuttonNew.setOnClickListener(new View.OnClickListener() {                                 //mbuttonNew button set value "Rainy" to database
            @Override
            public void onClick(View v) {
                //mref.setValue("Create new request");
                Intent intent2 = new Intent("bossharriscorporation.firebasedb.NewRequest");
                startActivity(intent2);
            }

        });




    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
