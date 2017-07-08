package com.soethiha.padc_assignment4.data.agents.retrofit;

import com.soethiha.padc_assignment4.data.responses.RestaurantListResponse;
import com.soethiha.padc_assignment4.utils.RestaurantConstants;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestaurantApi {

    @GET(RestaurantConstants.API_GET_RESTAURANT_LIST)
    Call<RestaurantListResponse> loadRestaurants();

}
