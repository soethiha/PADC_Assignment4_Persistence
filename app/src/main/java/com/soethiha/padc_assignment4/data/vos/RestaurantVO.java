package com.soethiha.padc_assignment4.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.soethiha.padc_assignment4.data.persistence.RestaurantContract;

import java.util.ArrayList;
import java.util.List;

import static com.soethiha.padc_assignment4.RestaurantApp.TAG;

/**
 * PADC_Assignment4
 *
 * @author SoeThihaTun
 * @version 1.0
 * @since 2017/07/05
 */

public class RestaurantVO {

    @SerializedName("title")
    private String title;

    @SerializedName("addr-short")
    private String addrShort;

    @SerializedName("image")
    private String image;

    @SerializedName("total-rating-count")
    private int totalRatingCnt;

    @SerializedName("average-rating-value")
    private double avgRatingVal;

    @SerializedName("is-ad")
    private boolean isAd;

    @SerializedName("is-new")
    private boolean isNew;

    @SerializedName("tags")
    private String[] tags;

    @SerializedName("lead-time-in-min")
    private int leadTimeInMin;

    public String getTitle() {
        return title;
    }

    public String getAddrShort() {
        return addrShort;
    }

    public String getImage() {
        return image;
    }

    public int getTotalRatingCnt() {
        return totalRatingCnt;
    }

    public double getAvgRatingVal() {
        return avgRatingVal;
    }

    public boolean isAd() {
        return isAd;
    }

    public boolean isNew() {
        return isNew;
    }

    public String[] getTags() {
        return tags;
    }

    public int getLeadTimeInMin() {
        return leadTimeInMin;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public static void saveRestaurants(Context context, List<RestaurantVO> restaurantList) {
        ContentValues[] restaurantCVs = new ContentValues[restaurantList.size()];

        for (int index = 0; index < restaurantList.size(); index++){
            RestaurantVO restaurantVO = restaurantList.get(index);
            restaurantCVs[index] = restaurantVO.parseToContentValues();

            // Save Tags here
             RestaurantVO.saveRestaurantTags(context, restaurantVO.getTitle(), restaurantVO.getTags());
        }

        int insertedCount = context.getContentResolver()
                .bulkInsert(RestaurantContract.RestaurantEntry.CONTENT_URI, restaurantCVs);

        Log.d(TAG, "saveRestaurants: bulkInserted count into Restaurant table = " + insertedCount);
    }

    private static void saveRestaurantTags(Context context, String title, String[] tags) {
        ContentValues[] restaurantTagCVs = new ContentValues[tags.length];
        for (int index = 0; index < tags.length; index++) {
            String tag = tags[index];

            ContentValues cv = new ContentValues();
            cv.put(RestaurantContract.RestaurantTagEntry.COLUMN_RESTAURANT_TITLE, title);
            cv.put(RestaurantContract.RestaurantTagEntry.COLUMN_TAG, tag);

            restaurantTagCVs[index] = cv;
        }

        int insertedCount = context.getContentResolver()
                .bulkInsert(RestaurantContract.RestaurantTagEntry.CONTENT_URI, restaurantTagCVs);

        Log.d(TAG, "saveRestaurants: bulkInserted count into RestaurantTag table = " + insertedCount);
    }

    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(RestaurantContract.RestaurantEntry.COLUMN_TITLE, title);
        cv.put(RestaurantContract.RestaurantEntry.COLUMN_ADDR_SHORT, addrShort);
        cv.put(RestaurantContract.RestaurantEntry.COLUMN_IMAGE, image);
        cv.put(RestaurantContract.RestaurantEntry.COLUMN_TOTAL_RATING_COUNT, totalRatingCnt);
        cv.put(RestaurantContract.RestaurantEntry.COLUMN_AVG_RATING_VALUE, avgRatingVal);
        cv.put(RestaurantContract.RestaurantEntry.COLUMN_IS_AD, isAd);
        cv.put(RestaurantContract.RestaurantEntry.COLUMN_IS_NEW, isNew);
        cv.put(RestaurantContract.RestaurantEntry.COLUMN_LEAD_TIME_IN_MIN, leadTimeInMin);
        return cv;
    }

    public static RestaurantVO parseFromCursor(Cursor data) {
        RestaurantVO restaurantVO = new RestaurantVO();
        restaurantVO.title = data.getString(data.getColumnIndex(RestaurantContract.RestaurantEntry.COLUMN_TITLE));
        restaurantVO.addrShort = data.getString(data.getColumnIndex(RestaurantContract.RestaurantEntry.COLUMN_ADDR_SHORT));
        restaurantVO.image = data.getString(data.getColumnIndex(RestaurantContract.RestaurantEntry.COLUMN_IMAGE));
        restaurantVO.totalRatingCnt = data.getInt(data.getColumnIndex(RestaurantContract.RestaurantEntry.COLUMN_TOTAL_RATING_COUNT));
        restaurantVO.avgRatingVal = data.getInt(data.getColumnIndex(RestaurantContract.RestaurantEntry.COLUMN_AVG_RATING_VALUE));
        restaurantVO.isAd = (data.getInt(data.getColumnIndex(RestaurantContract.RestaurantEntry.COLUMN_IS_AD)) != 0);
        restaurantVO.isNew = (data.getInt(data.getColumnIndex(RestaurantContract.RestaurantEntry.COLUMN_IS_NEW)) != 0);
        restaurantVO.leadTimeInMin = data.getInt(data.getColumnIndex(RestaurantContract.RestaurantEntry.COLUMN_LEAD_TIME_IN_MIN));
        return restaurantVO;
    }

    public static String[] loadRestaurantTagsByTitle(Context context, String title) {
        ArrayList<String> tags = new ArrayList<>();

        Cursor cursor = context.getContentResolver()
                .query(RestaurantContract.RestaurantTagEntry.buildRestaurantTagUriWithTitle(title),
                        null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                tags.add(cursor.getString(cursor.getColumnIndex(RestaurantContract.RestaurantTagEntry.COLUMN_TAG)));
            } while (cursor.moveToNext());
        }

        String[] tagArray = new String[tags.size()];
        tags.toArray(tagArray);
        return tagArray;
    }
}
