package bossharriscorporation.firebasedb;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class RequestListActivity extends AppCompatActivity {
    ListView listView ;

    private Firebase mFirebaseRef;
    private ValueEventListener mConnectedListener;
    private RequestListAdapter mRequestListAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_list);
        listView = (ListView) findViewById(R.id.requestList);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Setup our view and list adapter. Ensure it scrolls to the bottom as data changes
        listView = (ListView) findViewById(R.id.requestList);
        // Tell our list adapter that we only want 50 messages at a time
        mFirebaseRef = new Firebase( "https://incandescent-heat-5066.firebaseio.com/");
        AuthData authData = mFirebaseRef.getAuth();
        if(authData == null) {
            Intent intent1 = new Intent(".MainActivity");
            startActivity(intent1);

            Toast.makeText(getApplicationContext(),
                    "Please Login First", Toast.LENGTH_LONG).show();
        } else {
            mFirebaseRef = mFirebaseRef.child("request");
            mRequestListAdapter = new RequestListAdapter(mFirebaseRef.limit(50), this, R.layout.request);
            listView.setAdapter(mRequestListAdapter);
            mRequestListAdapter.registerDataSetObserver(new DataSetObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    listView.setSelection(mRequestListAdapter.getCount() - 1);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_request_list, menu);
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