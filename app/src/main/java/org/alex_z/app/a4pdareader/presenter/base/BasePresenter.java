package org.alex_z.app.a4pdareader.presenter.base;

import viper.Router;
import viper.ViewCallbacks;
import viper.ViperPresenter;

public abstract class BasePresenter<V extends ViewCallbacks, R extends Router>
        extends ViperPresenter<V, R> {
    public abstract void onStart();

    public abstract void onStop();


}
