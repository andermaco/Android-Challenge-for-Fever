package com.andermaco.test.ui.base;

/**
 * Created by andermaco@gmail.com on 27/07/17.
 */

import android.view.ViewGroup;

/**
 * MVP View that must be extended by all MVP views
 */
public interface BaseView {

    void setTitle(String title);

    ViewGroup getContainer();

}
