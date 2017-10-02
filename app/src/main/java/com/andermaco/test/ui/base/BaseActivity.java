package com.andermaco.test.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.andermaco.test.App;
import com.andermaco.test.ui.di.components.ApplicationComponent;

import butterknife.ButterKnife;

/**
 * Created by andermaco@gmail.com on 20/09/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private boolean isInjected = false;
    private BasePresenter presenter;


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());

        //Dependency injection
        ButterKnife.bind(this);
        injectDependencies();

        initPresenter();

    }

    @Override
    protected void onDestroy() {
        closeRealm();
        super.onDestroy();
    }


    /**
     * Close Realm
     */
    protected abstract void closeRealm();

    /**
     * Setup dagger 2
     * @param appComponent ApplicationComponent
     */
    protected abstract void setupComponent(ApplicationComponent appComponent);

    /**
     * Layout to be injected
     * @return Layout Id
     */
    protected abstract int getLayoutResourceId();

    /**
     * @return The presenter attached to the activity. This must extends from {@link BasePresenter}
     */
    protected abstract BasePresenter getPresenter();


    private void injectDependencies() {
        if (!isInjected) {
            setupComponent(App.getApp(this).getApplicationComponent());
            isInjected = true;
        }
    }

    private void initPresenter() {
        if (presenter == null) {
            presenter = this.getPresenter();
        }
    }
}
