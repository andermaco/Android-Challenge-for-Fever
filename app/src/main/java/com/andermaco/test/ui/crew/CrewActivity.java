package com.andermaco.test.ui.crew;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.andermaco.test.R;
import com.andermaco.test.model.Crew;
import com.andermaco.test.ui.adapter.CrewListAdapter;
import com.andermaco.test.ui.base.BaseActivity;
import com.andermaco.test.ui.base.BasePresenter;
import com.andermaco.test.ui.di.DiComponent;
import com.andermaco.test.ui.di.components.ApplicationComponent;
import com.andermaco.test.ui.di.components.CrewComponent;
import com.andermaco.test.ui.di.components.DaggerCrewComponent;
import com.andermaco.test.ui.di.modules.CrewModule;

import javax.inject.Inject;

import butterknife.BindView;
import io.realm.RealmResults;

public class CrewActivity extends BaseActivity implements CrewView, CrewListAdapter.OnCrewClickListener {

    protected DiComponent component;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Inject
    CrewPresenter presenter;

    private CrewListAdapter mAdapter;
    private boolean shortByName = true;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initToolbar();
        initList();
    }

    @Override
    protected void closeRealm() {
        presenter.closeRealm();
    }

    @Override
    protected void setupComponent(ApplicationComponent appComponent) {
        component = DaggerCrewComponent.builder()
                .crewModule(new CrewModule(this))
                .applicationComponent(appComponent)
                .build();
        ((CrewComponent) component).inject(this);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_crew;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
    }

    private void initList() {
        mAdapter = new CrewListAdapter(this);
        mAdapter.setOnCrewClickListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        presenter.showCrewByName();

    }

    @Override
    public void onCrewClick() {
//        presenter.showCrewByPosition();
//        if (shortByName) {
//            shortByName = false;
//        } else {
//            shortByName = true;
//        }
    }

    @Override
    public void showCrew(RealmResults<Crew> allCrew) {
        mAdapter.setCrew(allCrew);
    }
}
