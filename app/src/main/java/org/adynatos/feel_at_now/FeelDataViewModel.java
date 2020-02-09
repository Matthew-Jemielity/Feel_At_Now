package org.adynatos.feel_at_now;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class FeelDataViewModel extends AndroidViewModel {
    private final Repo mRepo;

    public FeelDataViewModel(@NonNull Application application) {
        super(application);

        mRepo = new Repo(application);
    }

    List<FeelData> get() {
        return mRepo.get();
    }

    void put(final FeelData data) {
        mRepo.put(data);
    }
}
