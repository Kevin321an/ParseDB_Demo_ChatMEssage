package www.fanfan.pub.chatmessage;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    final static private String PARSE_OBJECT="ChatMessage";
    public final static  String PARSE_OBJECT_COL_KEY_USER="user";
    public final static  String PARSE_OBJECT_COL_KEY_COLOR="color";
    public final static  String PARSE_OBJECT_COL_KEY_MESSAGE="message";
    final static private String COLOR_KEVIN="#FFC107";


    private  ViewAdapter mChartHistory;
    private List<ParseObject> chartHistory;
    private ListView chartBox;
    private int chartCounter=0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "KAimpzFF8jSa1QSJb0NSnMI4IHkCy4ppxHfEDJh7", "3DGoEbsicojMc74aKsl243DWQ6ooPSkzWaRQAkvP");

        final EditText inputBox = (EditText) findViewById(R.id.inputBox);
        chartBox = (ListView)findViewById(R.id.char_box);
        getMessage();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = inputBox.getText().toString();
                inputBox.setText("");
                sendMessage(str);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        getMessage();
                    }
                }, 500);
            }
        });


    }

    //push the message to the cloud
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
        postMessage.pinInBackground();

    }
    //pull message from cloud
    public void getMessage(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(PARSE_OBJECT);
        //query.fromLocalDatastore();
        query.whereNotEqualTo(PARSE_OBJECT_COL_KEY_MESSAGE, "");
        query.addAscendingOrder("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> message, ParseException e) {
                if (e == null) {
                    chartHistory = message;
                    for (int i = 0; i < message.size(); i++) {
                        ParseObject temp = message.get(i);
                        String thisMessage = temp.getString(PARSE_OBJECT_COL_KEY_MESSAGE);
                        Log.d("message", "Retrieved " + thisMessage);
                    }

                    if (chartHistory!=null || chartHistory.size()!=chartCounter)
                    {
                        showMessage();
                    }
                    chartCounter = chartHistory.size();
                } else {
                    Log.d("message", "Error: " + e.getMessage());
                }
            }
        });
    }
    // show message on the screen
    public void showMessage(){
        if (chartHistory != null){
            mChartHistory = new ViewAdapter(this, R.layout.list_chartivew,chartHistory);
            chartBox.setAdapter(mChartHistory);
        }
    }

}
