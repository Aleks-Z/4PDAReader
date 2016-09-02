package org.alex_z.app.a4pdareader.presenter.app.main.show_comment_news;

import org.alex_z.app.a4pdareader.presenter.app.main.base.BaseMainPresenter;
import org.alex_z.app.a4pdareader.presenter.entity.NewsPresenterEntity;

import javax.inject.Inject;

public class ShowCommentNewsPresenter extends BaseMainPresenter<IShowCommentNewsView> {
    private NewsPresenterEntity entity;

    @Inject
    public ShowCommentNewsPresenter() {
    }

    public void setEntity(NewsPresenterEntity entity) {
        this.entity = entity;
    }

    @Override
    public void onStart() {
        getView().showCommentNews(entity);
    }

    @Override
    public void onStop() {

    }


}
