package org.alex_z.app.a4pdareader.presenter.app.main.show_comment_news;

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
public class ShowCommentNewsFragment extends BaseMainFragment<ShowCommentNewsPresenter> implements IShowCommentNewsView {
    private static ShowCommentNewsFragment instance;
    private static final String KEY_FROM_DISK = "KEY_FROM_DISK";
    public static final String KEY_NEWS = "KEY_NEWS";

    private NewsPresenterEntity news;

    public static ShowCommentNewsFragment newInstance(NewsPresenterEntity news) {
        if (instance == null) {
            instance = new ShowCommentNewsFragment();
        }
        instance.setNews(news);

        return instance;
    }

    @Inject
    ShowCommentNewsPresenter showCommentNewsPresenter;

    @BindView(R.id.webView)
    WebView webView;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getPresenter().setEntity(news);
        //setRetainInstance(true);
    }

    public ShowCommentNewsFragment setNews(NewsPresenterEntity news) {
        this.news = news;
        return this;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_NEWS, news);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            news = (NewsPresenterEntity) savedInstanceState.getSerializable(KEY_NEWS);
            getPresenter().setEntity(news);
        }
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        if (args != null) {
            news = (NewsPresenterEntity) args.getSerializable(KEY_NEWS);
        }
    }

    @NonNull
    @Override
    protected ShowCommentNewsPresenter getPresenter() {
        return showCommentNewsPresenter;
    }

    @Override
    public void inject() {
        getDaggerAppComponent().inject(this);
    }

    @Override
    public void showCommentNews(NewsPresenterEntity entity) {
        webView.loadUrl(entity.getCommentUrlNews().toString());
    }
}
