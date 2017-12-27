package lemond.annoying.gamerscompanion.fragment_now.fragment_popular.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.model.PopularModel;
import lemond.annoying.gamerscompanion.repository.objects.Game;

public class PopularViewModel extends ViewModel {

    private MutableLiveData<List<Game>> mostPopularGames;
    private PopularModel popularModel;
    private boolean isRefreshDataTriggered;

    @Inject
    public PopularViewModel(PopularModel popularModel) {
        this.popularModel = popularModel;
    }

    public void refreshData(boolean forceRefresh) {
        if (!isRefreshDataTriggered || forceRefresh) {
            mostPopularGames = popularModel.getMostPopular();
            isRefreshDataTriggered = true;
        }
    }

    public LiveData<List<Game>> getMostPopularGames() {
        if (mostPopularGames == null) {
            mostPopularGames = new MutableLiveData<>();
        }
        return this.mostPopularGames;
    }
}
