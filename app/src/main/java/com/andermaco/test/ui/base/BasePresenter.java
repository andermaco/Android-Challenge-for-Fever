package com.andermaco.test.ui.base;

public interface BasePresenter<T extends BaseView> {
    /**
     * This method will be executed on activity/fragment's onStart method
     */
    void onStart();

    void onResume();

    void onPause();

    /**
     * This method will be executed on activity/fragment's onStop method
     */
    void onStop();

    /**
     * This method will be executed on activity/fragment's onDestroy method
     */
    void onDestroy();

    void onBackPressed();

}
