package bossharriscorporation.firebasedb;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;


public class Register extends AppCompatActivity {

    EditText password_et, confirm_password_et, email_et;        //et = EditText
    Button register;
    String get_email, get_password, get_confirmed_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = (Button) findViewById(R.id.button_registered);

        email_et = (EditText) findViewById(R.id.email_register);
        password_et = (EditText) findViewById(R.id.password_register);
        confirm_password_et = (EditText) findViewById(R.id.password_confirmed);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Converts to string, attempt to send data to db
                get_email = email_et.getText().toString();
                get_password = password_et.getText().toString();
                get_confirmed_password = confirm_password_et.getText().toString();

                //Checks for error
                if (get_password != get_confirmed_password)
                    confirm_password_et.setError("Passwords did not match, please re-enter your password.");


                Intent registered = new Intent("bossharriscorporation.firebasedb.LoginActivity");
                startActivity(registered);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
