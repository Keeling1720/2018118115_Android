package com.mingrisoft.greendao.entity.greendao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.mingrisoft.greendao.entity.greendao.WisdomEntity;
import com.mingrisoft.greendao.entity.greendao.CET4Entity;

import com.mingrisoft.greendao.entity.greendao.WisdomEntityDao;
import com.mingrisoft.greendao.entity.greendao.CET4EntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig wisdomEntityDaoConfig;
    private final DaoConfig cET4EntityDaoConfig;

    private final WisdomEntityDao wisdomEntityDao;
    private final CET4EntityDao cET4EntityDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        wisdomEntityDaoConfig = daoConfigMap.get(WisdomEntityDao.class).clone();
        wisdomEntityDaoConfig.initIdentityScope(type);

        cET4EntityDaoConfig = daoConfigMap.get(CET4EntityDao.class).clone();
        cET4EntityDaoConfig.initIdentityScope(type);

        wisdomEntityDao = new WisdomEntityDao(wisdomEntityDaoConfig, this);
        cET4EntityDao = new CET4EntityDao(cET4EntityDaoConfig, this);

        registerDao(WisdomEntity.class, wisdomEntityDao);
        registerDao(CET4Entity.class, cET4EntityDao);
    }
    
    public void clear() {
        wisdomEntityDaoConfig.getIdentityScope().clear();
        cET4EntityDaoConfig.getIdentityScope().clear();
    }

    public WisdomEntityDao getWisdomEntityDao() {
        return wisdomEntityDao;
    }

    public CET4EntityDao getCET4EntityDao() {
        return cET4EntityDao;
    }

}
