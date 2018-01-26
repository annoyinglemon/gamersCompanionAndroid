package lemond.annoying.gamerscompanion.fragment_now.fragment_popular.viewmodel;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.app.ViewControllerScope;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.model.PopularRepository;

@ViewControllerScope
public class PopularViewModelFactory implements ViewModelProvider.Factory {

    private final PopularRepository popularRepository;

    @Inject
    PopularViewModelFactory(PopularRepository popularRepository) {
        this.popularRepository = popularRepository;
    }

    @Override
    @NonNull
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PopularFragmentViewModel.class)) {
            return (T) new PopularFragmentViewModel(popularRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
