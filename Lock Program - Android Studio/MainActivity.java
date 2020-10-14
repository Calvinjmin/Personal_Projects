package com.example.calvinjmin.lockprogram;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ImageView comboLock;
    private ImageView background;
    private ImageView finalImage;
    private SeekBar seekBar;
    private TextView value;
    private ArrayList<String> inputValues = new ArrayList<String>();
    private TextView finalComboText;
    private int pastValue = 0;
    private int tracker = pastValue;
    private int iteration = 0;
    private boolean calculate = true;
    private HashMap<Integer,Integer> values = new HashMap<Integer, Integer>();
    private int trackerNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comboLock = findViewById( R.id.comboInput);
        seekBar = findViewById( R.id.seekBar );
        finalComboText = findViewById( R.id.finalCombo);
        value = findViewById( R.id.value );
        finalImage = findViewById( R.id.finalImage );

        value.setText("Current Lock Combo: ");

        seekBar.setMax(120);
        seekBar.setProgress(60);

        calculation();

        int valueOne = Integer.parseInt( getIntent().getStringExtra("valueOne" ));
        int valueTwo = Integer.parseInt( getIntent().getStringExtra("valueTwo" ));
        int valueThree = Integer.parseInt( getIntent().getStringExtra("valueThree" ));

        finalComboText.setText( valueOne + ", " + valueTwo + ", " + valueThree );

        //comboLock.setVisibility( View.VISIBLE );
        //background.setVisibility( View.VISIBLE );
        finalImage.setVisibility( View.INVISIBLE );

        comboLock.animate().setDuration( 0 );

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                comboLock.animate().rotation( ( seekBar.getProgress() * 9 ) ).start();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data ) {


    }

    public void inputButton( View v ) {
        value = findViewById( R.id.value );
        seekBar = findViewById( R.id.seekBar );

        if ( seekBar.getProgress() * 9 > pastValue && iteration == 0 ){
            inputValues.add( trackerNum, values.get(seekBar.getProgress() * 9) + "" );
            value.setText( inputValues.get(trackerNum) );
            pastValue = seekBar.getProgress() * 9;
            trackerNum++;
            iteration++;
        }
        else if ( seekBar.getProgress() * 9 < pastValue && iteration == 1 ) {
            inputValues.add( trackerNum, values.get(seekBar.getProgress() * 9) + "" );
            value.setText( value.getText().toString() + ", " +  inputValues.get(trackerNum ) );
            pastValue = seekBar.getProgress() * 9;
            trackerNum++;
            iteration++;
        }
        else if ( seekBar.getProgress() * 9 > pastValue && iteration == 2 ) {
            inputValues.add( trackerNum, values.get(seekBar.getProgress() * 9) + "" );
            value.setText( value.getText().toString() + ", " + inputValues.get(trackerNum) );
            trackerNum++;
            iteration++;
        }
        else if ( iteration > 2 ) {
            Toast.makeText( this , "Cannot Input Another Value" , Toast.LENGTH_LONG ).show();
        }
        else {
            Toast.makeText( this , "Cannot Input that Value" , Toast.LENGTH_LONG ).show();
        }

    }

    public void submitButton( View v ) {
        Intent finalIntent = new Intent( this, finishActivityFinal.class );
        comboLock = findViewById( R.id.comboInput );
        background = findViewById( R.id.background );
        finalComboText = findViewById( R.id.finalCombo);
        value = findViewById( R.id.value );
        seekBar = findViewById( R.id.seekBar );

           if (value.getText().toString().equals(finalComboText.getText().toString())) {
               //      finalIntent.putExtra( "test" , "hello, this is a test"  );
               //      startActivityForResult( finalIntent , 100 );
               comboLock.setVisibility( View.GONE );
               background.setVisibility( View.GONE );
               seekBar.setVisibility( View.GONE );
               finalComboText.setText( "" );
               value.setText( "" );
               finalImage.setVisibility( View.VISIBLE );
               Toast.makeText( this , "Congratulations!! You unlocked the code!" , Toast.LENGTH_LONG ).show();

           }
           else {
               Toast.makeText( this , "Not Correct" , Toast.LENGTH_LONG ).show();
               backspace( comboLock );
           }

    }




    public boolean onTouchEvent( MotionEvent e ) {

        return true;
    }

    public void calculation() {
        int combo = 0;
        for ( int j = 0; j < 1089; j+=9 ) {
            if ( combo == 0 ) {
                values.put( j , combo );
                combo = 39;
            }
            else {
                values.put( j , combo );
                combo--;
            }
        }
    }

    public void backspace( View v ) {
        value = findViewById( R.id.value );
        /*
        int loc = value.getText().toString().lastIndexOf( ',' );
        if ( loc < 0 ) {
            value.setText( "" );
            iteration = iteration - 1;
            pastValue = tracker;
        }
        else {
            inputValues.remove( trackerNum - 1 );
                value.setText(inputValues.get(0));
                for ( int i = 1; i < trackerNum - 1; i++ ){
                    value.setText( ", " + inputValues.get(i) );
                }
            trackerNum--;
            iteration = iteration - 1;
            pastValue = tracker;
        }
        */
        iteration = 0;
        value.setText( "" );
        pastValue = 0;

    }
}

