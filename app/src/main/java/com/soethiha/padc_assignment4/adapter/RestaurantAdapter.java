package com.soethiha.padc_assignment4.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.soethiha.padc_assignment4.R;
import com.soethiha.padc_assignment4.data.vos.RestaurantVO;
import com.soethiha.padc_assignment4.viewholder.RestaurantViewHolder;

public class RestaurantAdapter extends BaseRecyclerAdapter<RestaurantViewHolder, RestaurantVO> {

    public RestaurantAdapter(Context context) {
        super(context);
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.restaurant_item, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        holder.bindData(mData.get(position), position);
    }
}
