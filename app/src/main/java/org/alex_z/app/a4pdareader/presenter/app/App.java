package org.alex_z.app.a4pdareader.presenter.app;

import android.app.Application;

import org.alex_z.app.a4pdareader.additional.StringProvider;
import org.alex_z.app.a4pdareader.additional.greendao.GreenDaoProvider;

public class App extends Application {
    private AppComponent mComponent;

    public AppComponent getComponent() {
        return mComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initialization();
    }

    private void initialization() {
        mComponent = DaggerAppComponent.create();
        GreenDaoProvider.initialization(this, true);
        StringProvider.initialization(getResources());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        release();
    }

    private void release() {
        GreenDaoProvider.release();
        StringProvider.release();
    }
}
