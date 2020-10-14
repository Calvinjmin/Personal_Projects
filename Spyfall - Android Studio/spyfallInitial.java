package com.example.calvinjmin.spyfall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class spyfallInitial extends AppCompatActivity {

    private boolean begin = false;
    private Integer numberPlayers = 0;
    private EditText numOfPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spyfall_initial);
        numOfPlayers = findViewById( R.id.numOfPlayers );

        numOfPlayers.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                errorPrompt( numOfPlayers );
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    public void errorPrompt( View v ) {
        EditText prompt = (EditText) v ;
        try {
            if( Integer.parseInt(prompt.getText().toString()) < 3 || Integer.parseInt(prompt.getText().toString()) > 8 ) {
                prompt.setError("Number has to be between 3-8");
                begin = false;
            }
            else {
                begin = true;
            }
        }
        catch ( Exception e ) {

        }
    }

    public void switchToMain(View v ) {
        numberPlayers = Integer.parseInt( numOfPlayers.getText().toString() );

        Intent myIntent = new Intent( this , spyfallMain.class );

        if ( begin ) {
            myIntent.putExtra( "numberPlayers" , numberPlayers );
            startActivity( myIntent );
        }
    }
}
