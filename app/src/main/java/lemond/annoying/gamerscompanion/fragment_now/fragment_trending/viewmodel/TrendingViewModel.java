package lemond.annoying.gamerscompanion.fragment_now.fragment_trending.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.model.TrendingModel;
import lemond.annoying.gamerscompanion.repository.objects.Game;
import lemond.annoying.gamerscompanion.repository.service.DataState;

public class TrendingViewModel extends ViewModel {

    private MutableLiveData<DataState<List<Game>>> trendingGames;
    private TrendingModel trendingModel;
    private boolean isRefreshDataTriggered;

    @Inject
    public TrendingViewModel(TrendingModel trendingModel) {
        this.trendingModel = trendingModel;
    }

    public void refreshData(boolean forceRefresh) {
        if (!isRefreshDataTriggered || forceRefresh) {
            this.trendingGames = trendingModel.getTrendingGames();
            isRefreshDataTriggered = true;
        }
    }

    public LiveData<DataState<List<Game>>> getTrendingGames() {
        if (trendingGames == null) {
            trendingGames = new MutableLiveData<>();
        }
        return this.trendingGames;
    }
}
