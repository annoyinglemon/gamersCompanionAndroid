package lemond.annoying.gamerscompanion.fragment_now.fragment_popular.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.model.PopularModel;
import lemond.annoying.gamerscompanion.repository.objects.Game;
import lemond.annoying.gamerscompanion.repository.service.DataState;

public class PopularViewModel extends ViewModel {

    private MutableLiveData<DataState<List<Game>>> popularGames;
    private PopularModel popularModel;
    private boolean isRefreshDataTriggered;

    @Inject
    public PopularViewModel(PopularModel popularModel) {
        this.popularModel = popularModel;
    }

    public void initializeData() {
        if (!isRefreshDataTriggered) {
            this.popularGames = popularModel.getPopularGames();
            isRefreshDataTriggered = true;
        }
    }

    public LiveData<DataState<List<Game>>> getPopularGames() {
        if (popularGames == null) {
            popularGames = new MutableLiveData<>();
        }
        return this.popularGames;
    }

}
