package com.example.kahuja3project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// Creating a main activity that will have the two buttons and text message
public class MainActivity extends AppCompatActivity {
    TextView messageView = null ;
    Button whatName;
    Button submitName;
    String whatIsTheName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageView = (TextView) findViewById(R.id.messageText);
        whatName = (Button) findViewById(R.id.whatButton) ;


        whatName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(MainActivity.this,Activity2.class);
                startActivityForResult(newIntent,99);
            }
        });
    }

    // Creating onActivityResult function that waits for a requestCode to return to make sure that the activity worked
    // as it was intended to do so.
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {

        if (requestCode == 99) {
            submitName = (Button) findViewById(R.id.submitNameButton) ;
            Log.i("OnactivityResult", "onActivityResult: success");
            if (resultCode == Activity.RESULT_OK) {
                whatIsTheName = data.getStringExtra("Name: ");
                submitName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // Creates a new Intent to insert a contact
                        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);

                        // Sets the MIME type to match the Contacts Provider
                        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

                        intent.putExtra(ContactsContract.Intents.Insert.NAME, whatIsTheName);

                        startActivity(intent);

                    }
                });
            } else if (resultCode == Activity.RESULT_CANCELED) {
                whatIsTheName = data.getStringExtra("Name: ");
                submitName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Name is incorrect and the name is " + whatIsTheName, Toast.LENGTH_LONG).show();
                    }
                });
            }

        }
    }
}
