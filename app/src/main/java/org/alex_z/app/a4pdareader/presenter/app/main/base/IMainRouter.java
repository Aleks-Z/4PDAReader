package org.alex_z.app.a4pdareader.presenter.app.main.base;

import org.alex_z.app.a4pdareader.presenter.base.IBaseRouter;
import org.alex_z.app.a4pdareader.presenter.entity.NewsPresenterEntity;

import viper.Router;

public interface IMainRouter extends IBaseRouter, Router {
    void showListNews();

    void showNews(NewsPresenterEntity news);
}
