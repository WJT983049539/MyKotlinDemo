package com.rcdz.mykotlindemo.model.adapter;

import android.content.Context;


import com.rcdz.mykotlindemo.R;
import com.rcdz.mykotlindemo.model.bean.MyContacts;

import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/31 13:55
 */
public class TxlAdapter extends CommonRecyclerAdapter<MyContacts>{

    public TxlAdapter(Context context, List<MyContacts> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(CommonViewHolder holder, Context context, MyContacts item) {
        holder.setText(R.id.name,item.getName());
        holder.setText(R.id.number,item.getPhone());
    }
}
