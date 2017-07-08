package com.soethiha.padc_assignment4.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.soethiha.padc_assignment4.R;
import com.soethiha.padc_assignment4.adapter.RestaurantAdapter;
import com.soethiha.padc_assignment4.data.models.RestaurantModel;
import com.soethiha.padc_assignment4.data.vos.RestaurantVO;
import com.soethiha.padc_assignment4.events.DataEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_restaurant_list)
    RecyclerView rvRestaurant;

    private RestaurantAdapter mAdapter;

    private List<RestaurantVO> mRestaurantList;

    @Override
    protected void onStart() {
        super.onStart();

        // Register the EventBus
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Unregister the EventBus
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RestaurantModel.getInstance().loadRestaurant();

        mRestaurantList = new ArrayList<>();
        mAdapter = new RestaurantAdapter(this);
        rvRestaurant.setLayoutManager(new LinearLayoutManager(this));
        rvRestaurant.setAdapter(mAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveRestaurantList(DataEvent.RestaurantDataLoadedEvent event) {
        mRestaurantList = event.getRestaurantList();
        for (RestaurantVO restaurant: mRestaurantList) {
            Log.d("MainActivity", "receiveRestaurantList: " + restaurant.getTitle());
        }
        mAdapter.setNewData(mRestaurantList);
    }
}
