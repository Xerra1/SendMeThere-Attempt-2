package bossharriscorporation.firebasedb;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;

public class UserRequestListActivity extends AppCompatActivity {
    ListView listView ;

    private Firebase mFirebaseRef;
    private ValueEventListener mConnectedListener;
    private RequestListAdapter mUserRequestListAdapter;
    
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
        mFirebaseRef = new Firebase( "https://incandescent-heat-5066.firebaseio.com/request");
        AuthData authData = mFirebaseRef.getAuth();
        if(authData == null) {
            Intent intent1 = new Intent(".MainActivity");
            startActivity(intent1);

            Toast.makeText(getApplicationContext(),
                    "Please Login First", Toast.LENGTH_LONG).show();
        } else {
            mFirebaseRef = mFirebaseRef;
            mUserRequestListAdapter = new RequestListAdapter(mFirebaseRef.orderByChild("customer").equalTo(authData.getUid()), this, R.layout.request);
            listView.setAdapter(mUserRequestListAdapter);
            mUserRequestListAdapter.registerDataSetObserver(new DataSetObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    listView.setSelection(mUserRequestListAdapter.getCount() - 1);
                }
            });
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Request r = (Request)parent.getAdapter().getItem(position);
                // When clicked, show a toast with the TextView text
                Intent intent = new Intent("bossharriscorporation.firebasedb.QuotationList");
                intent.putExtra("rid", r.getRid());
                startActivity(intent);
                Toast.makeText(getApplicationContext(),
                        "Sent:" + r.getRid(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
