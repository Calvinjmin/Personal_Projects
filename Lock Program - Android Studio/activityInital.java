package com.example.calvinjmin.lockprogram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import org.w3c.dom.Text;

public class activityInital extends AppCompatActivity {

    private EditText valueOne;
    private EditText valueTwo;
    private EditText valueThree;
    private boolean begin = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inital);

        valueOne = findViewById( R.id.valueOneEditText );
        valueTwo = findViewById( R.id.valueTwoEditText );
        valueThree = findViewById( R.id.valueThreeEditText );

        valueOne.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                errorPrompt( valueOne );
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        valueTwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                errorPrompt( valueTwo );
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        valueThree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                errorPrompt( valueThree );
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void changeActivity( View v ){
        Intent myIntent = new Intent( this, MainActivity.class );
        myIntent.putExtra( "valueOne" , valueOne.getText().toString() );
        myIntent.putExtra( "valueTwo" , valueTwo.getText().toString() );
        myIntent.putExtra( "valueThree" , valueThree.getText().toString() );
        if ( Integer.parseInt( valueOne.getText().toString() ) > 39 || Integer.parseInt( valueTwo.getText().toString() ) > 39 ||
                Integer.parseInt( valueThree.getText().toString() ) > 39){
            begin = false;
        }
        else {
            begin = true;
        }
        if ( begin ) {
            startActivityForResult( myIntent , 1 );
        }
    }

    public void errorPrompt( View test ) {
        EditText prompt = (EditText) test;
        try {
            if (Integer.parseInt(prompt.getText().toString()) > 39) {
                prompt.setError("Number has to be below 40");
            }
        }
        catch ( Exception e ) {

        }
    }

}
