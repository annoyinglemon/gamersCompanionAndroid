package lemond.annoying.gamerscompanion.fragment_now.fragment_trending.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.fragment_now.fragment_main.injection.NowFragmentScope;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.model.TrendingModel;


@NowFragmentScope
public class TrendingViewModelFactory implements ViewModelProvider.Factory {

    private final TrendingModel trendingModel;

    @Inject
    public TrendingViewModelFactory(TrendingModel trendingModel) {
        this.trendingModel = trendingModel;
    }

    @Override
    @NonNull
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TrendingViewModel.class)) {
            return (T) new TrendingViewModel(trendingModel);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
