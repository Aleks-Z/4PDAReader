package org.alex_z.app.a4pdareader.presenter.app.main.list_news;

import org.alex_z.app.a4pdareader.presenter.app.main.base.IMainView;
import org.alex_z.app.a4pdareader.presenter.entity.NewsPresenterEntity;

import java.util.List;

interface IListNewsView extends IMainView {
    void setListNews(List<NewsPresenterEntity> list);
    List<NewsPresenterEntity> getListNews();
    void switchRefresh();
}
