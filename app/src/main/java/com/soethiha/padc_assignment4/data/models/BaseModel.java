package com.soethiha.padc_assignment4.data.models;

import android.content.Context;
import android.content.ContextWrapper;

import com.soethiha.padc_assignment4.data.agents.RestaurantDataAgent;
import com.soethiha.padc_assignment4.data.agents.retrofit.RetrofitDataAgent;

import org.greenrobot.eventbus.EventBus;

public class BaseModel extends ContextWrapper {

    protected RestaurantDataAgent dataAgent;

    public BaseModel(Context base) {
        super(base);
        dataAgent = RetrofitDataAgent.getObjInstance();

        // Register the EventBus
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }
}
