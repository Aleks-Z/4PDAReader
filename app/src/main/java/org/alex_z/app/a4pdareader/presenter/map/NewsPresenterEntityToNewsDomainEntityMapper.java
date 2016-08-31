package org.alex_z.app.a4pdareader.presenter.map;

import org.alex_z.app.a4pdareader.domain.entity.NewsDomainEntity;
import org.alex_z.app.a4pdareader.presenter.entity.NewsPresenterEntity;

import viper.Mapper;

public class NewsPresenterEntityToNewsDomainEntityMapper extends Mapper<NewsPresenterEntity, NewsDomainEntity> {
    @Override
    public NewsDomainEntity map(NewsPresenterEntity entity) {
        return new NewsDomainEntity(entity.getTitle(), entity.getUrlNews(), entity.getSourceHTML());
    }
}
