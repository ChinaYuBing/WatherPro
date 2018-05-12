package demo.com.watherpro.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import demo.com.watherpro.R;
import demo.com.watherpro.pojo.MyForeCast;
import demo.com.watherpro.pojo.MyYesterDay;

/**
 * Created by Shinelon on 2017/12/6.
 */

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<MyForeCast> myForeCastList;
    private MyYesterDay myYesterDay;

    public MyAdapter(Context context, List<MyForeCast> myForeCastList, MyYesterDay myYesterDay) {
        this.context = context;
        this.myForeCastList = myForeCastList;
        this.myYesterDay = myYesterDay;
    }

    @Override
    public int getCount() {
        return myForeCastList.size();
    }

    @Override
    public Object getItem(int position) {
        return myForeCastList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = View.inflate(context, R.layout.item, null);

        TextView textDay = convertView.findViewById(R.id.textDay);
        TextView textDate = convertView.findViewById(R.id.textDate);
        TextView textType1 = convertView.findViewById(R.id.textType1);
        TextView textMaxHigh = convertView.findViewById(R.id.maxHigh);
        TextView textMinHigh = convertView.findViewById(R.id.minHigh);
        ImageView imageView = convertView.findViewById(R.id.textImage);

        if (position == 0) {
            textDay.setText("今天");
        } else if (position == 1) {
            textDay.setText("明天");
        } else {
            textDay.setText(myForeCastList.get(position).getDate());
        }

        textDate.setText(myForeCastList.get(position).getDate1());
        textType1.setText(myForeCastList.get(position).getType());
        textMaxHigh.setText(myForeCastList.get(position).getHigh());
        textMinHigh.setText(myForeCastList.get(position).getLow());

        if (textType1.getText().equals("多云")) {
            imageView.setImageResource(R.mipmap.cloudy_ic2);
        } else {
            imageView.setImageResource(R.mipmap.sun_ic2);
        }


        return convertView;
    }
}
