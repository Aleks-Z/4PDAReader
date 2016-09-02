package org.alex_z.app.a4pdareader.presenter.app.main.list_news;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import org.alex_z.app.a4pdareader.R;
import org.alex_z.app.a4pdareader.presenter.entity.NewsPresenterEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


class ListNewsAdapter extends RecyclerView.Adapter<ListNewsAdapter.NewsViewHolder> {
    private List<NewsPresenterEntity> newsList;
    private View.OnClickListener onItemClickListener;
    private View.OnClickListener onItemCommentImageButtonClickListener;
    private View.OnClickListener onItemSaveImageButtonClickListener;

    public ListNewsAdapter(List<NewsPresenterEntity> newsList, View.OnClickListener onItemClickListener) {
        this.newsList = newsList;
        this.onItemClickListener = onItemClickListener;
    }

    ListNewsAdapter(List<NewsPresenterEntity> newsList) {
        this.newsList = newsList;
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemCommentImageButtonClickListener
            (View.OnClickListener onItemCommentImageButtonClickListener) {
        this.onItemCommentImageButtonClickListener = onItemCommentImageButtonClickListener;
    }

    public void setOnItemSaveImageButtonClickListener
            (View.OnClickListener onItemSaveImageButtonClickListener) {
        this.onItemSaveImageButtonClickListener = onItemSaveImageButtonClickListener;
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

        @BindView(R.id.saveImageButton)
        ImageButton saveImageButton;

        @BindView(R.id.commentImageButton)
        ImageButton commentImageButton;

        NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(onItemClickListener);
            commentImageButton.setOnClickListener(onItemCommentImageButtonClickListener);
            saveImageButton.setOnClickListener(onItemSaveImageButtonClickListener);
        }

        void bind(NewsPresenterEntity news) {
            itemView.setTag(news);
            commentImageButton.setTag(news);
            saveImageButton.setTag(news);
            newsTitleTextView.setText(news.getTitle());
        }

    }
}