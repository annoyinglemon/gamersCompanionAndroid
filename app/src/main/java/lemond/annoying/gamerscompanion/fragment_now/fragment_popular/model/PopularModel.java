package lemond.annoying.gamerscompanion.fragment_now.fragment_popular.model;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import lemond.annoying.gamerscompanion.repository.service.GameService;
import lemond.annoying.gamerscompanion.repository.objects.Game;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularModel {

    private GameService gameService;

    public PopularModel(GameService gameService){
        this.gameService = gameService;
    }

    public MutableLiveData<List<Game>> getMostPopular() {
        final MutableLiveData<List<Game>> data = new MutableLiveData<>();
        gameService.getMostPopular().enqueue(new Callback<List<Game>>() {

            @Override
            public void onResponse(@NonNull Call<List<Game>> call, @NonNull Response<List<Game>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Game>> call, @NonNull Throwable t) {
                // TODO: 2017-12-25 create object with list of object result and call result enum
                data.setValue(null);
            }
        });
        return data;
    }

}
