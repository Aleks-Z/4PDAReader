package org.alex_z.app.a4pdareader.presenter.app.main.news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.alex_z.app.a4pdareader.R;
import org.alex_z.app.a4pdareader.presenter.app.main.base.BaseMainFragment;
import org.alex_z.app.a4pdareader.presenter.base.Layout;
import org.alex_z.app.a4pdareader.presenter.entity.NewsPresenterEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

@Layout(id = R.layout.fragment_news)
public class NewsFragment extends BaseMainFragment<NewsPresenter> implements INewsView {
    @Inject
    NewsPresenter newsPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout refreshLayout;

    List<NewsPresenterEntity> news;
    NewsAdapter adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        refreshLayout.setOnRefreshListener(() -> getPresenter().updateNews());
        news = new ArrayList<>();
        adapter = new NewsAdapter(this.news);
        adapter.setOnItemClickListener(
                view -> newsPresenter.newsSelected((NewsPresenterEntity) view.getTag())
        );
        recyclerView.setAdapter(adapter);
    }

    @NonNull
    @Override
    protected NewsPresenter getPresenter() {
        return newsPresenter;
    }

    @Override
    public void inject() {
        getDaggerAppComponent().inject(this);
    }

    @Override
    public void setListNews(List<NewsPresenterEntity> list) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        news.clear();
        news.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public List<NewsPresenterEntity> getListNews() {
        return new ArrayList<>(news);
    }

    @Override
    public void switchRefresh() {
        refreshLayout.setRefreshing(!refreshLayout.isRefreshing());
    }
}
