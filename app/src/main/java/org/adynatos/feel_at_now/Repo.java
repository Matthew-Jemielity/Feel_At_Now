package org.adynatos.feel_at_now;

import android.app.Application;

import java.util.List;

class Repo {
    private final Dao mDao;

    Repo(Application application) {
        Database db = Database.get(application);
        mDao = db.dao();
    }

    List<FeelData> get() {
        return mDao.get();
    }

    void put(final FeelData data) {
        Database.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDao.put(data);
            }
        });
    }
}
