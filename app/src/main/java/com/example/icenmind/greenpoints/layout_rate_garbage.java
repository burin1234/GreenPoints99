package com.example.icenmind.greenpoints;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class layout_rate_garbage extends BaseAdapter {
    Context mContext;
    List<String> detail;
    List<String> money;

    public layout_rate_garbage(Context context, List<String> detail, List<String> money) {
        this.mContext= context;
        this.detail = detail;
        this.money = money;
    }

    public int getCount() {
        return detail.size();
    }
    public Object getItem(int position) {
        return null;
    }
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
        view = mInflater.inflate(R.layout.activity_layout_rate_garbage, parent, false);

        TextView t_name = (TextView)view.findViewById(R.id.name);
        t_name.setText(detail.get(position));

        TextView t_money = (TextView)view.findViewById(R.id.unit);
        t_money.setText(""+money.get(position));

        return view;
    }
}
