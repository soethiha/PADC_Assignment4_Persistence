package com.soethiha.padc_assignment4.data.models;

import android.content.Context;
import android.util.Log;

import com.soethiha.padc_assignment4.data.vos.RestaurantVO;
import com.soethiha.padc_assignment4.events.DataEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import static com.soethiha.padc_assignment4.RestaurantApp.TAG;

public class RestaurantModel extends BaseModel {

    private static RestaurantModel objInstance;

    private List<RestaurantVO> mRestaurantList;

    private RestaurantModel(Context context) {
        super(context);
    }

    public static RestaurantModel getInstance(Context context) {
        if (objInstance == null) {
            objInstance = new RestaurantModel(context);
        }
        return objInstance;
    }

    public void loadRestaurant() {
        dataAgent.loadRestaurants();
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void receiveRestaurantList(DataEvent.RestaurantDataLoadedEvent event) {
        // Receive data from network(by Retrofit) through EventBus
        mRestaurantList = event.getRestaurantList();

        Log.d(TAG, "receiveRestaurantList: from EventBus" + mRestaurantList.size());

        // Save the data in Persistence Layer
        RestaurantVO.saveRestaurants(getApplicationContext(), mRestaurantList);
    }
}
