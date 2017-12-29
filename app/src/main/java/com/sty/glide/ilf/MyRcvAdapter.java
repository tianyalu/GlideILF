package com.sty.glide.ilf;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Steven.T on 2017/12/28/0028.
 */

public class MyRcvAdapter extends RecyclerView.Adapter<MyRcvAdapter.MyViewHolder> {
    private List<String> mDatas;
    private Context mContext;
    private LayoutInflater mInflater;

    public MyRcvAdapter(Context context, List<String> mDatas){
        this.mDatas = mDatas;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(R.layout.item_recycler_view, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText((position + 1) + "");
        String url = mDatas.get(position);
        Glide.with(mContext).load(url).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        if(mDatas != null){
            return mDatas.size();
        }else {
            return 0;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_rcv_item);
            tv = itemView.findViewById(R.id.tv_count);
        }
    }
}
