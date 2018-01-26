package lemond.annoying.gamerscompanion.fragment_now.fragment_trending.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.app.ViewControllerScope;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.model.TrendingRepository;


@ViewControllerScope
public class TrendingViewModelFactory implements ViewModelProvider.Factory {

    private final TrendingRepository trendingRepository;

    @Inject
    TrendingViewModelFactory(TrendingRepository trendingRepository) {
        this.trendingRepository = trendingRepository;
    }

    @Override
    @NonNull
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TrendingFragmentViewModel.class)) {
            return (T) new TrendingFragmentViewModel(trendingRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
