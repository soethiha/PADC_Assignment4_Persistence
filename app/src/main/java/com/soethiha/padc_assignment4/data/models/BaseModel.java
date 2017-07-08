package com.soethiha.padc_assignment4.data.models;

import com.soethiha.padc_assignment4.data.agents.RestaurantDataAgent;
import com.soethiha.padc_assignment4.data.agents.retrofit.RetrofitDataAgent;

public class BaseModel {

    protected RestaurantDataAgent dataAgent;

    public BaseModel() {
        dataAgent = RetrofitDataAgent.getObjInstance();
    }
}
