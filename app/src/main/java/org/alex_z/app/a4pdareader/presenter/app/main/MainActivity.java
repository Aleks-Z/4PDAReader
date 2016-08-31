package org.alex_z.app.a4pdareader.presenter.app.main;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.webkit.WebView;
import android.widget.ViewSwitcher;

import org.alex_z.app.a4pdareader.R;
import org.alex_z.app.a4pdareader.presenter.app.main.base.BaseMainActivity;
import org.alex_z.app.a4pdareader.presenter.app.main.base.IMainRouter;
import org.alex_z.app.a4pdareader.presenter.app.main.list_news.ListNewsFragment;
import org.alex_z.app.a4pdareader.presenter.app.main.show_news.ShowNewsFragment;
import org.alex_z.app.a4pdareader.presenter.base.Layout;
import org.alex_z.app.a4pdareader.presenter.entity.NewsPresenterEntity;

import java.net.URISyntaxException;
import java.util.List;

@Layout(id = R.layout.activity_main)
public class MainActivity extends BaseMainActivity implements IMainRouter {

    @Override
    public void showListNews() {
        showFragment(R.id.content, ListNewsFragment.getInstance(), true);
    }

    @Override
    public void showNews(NewsPresenterEntity news, boolean fromDisk) {
        if (getResources()
                .getConfiguration()
                .orientation == Configuration.ORIENTATION_PORTRAIT) {
            ViewSwitcher viewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
            viewSwitcher.showNext();
        }

        showFragment(
                R.id.content2,
                ShowNewsFragment.newInstance(fromDisk, news),
                false
        );
    }

    @Override
    public void onStartRouter() {
        showFragment(R.id.content, ListNewsFragment.getInstance(), false);
    }

    @Override
    public void onBackPressed() {
        if (getResources()
                .getConfiguration()
                .orientation == Configuration.ORIENTATION_PORTRAIT) {
            ViewSwitcher viewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
            switch (viewSwitcher.getDisplayedChild()) {
                case 0:
                    super.onBackPressed();
                    break;
                case 1:
                    viewSwitcher.showPrevious();
                    break;
            }

        } else
            super.onBackPressed();
    }
}
