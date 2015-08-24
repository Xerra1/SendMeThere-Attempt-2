package bossharriscorporation.firebasedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
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

        Button mbuttonRequests = (Button) findViewById(R.id.buttonRequests);
        Button mbuttonNew = (Button) findViewById(R.id.buttonNew);
        final TextView mTextCondition = (TextView) findViewById(R.id.textViewDashboard);                  //Text and button declarations

        mref = new Firebase("https://incandescent-heat-5066.firebaseio.com/test");                      //Links our database

        mref.addValueEventListener(new ValueEventListener() {                                       //Event listener will notify us of changes from database

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Fetches the information in database
                /*String newCondition = (String) dataSnapshot.getValue();
                mTextCondition.setText(newCondition); */

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {


            }
        });

        mbuttonRequests.setOnClickListener(new View.OnClickListener() {                                 //mButtonSunny button set value "Sunny" to database
            @Override
            public void onClick(View v) {
               // mref.setValue("View your requests");
                Intent intent2 = new Intent("bossharriscorporation.firebasedb.RequestList");
                startActivity(intent2);
            }

        });

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
