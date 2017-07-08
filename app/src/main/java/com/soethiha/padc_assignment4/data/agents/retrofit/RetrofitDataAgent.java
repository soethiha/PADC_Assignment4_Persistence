package com.soethiha.padc_assignment4.data.agents.retrofit;

import android.util.Log;

import com.soethiha.padc_assignment4.data.agents.RestaurantDataAgent;
import com.soethiha.padc_assignment4.data.responses.RestaurantListResponse;
import com.soethiha.padc_assignment4.events.DataEvent;
import com.soethiha.padc_assignment4.utils.CommonInstances;
import com.soethiha.padc_assignment4.utils.RestaurantConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Soe Thiha on 22-Jun-17.
 */

public class RetrofitDataAgent implements RestaurantDataAgent {

    private static RetrofitDataAgent objInstance;

    private final RestaurantApi theApi;

    private static final String TAG = "RetrofitDataAgent";

    private RetrofitDataAgent() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestaurantConstants.RESTAURANT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(CommonInstances.getGsonInstance()))
                .client(okHttpClient)
                .build();

        theApi = retrofit.create(RestaurantApi.class);
    }

    public static RetrofitDataAgent getObjInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgent();
        }
        return objInstance;
    }

    @Override
    public void loadRestaurants() {
        Call<RestaurantListResponse> loadRestaurantCall = theApi.loadRestaurants();
        loadRestaurantCall.enqueue(new Callback<RestaurantListResponse>() {
            @Override
            public void onResponse(Call<RestaurantListResponse> call, Response<RestaurantListResponse> response) {
                RestaurantListResponse restaurantListResponse = response.body();
                if (restaurantListResponse == null) {
                    Log.d(TAG, "onResponse: null");
                } else {
                    Log.d(TAG, "onResponse: " + restaurantListResponse.getCode());
                    Log.d(TAG, "onResponse: " + restaurantListResponse.getMessage());
                    Log.d(TAG, "onResponse: " + restaurantListResponse.getTimestamp());
                    Log.d(TAG, "onResponse: " + restaurantListResponse.getRestaurantList().size());

                    // Boradcast with EventBus
                    EventBus.getDefault().post(new DataEvent.RestaurantDataLoadedEvent(restaurantListResponse.getRestaurantList()));
                }
            }

            @Override
            public void onFailure(Call<RestaurantListResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }
}
