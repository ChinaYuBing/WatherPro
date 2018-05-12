package demo.com.watherpro.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import demo.com.watherpro.R;
import demo.com.watherpro.pojo.GridePo;

/**
 * Created by Shinelon on 2017/12/6.
 */

public class GrideAdapter extends BaseAdapter {

    private List<GridePo> gridePoList;
    private Context context;

    public GrideAdapter(Context context, List<GridePo> gridePoList) {
        this.gridePoList = gridePoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return gridePoList.size();
    }

    @Override
    public Object getItem(int position) {
        return gridePoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.gride_view_item, null);

        ImageView imageView = convertView.findViewById(R.id.imageView1);
        TextView textView1 = convertView.findViewById(R.id.textView1);
        TextView textView2 = convertView.findViewById(R.id.textView2);

        imageView.setImageResource(gridePoList.get(position).getImage());
        textView1.setText(gridePoList.get(position).getName());
        textView2.setText(gridePoList.get(position).getContent());


        return convertView;
    }
}
