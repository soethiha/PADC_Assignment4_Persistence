package com.soethiha.padc_assignment4.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Soe Thiha on 22-Jun-17.
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
}
