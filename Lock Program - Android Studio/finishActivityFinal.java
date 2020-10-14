package com.example.calvinjmin.lockprogram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class finishActivityFinal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_final);

        Toast.makeText( this , getIntent().getStringExtra("test" ) , Toast.LENGTH_LONG ).show();
    }
}
