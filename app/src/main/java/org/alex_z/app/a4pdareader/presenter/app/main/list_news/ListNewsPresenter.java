package org.alex_z.app.a4pdareader.presenter.app.main.list_news;

import org.alex_z.app.a4pdareader.additional.StringProvider;
import org.alex_z.app.a4pdareader.domain.entity.NewsDomainEntity;
import org.alex_z.app.a4pdareader.domain.news.GetNewsInteractor;
import org.alex_z.app.a4pdareader.domain.news.SaveNewsInteractor;
import org.alex_z.app.a4pdareader.presenter.app.main.base.BaseMainPresenter;
import org.alex_z.app.a4pdareader.presenter.entity.NewsPresenterEntity;
import org.alex_z.app.a4pdareader.presenter.map.NewsDomainEntityToNewsPresenterEntityMapper;
import org.alex_z.app.a4pdareader.presenter.map.NewsPresenterEntityToNewsDomainEntityMapper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

public class ListNewsPresenter extends BaseMainPresenter<IListNewsView> {
    private GetNewsInteractor getNewsInteractor;
    private SaveNewsInteractor saveNewsInteractor;
    private URL sourceURL;

    @Inject
    ListNewsPresenter(GetNewsInteractor getNewsInteractor, SaveNewsInteractor saveNewsInteractor) {
        this.getNewsInteractor = getNewsInteractor;
        this.saveNewsInteractor = saveNewsInteractor;
        try {
            sourceURL = new URL(StringProvider.getInstance().LINK_SOURCE);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        getNewsInteractor.execute(new Subscriber<List<NewsDomainEntity>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                getView().showError(0);
            }

            @Override
            public void onNext(List<NewsDomainEntity> newsDomainEntities) {
                getView().setListNews(
                        new ArrayList<>(
                                new NewsDomainEntityToNewsPresenterEntityMapper()
                                        .map(newsDomainEntities)
                        )
                );
            }
        }, sourceURL);
    }

    @Override
    public void onStop() {
        getNewsInteractor.unsubscribe();
        saveNewsInteractor.unsubscribe();
    }

    public void newsCommentSelected(NewsPresenterEntity entity) {
        getRouter().showCommentNews(entity);
    }

    public void newsSelected(NewsPresenterEntity entity) {
        getRouter().showNews(entity, false);
    }

    public void updateNews() {
        getNewsInteractor.execute(new Subscriber<List<NewsDomainEntity>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                getView().showError(0);
            }

            @Override
            public void onNext(List<NewsDomainEntity> newsDomainEntities) {
                getView().setListNews(
                        new ArrayList<>(
                                new NewsDomainEntityToNewsPresenterEntityMapper()
                                        .map(newsDomainEntities)
                        )
                );
                getView().switchRefresh();
            }
        }, sourceURL);
    }

    public void saveNews(NewsPresenterEntity entity) {
        saveNewsInteractor.execute(
                new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        getView().showNewMessagesNotification();
                    }
                },
                new NewsPresenterEntityToNewsDomainEntityMapper().map(entity));
    }
}
