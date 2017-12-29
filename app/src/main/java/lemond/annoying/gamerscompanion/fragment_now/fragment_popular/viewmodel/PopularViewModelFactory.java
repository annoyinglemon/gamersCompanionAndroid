package lemond.annoying.gamerscompanion.fragment_now.fragment_popular.viewmodel;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.model.PopularModel;

public class PopularViewModelFactory implements ViewModelProvider.Factory{

    private final PopularModel popularModel;

    @Inject
    public PopularViewModelFactory(PopularModel popularModel) {
        this.popularModel = popularModel;
    }

    @Override
    @NonNull
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PopularViewModel.class)) {
            return (T) new PopularViewModel(popularModel);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
