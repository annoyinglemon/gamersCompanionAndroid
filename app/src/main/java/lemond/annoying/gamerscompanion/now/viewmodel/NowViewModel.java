package lemond.annoying.gamerscompanion.now.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.game.model.GameRepository;
import lemond.annoying.gamerscompanion.game.objects.Game;

public class NowViewModel extends ViewModel {

    private LiveData<List<Game>> mostPopularGames;
    private LiveData<List<Game>> recentlyReleasedGames;
    private LiveData<List<Game>> comingSoonGames;

    @Inject
    public NowViewModel(GameRepository gameRepository) {
        mostPopularGames = gameRepository.getMostPopular();
    }

    public LiveData<List<Game>> getMostPopularGames() {
        return mostPopularGames;
    }

    public LiveData<List<Game>> getRecentlyReleasedGames() {
        return recentlyReleasedGames;
    }

    public LiveData<List<Game>> getComingSoonGames() {
        return comingSoonGames;
    }

    public void getMostPopular() {
    }


}
