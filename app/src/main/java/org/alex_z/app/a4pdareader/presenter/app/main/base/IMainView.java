package org.alex_z.app.a4pdareader.presenter.app.main.base;

import android.support.annotation.StringRes;

import viper.ViewCallbacks;


public interface IMainView extends ViewCallbacks {
    void showError(@StringRes int message);

    void showNewMessagesNotification();
}
