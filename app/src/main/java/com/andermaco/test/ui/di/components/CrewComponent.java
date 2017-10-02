package com.andermaco.test.ui.di.components;

import com.andermaco.test.ui.crew.CrewActivity;
import com.andermaco.test.ui.di.DiComponent;
import com.andermaco.test.ui.di.modules.CrewModule;
import com.andermaco.test.ui.di.scopes.PerActivity;

import dagger.Component;

/**
 * Created by andermaco@gmail.com on 20/09/17.
 */

@PerActivity
@Component(
        modules = {
                CrewModule.class,
        },
        dependencies = {
                ApplicationComponent.class
        }
)

public interface CrewComponent extends DiComponent {
    void inject(CrewActivity loginActivity);
}