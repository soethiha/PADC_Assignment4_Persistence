package com.soethiha.padc_assignment4.data.persistence;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.soethiha.padc_assignment4.RestaurantApp;

/**
 * PADC_Assignment4
 *
 * @author SoeThihaTun
 * @version 1.0
 * @since 2017/07/05
 */

public class RestaurantContract {

    public static final String CONTENT_AUTHORITY = RestaurantApp.class.getPackage().getName();
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // Table paths
    public static final String PATH_RESTAURANTS = "restaurants";
    public static final String PATH_RESTAURANT_TAGS = "restaurant_tags";

    // Inner Entry Classes
    public static final class RestaurantEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RESTAURANTS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANTS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANTS;

        public static final String TABLE_NAME = "restaurants";

        // Table Columns
        // Note : - does not work in SQLite Column Name
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_ADDR_SHORT = "addr_short";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_TOTAL_RATING_COUNT = "total_rating_count";
        public static final String COLUMN_AVG_RATING_VALUE = "average_rating_value";
        public static final String COLUMN_IS_AD = "is_ad";
        public static final String COLUMN_IS_NEW = "is_new";
        public static final String COLUMN_LEAD_TIME_IN_MIN = "lead_time_in_min";

        // Uri Builder
        public static Uri buildRestaurantUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class RestaurantTagEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RESTAURANT_TAGS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANT_TAGS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANT_TAGS;

        public static final String TABLE_NAME = "restaurant_tags";

        // Table Columns
        public static final String COLUMN_RESTAURANT_TITLE = "restaurant_title";
        public static final String COLUMN_TAG = "tag";

        // Uri Builder
        public static Uri buildRestaurantTagUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildRestaurantTagUriWithTitle(String restaurantTitle) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_RESTAURANT_TITLE, restaurantTitle)
                    .build();
        }

        public static String getRestaurantTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_RESTAURANT_TITLE);
        }
    }
}
