package bossharriscorporation.firebasedb;

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

import java.util.Map;

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
        Button mButtonRequests = (Button) findViewById(R.id.buttonRequests);
        Button mButtonNew = (Button) findViewById(R.id.buttonNew);
        final TextView mTextCondition = (TextView) findViewById(R.id.textViewWeather);                  //Text and button declarations

        mref = new Firebase("https://incandescent-heat-5066.firebaseio.com/test");                      //Links our database
        mref.authWithPassword("kiwi.198@gmail.com", "admin", new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                Firebase user = new Firebase("https://incandescent-heat-5066.firebaseio.com/"+authData.getUid());
                user.child("name").setValue("Kiwi");
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                // there was an error
            }
        });

        mref.addValueEventListener(new ValueEventListener() {                                       //Event listener will notify us of changes from database

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String newCondition = (String) dataSnapshot.getValue();
                mTextCondition.setText(newCondition);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {


            }
        });

        mButtonRequests.setOnClickListener(new View.OnClickListener() {                                 //mButtonSunny button set value "Sunny" to database
            @Override
            public void onClick(View v) {
                mref.setValue("Sunny");
            }

        });

        mButtonNew.setOnClickListener(new View.OnClickListener() {                                 //mButtonNew button set value "Rainy" to database
            @Override
            public void onClick(View v) {
                mref.setValue("Rainy");
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
