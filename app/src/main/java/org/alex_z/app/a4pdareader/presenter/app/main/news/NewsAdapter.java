package org.alex_z.app.a4pdareader.presenter.app.main.news;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import org.alex_z.app.a4pdareader.R;
import org.alex_z.app.a4pdareader.presenter.entity.NewsPresenterEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<NewsPresenterEntity> newsList;
    private View.OnClickListener onItemClickListener;

    public NewsAdapter(List<NewsPresenterEntity> newsList, View.OnClickListener onItemClickListener) {
        this.newsList = newsList;
        this.onItemClickListener = onItemClickListener;
    }

    NewsAdapter(List<NewsPresenterEntity> newsList) {
        this.newsList = newsList;
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.item_news,
                        parent,
                        false
                );

        return new NewsViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.bind(newsList.get(position));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.news_title)
        TextView newsTitleTextView;

        NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(onItemClickListener);
        }

        void bind(NewsPresenterEntity news) {
            newsTitleTextView.setText(news.getTitle());
            itemView.setTag(news);
        }

    }
}