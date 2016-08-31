package org.alex_z.app.a4pdareader.presenter.app.main.show_news;

import org.alex_z.app.a4pdareader.presenter.app.main.base.IMainView;
import org.alex_z.app.a4pdareader.presenter.entity.NewsPresenterEntity;

public interface IShowNewsView extends IMainView {
    void showNews(NewsPresenterEntity entity);
}
