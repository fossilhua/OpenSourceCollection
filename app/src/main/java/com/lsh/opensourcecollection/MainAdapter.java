package com.lsh.opensourcecollection;

import android.content.Context;
import android.widget.TextView;

import com.lsh.lshrecyclerviewadapter.BaseAdapter;
import com.lsh.lshrecyclerviewadapter.BaseViewHolder;

import java.util.List;

/**
 *
 * Created by hua on 2016/10/17.
 */

public class MainAdapter extends BaseAdapter<String> {
    public MainAdapter(Context mContext, List<String> list) {
        super(mContext, list, android.R.layout.simple_list_item_1);
    }

    @Override
    public void onBindData(BaseViewHolder holder, List<String> data, int position) {
        TextView tv = holder.retrieveView(android.R.id.text1);
        tv.setBackgroundColor(mContext.getResources().getColor(android.R.color.white));
        tv.setText(data.get(position));
    }
}
