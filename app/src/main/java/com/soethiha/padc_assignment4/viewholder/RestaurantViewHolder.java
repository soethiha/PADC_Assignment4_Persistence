package com.soethiha.padc_assignment4.viewholder;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.soethiha.padc_assignment4.R;
import com.soethiha.padc_assignment4.data.vos.RestaurantVO;

import butterknife.BindView;

public class RestaurantViewHolder extends BaseViewHolder<RestaurantVO> {

    @BindView(R.id.rb_restaurant_rating)
    RatingBar rbRating;

    @BindView(R.id.tv_restaurant_rating_count)
    TextView tvRatingCount;

    @BindView(R.id.tv_restaurant_title)
    TextView tvTitle;

    @BindView(R.id.tv_restaurant_address)
    TextView tvAddress;

    @BindView(R.id.tv_deliver_time)
    TextView tvDeliverTime;

    @BindView(R.id.tv_ad)
    TextView tvAD;

    public RestaurantViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(RestaurantVO data, int position) {

        boolean isAD = data.isAd();

        rbRating.setRating((float) data.getAvgRatingVal());
        tvRatingCount.setText("(" + data.getTotalRatingCnt() + ")");
        tvTitle.setText(data.getTitle());

        if (data.getAddrShort()!= null) {
            tvAddress.setText("(" + data.getAddrShort() + ")");
        }

        tvDeliverTime.setText("delivers in " + data.getLeadTimeInMin() + " min.");

        if (isAD) {
            tvAD.setVisibility(View.VISIBLE);
        } else {
            tvAD.setVisibility(View.INVISIBLE);
        }
    }
}
