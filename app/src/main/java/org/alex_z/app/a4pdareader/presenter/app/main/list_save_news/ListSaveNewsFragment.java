package org.alex_z.app.a4pdareader.presenter.app.main.list_save_news;

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

@Layout(id = R.layout.fragment_news)
public class ListSaveNewsFragment extends BaseMainFragment<ListSaveNewsPresenter> implements IListSaveNewsView {
    @Inject
    ListSaveNewsPresenter listSaveNewsPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout refreshLayout;

    List<NewsPresenterEntity> news;
    ListSaveNewsAdapter adapter;

    private static ListSaveNewsFragment instance;

    public static ListSaveNewsFragment getInstance() {
        if (instance == null) instance = new ListSaveNewsFragment();
        return instance;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        news = new ArrayList<>();
        adapter = new ListSaveNewsAdapter(this.news);
        adapter.setOnItemClickListener(
                view -> listSaveNewsPresenter.newsSelected((NewsPresenterEntity) view.getTag())
        );

        refreshLayout.setOnRefreshListener(() -> getPresenter().updateNews());

        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @NonNull
    @Override
    protected ListSaveNewsPresenter getPresenter() {
        return listSaveNewsPresenter;
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
