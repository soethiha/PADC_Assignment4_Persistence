package com.soethiha.padc_assignment4.data.responses;

import com.google.gson.annotations.SerializedName;
import com.soethiha.padc_assignment4.data.vos.RestaurantVO;

import java.util.ArrayList;

public class RestaurantListResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("timestamp")
    private String timestamp;

    @SerializedName("restaurants")
    private ArrayList<RestaurantVO> restaurantList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public ArrayList<RestaurantVO> getRestaurantList() {
        return restaurantList;
    }
}
