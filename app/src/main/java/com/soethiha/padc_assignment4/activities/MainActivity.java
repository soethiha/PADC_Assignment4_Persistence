package com.soethiha.padc_assignment4.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.soethiha.padc_assignment4.R;
import com.soethiha.padc_assignment4.RestaurantApp;
import com.soethiha.padc_assignment4.adapter.RestaurantAdapter;
import com.soethiha.padc_assignment4.data.models.RestaurantModel;
import com.soethiha.padc_assignment4.data.persistence.RestaurantContract;
import com.soethiha.padc_assignment4.data.vos.RestaurantVO;
import com.soethiha.padc_assignment4.utils.RestaurantConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.rv_restaurant_list)
    RecyclerView rvRestaurant;

    private RestaurantAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Load the Restaurant data from model
        RestaurantModel.getInstance(getApplicationContext()).loadRestaurant();

        mAdapter = new RestaurantAdapter(this);
        rvRestaurant.setLayoutManager(new LinearLayoutManager(this));
        rvRestaurant.setAdapter(mAdapter);

        // Initialize Loader
        getSupportLoaderManager().initLoader(RestaurantConstants.RESTAURANT_LIST_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getApplicationContext(),
                RestaurantContract.RestaurantEntry.CONTENT_URI,
                null,
                null,
                null,
                RestaurantContract.RestaurantEntry.COLUMN_TITLE + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<RestaurantVO> restaurantList = new ArrayList<>();

        if (data != null && data.moveToFirst()) {
            do {
                RestaurantVO restaurant = RestaurantVO.parseFromCursor(data);
                restaurant.setTags(RestaurantVO.loadRestaurantTagsByTitle(getApplicationContext(), restaurant.getTitle()));
                restaurantList.add(restaurant);
            } while(data.moveToNext());
        }

        Log.d(RestaurantApp.TAG, "onLoadFinished: " + restaurantList.size());

        mAdapter.setNewData(restaurantList);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
