package org.alex_z.app.a4pdareader.domain.map;

import org.alex_z.app.a4pdareader.data.entity.NewsDataEntity;
import org.alex_z.app.a4pdareader.domain.entity.NewsDomainEntity;

import viper.Mapper;

public class NewsDataEntityToNewsDomainEntityMapper extends Mapper<NewsDataEntity, NewsDomainEntity> {
    @Override
    public NewsDomainEntity map(NewsDataEntity entity) {
        return new NewsDomainEntity(entity.getTitle(), entity.getDescription());
    }
}
