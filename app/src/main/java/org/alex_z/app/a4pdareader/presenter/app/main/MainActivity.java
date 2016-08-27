package org.alex_z.app.a4pdareader.presenter.app.main;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.webkit.WebView;

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
        showFragment(R.id.content, new NewsFragment(), true);
    }

    @Override
    public void showNews(NewsPresenterEntity news) {
        String url = "";
        try {
            url = news.getUrlNews().toURI().toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            //builder.setStartAnimations(this, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            //builder.setExitAnimations(this, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(this, Uri.parse(url));
        } else {
            WebView webView = (WebView) findViewById(R.id.webView);
            webView.loadUrl(url);
        }
    }

    @Override
    public void onStartRouter() {
        showFragment(R.id.content, new NewsFragment(), false);
    }
}
