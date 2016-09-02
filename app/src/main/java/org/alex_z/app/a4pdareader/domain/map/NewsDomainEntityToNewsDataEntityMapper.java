package org.alex_z.app.a4pdareader.domain.map;

import org.alex_z.app.a4pdareader.data.entity.NewsDataEntity;
import org.alex_z.app.a4pdareader.domain.entity.NewsDomainEntity;

import viper.Mapper;

public class NewsDomainEntityToNewsDataEntityMapper extends Mapper<NewsDomainEntity, NewsDataEntity> {
    @Override
    public NewsDataEntity map(NewsDomainEntity entity) {
        return new NewsDataEntity(
                entity.getTitle(),
                entity.getUrlNews().toString(),
                entity.getCommentUrlNews().toString(),
                entity.getSourceHTML()
        );
    }
}
