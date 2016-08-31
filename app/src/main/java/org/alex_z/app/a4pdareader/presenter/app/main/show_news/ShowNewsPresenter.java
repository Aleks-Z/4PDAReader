package org.alex_z.app.a4pdareader.presenter.app.main.show_news;

import org.alex_z.app.a4pdareader.presenter.app.main.base.BaseMainPresenter;
import org.alex_z.app.a4pdareader.presenter.entity.NewsPresenterEntity;

import javax.inject.Inject;

public class ShowNewsPresenter extends BaseMainPresenter<IShowNewsView> {
    private NewsPresenterEntity entity;

    @Inject
    public ShowNewsPresenter() {
    }

    public void setEntity(NewsPresenterEntity entity) {
        this.entity = entity;
    }

    @Override
    public void onStart() {
        getView().showNews(entity);
    }

    @Override
    public void onStop() {

    }


}
