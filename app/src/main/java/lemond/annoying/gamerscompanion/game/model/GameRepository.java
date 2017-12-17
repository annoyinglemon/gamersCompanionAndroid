package lemond.annoying.gamerscompanion.game.model;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import lemond.annoying.gamerscompanion.game.objects.Game;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class GameRepository {

    private GameService gameService;

    @Inject
    public GameRepository(GameService gameService) {
        this.gameService = gameService;
    }

    public LiveData<List<Game>> getMostPopular() {
        final MutableLiveData<List<Game>> data = new MutableLiveData<>();
        gameService.getMostPopular().enqueue(new Callback<List<Game>>() {

            @Override
            public void onResponse(@NonNull Call<List<Game>> call, @NonNull Response<List<Game>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Game>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

}
