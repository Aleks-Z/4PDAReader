package org.alex_z.app.a4pdareader.presenter.app.main;

import android.content.res.Configuration;
import android.view.MenuItem;
import android.widget.ViewSwitcher;

import org.alex_z.app.a4pdareader.R;
import org.alex_z.app.a4pdareader.presenter.app.main.base.BaseMainActivity;
import org.alex_z.app.a4pdareader.presenter.app.main.base.IMainRouter;
import org.alex_z.app.a4pdareader.presenter.app.main.list_news.ListNewsFragment;
import org.alex_z.app.a4pdareader.presenter.app.main.list_save_news.ListSaveNewsFragment;
import org.alex_z.app.a4pdareader.presenter.app.main.show_comment_news.ShowCommentNewsFragment;
import org.alex_z.app.a4pdareader.presenter.app.main.show_news.ShowNewsFragment;
import org.alex_z.app.a4pdareader.presenter.base.Layout;
import org.alex_z.app.a4pdareader.presenter.entity.NewsPresenterEntity;

@Layout(id = R.layout.activity_main, menuId = R.menu.activity_main)
public class MainActivity extends BaseMainActivity implements IMainRouter {

    @Override
    public void showListNews() {
        showFragment(R.id.content, ListNewsFragment.getInstance(), false);
    }

    @Override
    public void showSaveListNews() {
        showFragment(R.id.content, ListSaveNewsFragment.getInstance(), false);
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
    public void showCommentNews(NewsPresenterEntity news) {
        if (getResources()
                .getConfiguration()
                .orientation == Configuration.ORIENTATION_PORTRAIT) {
            ViewSwitcher viewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
            viewSwitcher.showNext();
        }

        showFragment(
                R.id.content2,
                ShowCommentNewsFragment.newInstance(news),
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_item_load_news:
                showListNews();
                return true;
            case R.id.menu_item_saved_news:
                showSaveListNews();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
