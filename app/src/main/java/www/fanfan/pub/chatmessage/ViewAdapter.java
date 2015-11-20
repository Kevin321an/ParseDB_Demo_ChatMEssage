package www.fanfan.pub.chatmessage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.List;

/**
 * Created by FM on 11/18/2015.
 */
public class ViewAdapter extends ArrayAdapter<ParseObject> {


    private int mResource;
    private Context mContext;

    public ViewAdapter(Activity context, int resource, List<ParseObject> chartHistory) {
        super(context, resource, chartHistory);
        this.mResource = resource;
        this.mContext = context;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LinearLayout layout;
        // Get the data item for this position
        ParseObject chartHistory = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        TextView chartText;
        TextView nameText;

        if (view == null) {
            layout = new LinearLayout(getContext());
            ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(mResource, layout, true);
            chartText = (TextView)layout.findViewById(R.id.list_item_chart_textview);
            nameText = (TextView)layout.findViewById(R.id.list_item_name_textview);


            layout.setTag(chartText);
        } else {
            layout = (LinearLayout) view;

            chartText = (TextView) view.getTag();
            nameText = (TextView) view.getTag();
        }
        String str = chartHistory.getString(MainActivity.PARSE_OBJECT_COL_KEY_MESSAGE); //single message
        String color = chartHistory.getString(MainActivity.PARSE_OBJECT_COL_KEY_COLOR);
        String name = chartHistory.getString(MainActivity.PARSE_OBJECT_COL_KEY_USER);
        if (name!=null) nameText.setText(name+":");
        if ( str != null){
            chartText.setText(str);
        }
        if (color.substring(0,1).equals("#"))
        {
            chartText.setTextColor(Color.parseColor(color));
            nameText.setTextColor(Color.parseColor(color));
        }

        return layout;
    }


}
