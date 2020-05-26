package com.example.kahuja3project1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;



// Creating the second activity that will have two text fields, one which is read only and the other one is editable
public class Activity2 extends AppCompatActivity {

    TextView messageView = null;
    TextView inputName = null;
    String whatIsTheName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        messageView = (TextView) findViewById(R.id.messageView);
        inputName = (TextView) findViewById(R.id.inputText);


        inputName.setOnEditorActionListener(new TextView.OnEditorActionListener(){

            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if(i==KeyEvent.KEYCODE_ENTER || i== EditorInfo.IME_ACTION_DONE){

                    if(!inputName.getText().toString().isEmpty()){

                        whatIsTheName = inputName.getText().toString();

                        if(whatIsTheName.matches("[a-zA-Z ]+")) {
                            Intent validName = new Intent();
                            validName.putExtra("Name: ",inputName.getText().toString());
                            setResult(Activity.RESULT_OK,validName);
                            finish();
                            return true;
                        } else {
                            Intent notValidName = new Intent();
                            notValidName.putExtra("Name: ", inputName.getText().toString());
                            setResult(Activity.RESULT_CANCELED, notValidName);
                            finish();
                            return true;
                        }


                    }
                }
                return false;
            }
        });

    }


}
