package org.alex_z.app.a4pdareader.presenter.app.main.list_saved_news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
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

@Layout(id = R.layout.fragment_news, menuId = R.menu.fragment_list_news)
public class ListSavedSavedNewsFragment extends BaseMainFragment<ListSavedNewsPresenter> implements IListSavedNewsView {
    @Inject
    ListSavedNewsPresenter listSavedNewsPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout refreshLayout;

    List<NewsPresenterEntity> news;
    ListSavedNewsAdapter adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        news = new ArrayList<>();
        adapter = new ListSavedNewsAdapter(this.news);
        adapter.setOnItemClickListener(
                view -> listSavedNewsPresenter.newsSelected((NewsPresenterEntity) view.getTag())
        );

        refreshLayout.setOnRefreshListener(() -> getPresenter().updateNews());

        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @NonNull
    @Override
    protected ListSavedNewsPresenter getPresenter() {
        return listSavedNewsPresenter;
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