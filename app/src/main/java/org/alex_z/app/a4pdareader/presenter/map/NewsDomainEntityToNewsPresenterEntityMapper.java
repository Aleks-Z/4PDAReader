package org.alex_z.app.a4pdareader.presenter.map;

import org.alex_z.app.a4pdareader.domain.entity.NewsDomainEntity;
import org.alex_z.app.a4pdareader.presenter.entity.NewsPresenterEntity;

import viper.Mapper;

public class NewsDomainEntityToNewsPresenterEntityMapper
        extends Mapper<NewsDomainEntity, NewsPresenterEntity> {
    @Override
    public NewsPresenterEntity map(NewsDomainEntity entity) {
        return new NewsPresenterEntity(
                entity.getTitle(),

                entity.getUrlNews(),
                entity.getSourceHTML()
        );
    }
}
