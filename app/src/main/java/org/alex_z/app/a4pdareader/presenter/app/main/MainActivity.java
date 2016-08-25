package org.alex_z.app.a4pdareader.presenter.app.main;

import android.os.Bundle;

import org.alex_z.app.a4pdareader.R;
import org.alex_z.app.a4pdareader.presenter.app.main.base.BaseMainActivity;
import org.alex_z.app.a4pdareader.presenter.app.main.base.IMainRouter;
import org.alex_z.app.a4pdareader.presenter.app.main.news.NewsFragment;
import org.alex_z.app.a4pdareader.presenter.base.Layout;

@Layout(id = R.layout.activity_main)
public class MainActivity extends BaseMainActivity implements IMainRouter {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showNews() {
        showFragment(new NewsFragment(), true);
    }

    @Override
    public void onStartRouter() {
        showFragment(new NewsFragment(), false);
    }
}
