package org.alex_z.app.a4pdareader.presenter.app.main.news;

import org.alex_z.app.a4pdareader.additional.StringProvider;
import org.alex_z.app.a4pdareader.domain.entity.NewsDomainEntity;
import org.alex_z.app.a4pdareader.domain.news.NewsInteractor;
import org.alex_z.app.a4pdareader.presenter.app.main.base.BaseMainPresenter;
import org.alex_z.app.a4pdareader.presenter.map.NewsDomainEntityToNewsPresenterEntityMapper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

public class NewsPresenter extends BaseMainPresenter<INewsView> {
    private NewsInteractor newsInteractor;
    private URL url;

    @Inject
    NewsPresenter(NewsInteractor newsInteractor) {
        this.newsInteractor = newsInteractor;
        try {
            url = new URL(StringProvider.getInstance().LINK_SOURCE);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        newsInteractor.execute(new Subscriber<List<NewsDomainEntity>>() {
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
                getView().setNews(
                        new ArrayList<>(
                                new NewsDomainEntityToNewsPresenterEntityMapper()
                                        .map(newsDomainEntities)
                        )
                );
            }
        }, url);
    }

    @Override
    public void onStop() {
        newsInteractor.unsubscribe();
    }

}
