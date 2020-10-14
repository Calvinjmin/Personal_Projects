package com.example.calvinjmin.spyfall;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class spyfallMain extends AppCompatActivity {

    private int numOfPlayers = 0;
    private Button show;
    private Button nextPlayer;
    private Button done;
    private TextView player;
    private TextView role;
    private int randomGameTheme;
    private String[] gameRoles;
    private String gameThemeName;
    private int iteration = 0;
    private int spyPlayer = 0;

    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spyfall_main);
        Context context = getApplicationContext();

        show = findViewById( R.id.show );
        nextPlayer = findViewById( R.id.nextPlayer );
        done = findViewById( R.id.done );
        player = findViewById( R.id.player );
        role = findViewById( R.id.role );

        done.setVisibility( View.INVISIBLE );
        nextPlayer.setVisibility( View.INVISIBLE );
        show.setVisibility( View.VISIBLE );

        numOfPlayers = getIntent().getIntExtra("numberPlayers", 0);
        String[] gameThemesNames = getResources().getStringArray( R.array.gameThemes );

        Map<String, String []> gameThemes = new TreeMap<String, String[]>();
        iteration = 0;

        gameThemes.put( gameThemesNames[0] , getResources().getStringArray(R.array.theaterRoles ) );
        gameThemes.put( gameThemesNames[1] , getResources().getStringArray(R.array.circusRoles ));
        gameThemes.put( gameThemesNames[2] , getResources().getStringArray(R.array.pirateRoles ));
        gameThemes.put( gameThemesNames[3] , getResources().getStringArray(R.array.submarineRoles ));
        gameThemes.put( gameThemesNames[4] , getResources().getStringArray(R.array.movieRoles ));

        randomGameTheme = (int)(Math.random() * 5);
        gameThemeName = gameThemesNames[randomGameTheme];
        gameRoles = assignSpy( gameThemes.get( gameThemesNames[ randomGameTheme ] ) );

        //ArrayList<String> temp = (ArrayList<String>) Arrays.asList( gameRoles );
        //Collections.shuffle( temp );
        //gameRoles = (String[]) temp.toArray();

    }

    public String[] assignSpy( String[] roles ) {
        spyPlayer = (int)(Math.random() * numOfPlayers );
        roles[spyPlayer] = "???";
        return roles;
    }

    public void showRole( View v ) {
        nextPlayer.setVisibility( View.VISIBLE );
        show.setVisibility( View.INVISIBLE );
        try {
            if ( iteration > numOfPlayers - 1 ) {
                Toast.makeText( this , "Done", Toast.LENGTH_SHORT ).show();
                //Switch intent to the actual game - Timer
            }
            else {
                if (iteration == spyPlayer) {
                    player.setText("Player " + (iteration + 1));
                    role.setText("Role: " + gameRoles[iteration]);
                    iteration++;
                } else {
                    player.setText("Player " + (iteration + 1));
                    role.setText("Theme: " + gameThemeName + "\nRole: " + gameRoles[iteration]);
                    iteration++;
                }
            }

        }
        catch ( Exception e ) {

        }
    }

    public void next( View v )
    {
        nextPlayer.setVisibility( View.INVISIBLE );
        show.setVisibility( View.VISIBLE );
        player.setText("Player Unknown");
        role.setText("Classified Information");
        if ( iteration > numOfPlayers - 1 ) {
            nextPlayer.setVisibility( View.INVISIBLE );
            show.setVisibility( View.INVISIBLE);
            done.setVisibility( View.VISIBLE );
        }
    }

    public void nextActivity( View v ) {
        Toast.makeText( this , "Done", Toast.LENGTH_SHORT ).show();
        //Switch intent to the actual game - Timer
    }

}
