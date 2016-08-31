package org.alex_z.app.a4pdareader.presenter.app;

import org.alex_z.app.a4pdareader.additional.dagger.RxSchedulerModule;
import org.alex_z.app.a4pdareader.data.DataModule;
import org.alex_z.app.a4pdareader.domain.DomainModule;
import org.alex_z.app.a4pdareader.presenter.PresenterModule;
import org.alex_z.app.a4pdareader.presenter.app.main.list_news.ListNewsFragment;
import org.alex_z.app.a4pdareader.presenter.app.main.list_saved_news.ListSavedSavedNewsFragment;
import org.alex_z.app.a4pdareader.presenter.app.main.show_news.ShowNewsFragment;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {
        RxSchedulerModule.class,
        DataModule.class,
        DomainModule.class,
        PresenterModule.class
})
public interface AppComponent {
    void inject(ListNewsFragment view);

    void inject(ListSavedSavedNewsFragment view);

    void inject(ShowNewsFragment view);
}
