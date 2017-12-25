package lemond.annoying.gamerscompanion.fragment_now.fragment_popular;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.repository.objects.Game;

public class PopularViewModel extends ViewModel {

    private MutableLiveData<List<Game>> mostPopularGames;
    private PopularModel popularModel;

    @Inject
    public PopularViewModel(PopularModel popularModel) {
        this.popularModel = popularModel;
        mostPopularGames = popularModel.getMostPopular();
    }

    public void refreshData() {
        mostPopularGames = popularModel.getMostPopular();
    }

    public LiveData<List<Game>> getMostPopularGames() {
        if (mostPopularGames == null) {
            mostPopularGames = new MutableLiveData<>();
        }
        return this.mostPopularGames;
    }
}
