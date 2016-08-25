package org.alex_z.app.a4pdareader.presenter.app.main.base;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import org.alex_z.app.a4pdareader.presenter.base.BaseFragment;

public abstract class BaseMainFragment<T extends BaseMainPresenter>
        extends BaseFragment<T>
        implements IMainView {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void showError(@StringRes int message) {
        Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNewMessagesNotification() {
        if (getView() != null)
            Snackbar.make(getView(), "Message", Snackbar.LENGTH_LONG).show();
    }
}
