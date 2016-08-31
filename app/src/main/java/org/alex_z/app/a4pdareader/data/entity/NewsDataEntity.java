package org.alex_z.app.a4pdareader.data.entity;

import android.webkit.URLUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity(active = true)
public class NewsDataEntity {
    @Id
    private Long id;

    @NotNull
    private String title;
    @NotNull
    private String urlNews;

    private String sourceHTML;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 846585716)
    private transient NewsDataEntityDao myDao;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    public NewsDataEntity(String title, String urlNews, String sourceHTML) {
        if (!URLUtil.isValidUrl(urlNews))
            throw new IllegalArgumentException("urlNews isn't valid");

        this.title = title;
        this.urlNews = urlNews;
        this.sourceHTML = sourceHTML;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1793596188)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getNewsDataEntityDao() : null;
    }

    public String getSourceHTML() {
        return this.sourceHTML;
    }

    public void setSourceHTML(String sourceHTML) {
        this.sourceHTML = sourceHTML;
    }

    public String getUrlNews() {
        return this.urlNews;
    }

    public void setUrlNews(String urlNews) {
        this.urlNews = urlNews;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 1951640860)
    public NewsDataEntity(Long id, @NotNull String title, @NotNull String urlNews,
                          String sourceHTML) {
        this.id = id;
        this.title = title;
        this.urlNews = urlNews;
        this.sourceHTML = sourceHTML;
    }

    @Generated(hash = 1681746503)
    public NewsDataEntity() {
    }


}
