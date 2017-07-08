package com.soethiha.padc_assignment4.events;

import com.soethiha.padc_assignment4.data.vos.RestaurantVO;

import java.util.List;

/**
 * Created by Soe Thiha on 22-Jun-17.
 */

public class DataEvent {

    public static class RestaurantDataLoadedEvent {

        private List<RestaurantVO> restaurantList;

        public RestaurantDataLoadedEvent(List<RestaurantVO> restaurantList) {
            this.restaurantList = restaurantList;
        }

        public List<RestaurantVO> getRestaurantList() {
            return restaurantList;
        }
    }
}
