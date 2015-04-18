package edu.washington.ruokua.lifecounter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    public final int DEFAULT_NUM_PLAYER = 2;
    public final int DEFAULT_LIFE = 20;

    private ArrayList<Integer> playerInfo = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {

            for (int i = 0; i < DEFAULT_NUM_PLAYER; i++) {
                 playerInfo.add(DEFAULT_LIFE);
                 //Get the layout
                 String playerName = "player" + (i + 1);
                 int playerLayoutID = getResources().getIdentifier(playerName, "id", getPackageName());
                 LinearLayout layout = (LinearLayout) findViewById(playerLayoutID);
                 //Set the Name label
                 TextView nameView = (TextView) layout.findViewById(R.id.player_name);
                 nameView.setText(playerName + ": ");
                 //Set the health label
                 TextView healthView = (TextView) layout.findViewById(R.id.player_life);
                 healthView.setText("" + playerInfo.get(i)); //Set the health to value from arraylist

            }

       }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
