package org.alex_z.app.a4pdareader.presenter.app.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;

import org.alex_z.app.a4pdareader.R;
import org.alex_z.app.a4pdareader.presenter.app.main.base.BaseMainActivity;
import org.alex_z.app.a4pdareader.presenter.app.main.base.IMainRouter;
import org.alex_z.app.a4pdareader.presenter.app.main.news.NewsFragment;
import org.alex_z.app.a4pdareader.presenter.base.Layout;
import org.alex_z.app.a4pdareader.presenter.entity.NewsPresenterEntity;

import java.net.URISyntaxException;

@Layout(id = R.layout.activity_main)
public class MainActivity extends BaseMainActivity implements IMainRouter {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showListNews() {
        showFragment(new NewsFragment(), true);
    }

    @Override
    public void showNews(NewsPresenterEntity news) {
        String url = "";
        try {
            url = news.getUrlNews().toURI().toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }

    @Override
    public void onStartRouter() {
        showFragment(new NewsFragment(), false);
    }
}
