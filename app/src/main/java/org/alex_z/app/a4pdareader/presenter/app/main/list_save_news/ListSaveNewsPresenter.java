package org.alex_z.app.a4pdareader.presenter.app.main.list_save_news;

import org.alex_z.app.a4pdareader.additional.StringProvider;
import org.alex_z.app.a4pdareader.domain.entity.NewsDomainEntity;
import org.alex_z.app.a4pdareader.domain.news.GetNewsInteractor;
import org.alex_z.app.a4pdareader.presenter.app.main.base.BaseMainPresenter;
import org.alex_z.app.a4pdareader.presenter.entity.NewsPresenterEntity;
import org.alex_z.app.a4pdareader.presenter.map.NewsDomainEntityToNewsPresenterEntityMapper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

public class ListSaveNewsPresenter extends BaseMainPresenter<IListSaveNewsView> {
    private GetNewsInteractor getNewsInteractor;
    private URL sourceURL;

    @Inject
    ListSaveNewsPresenter(GetNewsInteractor getNewsInteractor) {
        this.getNewsInteractor = getNewsInteractor;
        this.getNewsInteractor.fromDisk(true);
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
    }

    public void newsSelected(NewsPresenterEntity entity) {
        getRouter().showNews(entity, true);
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

}
