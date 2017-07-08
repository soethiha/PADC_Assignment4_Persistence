package com.soethiha.padc_assignment4.data.models;

public class RestaurantModel extends BaseModel {

    private static RestaurantModel objInstance;

    private RestaurantModel() {
        super();
    }

    public static RestaurantModel getInstance() {
        if (objInstance == null) {
            objInstance = new RestaurantModel();
        }
        return objInstance;
    }

    public void loadRestaurant() {
        dataAgent.loadRestaurants();
    }
}
