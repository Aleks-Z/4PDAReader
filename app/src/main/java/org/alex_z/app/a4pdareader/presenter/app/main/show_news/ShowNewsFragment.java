package org.alex_z.app.a4pdareader.presenter.app.main.show_news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import org.alex_z.app.a4pdareader.R;
import org.alex_z.app.a4pdareader.presenter.app.main.base.BaseMainFragment;
import org.alex_z.app.a4pdareader.presenter.base.Layout;
import org.alex_z.app.a4pdareader.presenter.entity.NewsPresenterEntity;

import javax.inject.Inject;

import butterknife.BindView;

@Layout(id = R.layout.fragment_show_news)
public class ShowNewsFragment extends BaseMainFragment<ShowNewsPresenter> implements IShowNewsView {
    private static ShowNewsFragment instance;
    private static final String KEY_FROM_DISK = "KEY_FROM_DISK";
    public static final String KEY_NEWS = "KEY_NEWS";

    private boolean fromDisk;
    private NewsPresenterEntity news;

    public static ShowNewsFragment newInstance(boolean fromDisk, NewsPresenterEntity news) {
        if (instance == null) {
            instance = new ShowNewsFragment();
        }

        instance.setFromDisk(fromDisk);
        instance.setNews(news);

        return instance;
    }

    @Inject
    ShowNewsPresenter showNewsPresenter;

    @BindView(R.id.webView)
    WebView webView;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getPresenter().setEntity(news);
        //setRetainInstance(true);
    }

    public ShowNewsFragment setFromDisk(boolean fromDisk) {
        this.fromDisk = fromDisk;
        return this;
    }

    public ShowNewsFragment setNews(NewsPresenterEntity news) {
        this.news = news;
        return this;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_FROM_DISK, fromDisk);
        outState.putSerializable(KEY_NEWS, news);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            fromDisk = savedInstanceState.getBoolean(KEY_FROM_DISK);
            news = (NewsPresenterEntity) savedInstanceState.getSerializable(KEY_NEWS);
            getPresenter().setEntity(news);
        }
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        if (args != null) {
            fromDisk = args.getBoolean(KEY_FROM_DISK);
            news = (NewsPresenterEntity) args.getSerializable(KEY_NEWS);
        }
    }

    @NonNull
    @Override
    protected ShowNewsPresenter getPresenter() {
        return showNewsPresenter;
    }

    @Override
    public void inject() {
        getDaggerAppComponent().inject(this);
    }

    @Override
    public void showNews(NewsPresenterEntity entity) {
        if (fromDisk)
            webView.loadData(entity.getSourceHTML(), "text/html; charset=UTF-8", null);
        else
            webView.loadUrl(entity.getUrlNews().toString());
    }
}
