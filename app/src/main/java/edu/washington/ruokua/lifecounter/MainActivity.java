package edu.washington.ruokua.lifecounter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    public final int DEFAULT_NUM_PLAYER = 2;
    public final int DEFAULT_LIFE = 20;
    public final int MAX_CAPACITY = 8;
    public int leftPlayerCapacity = MAX_CAPACITY - DEFAULT_NUM_PLAYER;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {

            for (int i = 0; i < DEFAULT_NUM_PLAYER; i++) {
                //Get the layout
                String playerName = "player" + (i + 1);
                int playerLayoutID = getResources().getIdentifier(playerName, "id", getPackageName());
                LinearLayout layout = (LinearLayout) findViewById(playerLayoutID);
                layout.setTag(i + 1);
                //Set the Name label
                TextView nameView = (TextView) layout.findViewById(R.id.player_name);
                nameView.setText(playerName + ": ");
                //Set the health label
                TextView healthView = (TextView) layout.findViewById(R.id.player_life);
                healthView.setText("" + DEFAULT_LIFE); //Set the health to value from arraylist

            }

       }
    }


    public void changeLifePoint(View v) {
        LinearLayout player = (LinearLayout) v.getParent(); //Player view
        TextView playerName = (TextView) player.findViewById(R.id.player_name);
        TextView health = (TextView) player.findViewById(R.id.player_life); //Textview of the health
        int currHealth = Integer.parseInt(health.getText().toString());

        switch (v.getTag().toString()) {
            case "add_one": currHealth += 1;
                break;
            case "add_five": currHealth += 5;
                break;
            case "subtract_one": currHealth -= 1;
                break;
            case "subtract_five": currHealth -= 5;
                break;
        }


        if(currHealth <= 0) {

            LinearLayout playGround = (LinearLayout)player.getParent();
            TextView loseMessage = new TextView(this);

            loseMessage.setText("Player " + player.getTag().toString() + " " + "LOSES");
            Button add = (Button) player.findViewById(R.id.add);
            add.setEnabled(false);
            Button subtract = (Button)player.findViewById(R.id.subtract);
            subtract.setEnabled(false);
            Button addFive = (Button) player.findViewById(R.id.add_five);
            addFive.setEnabled(false);
            Button subtractFive = (Button) player.findViewById(R.id.subtract_five);
            subtractFive.setEnabled(false);

            playGround.addView(loseMessage);


        }
        health.setText("" + currHealth);
    }

    public void addPlayer(View v) {
        if (leftPlayerCapacity > 0) {
            leftPlayerCapacity -= 1;
            LinearLayout parent = (LinearLayout) v.getParent();
            String playerName = "player" + (MAX_CAPACITY - leftPlayerCapacity);
            Log.i("X", playerName);
            int viewStubIntID = parent.getResources().getIdentifier(playerName, "id", getPackageName());

            //find viewstub to inflate
            ViewStub stub = (ViewStub) parent.findViewById(viewStubIntID);
            LinearLayout newPlayer = (LinearLayout) stub.inflate();
            newPlayer.setTag(MAX_CAPACITY - leftPlayerCapacity);
            //Set new player name
            TextView nameView = (TextView) newPlayer.findViewById(R.id.player_name);
            nameView.setText(playerName + ": " );

            //Set new player health
            TextView healthView = (TextView) newPlayer.findViewById(R.id.player_life);
            healthView.setText("" + DEFAULT_LIFE);
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
