package org.alex_z.app.a4pdareader.presenter.app.main.news;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.alex_z.app.a4pdareader.R;
import org.alex_z.app.a4pdareader.presenter.app.main.base.BaseMainFragment;
import org.alex_z.app.a4pdareader.presenter.base.Layout;
import org.alex_z.app.a4pdareader.presenter.entity.NewsPresenterEntity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

@Layout(id = R.layout.fragment_news)
public class NewsFragment extends BaseMainFragment<NewsPresenter> implements INewsView {
    @Inject
    NewsPresenter newsPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    List<NewsPresenterEntity> news;
    NewsAdapter adapter;

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
    public void setNews(List<NewsPresenterEntity> list) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.news = list;
        if (adapter == null) {
            adapter = new NewsAdapter(this.news);
            recyclerView.setAdapter(adapter);
        } else
            adapter.notifyDataSetChanged();
    }
}
