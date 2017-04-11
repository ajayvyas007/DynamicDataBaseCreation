package com.ajayvyas.dynamdb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;


import com.ajayvyas.dynamdb.R;
import com.ajayvyas.dynamdb.model.data.DataOfPhpMySql;

import java.util.List;

/**
 * Created by ajay on 31/3/17.
 */

public class SwipeListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<DataOfPhpMySql> mDataList;
    private DataOfPhpMySql items;

    public SwipeListAdapter(Context ctx, List<DataOfPhpMySql> movieList) {
        this.mDataList = movieList;
        this.mContext=ctx;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int location) {
        return mDataList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (mInflater == null)
            mInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = mInflater.inflate(R.layout.list_row, null);

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView contactName = (TextView) convertView.findViewById(R.id.emp_id);
        RadioButton male = (RadioButton)convertView.findViewById(R.id.male_radio_button);
        RadioButton female = (RadioButton)convertView.findViewById(R.id.female_radio_button);
        items=mDataList.get(position);
        name.setText(items.getmName());
        contactName.setText(items.getmEmployeeId());
        switch (items.getmSex().toString())
        {
            case "male":
                male.setChecked(true);
                female.setChecked(false);
                male.setClickable(false);
                female.setClickable(false);
                break;
            case "female":
                male.setChecked(false);
                female.setChecked(true);
                male.setClickable(false);
                female.setClickable(false);
                break;
        }
        //Toast.makeText(mContext,items.getmName().toString(),Toast.LENGTH_LONG).show();

        return convertView;
    }
}
