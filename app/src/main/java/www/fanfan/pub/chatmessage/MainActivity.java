package www.fanfan.pub.chatmessage;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;

public class MainActivity extends AppCompatActivity {

    final static private String PARSE_OBJECT="ChatMessage";
    final static private String PARSE_OBJECT_COL_KEY_USER="user";
    final static private String PARSE_OBJECT_COL_KEY_COLOR="color";
    final static private String PARSE_OBJECT_COL_KEY_MESSAGE="message";
    final static private String COLOR_KEVIN="black";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "8ejmNKzf0l63sdI18MuukakvLiWD6L2xtpQhdGzi", "NuxMakByXfaJ6VXlDRXOGPrK2fkxFUTEdlN6K592");

        final EditText inputBox = (EditText) findViewById(R.id.inputBox);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = inputBox.getText().toString();
                sendMessage(str);
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


 /*   class MyTimerTask extends TimerTask {
        public void run() {
            System.out.println("works");
            ParseAnalytics.trackAppOpenedInBackground(getIntent());
            ParseObject  GpsCoordinate = new ParseObject("GpsCoordinate");
            Double lat = MainActivity.la;
            Double lon = MainActivity.lng;
            GpsCoordinate.put("latitud", lat);
            GpsCoordinate.put("longtitude", lon);
            GpsCoordinate.saveInBackground();
        }




    }*/

    public void sendMessage(String str){

        System.out.println("works");
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
        ParseObject  postMessage = new ParseObject(PARSE_OBJECT);
        String usr = "Kevin";
        String color = COLOR_KEVIN;
        String message = str;
        postMessage.put(PARSE_OBJECT_COL_KEY_USER,usr);
        postMessage.put(PARSE_OBJECT_COL_KEY_COLOR,color);
        postMessage.put(PARSE_OBJECT_COL_KEY_MESSAGE,message);
        postMessage.saveInBackground();
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
