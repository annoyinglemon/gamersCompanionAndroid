package lemond.annoying.gamerscompanion.fragment_now.fragment_popular.model;


import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import lemond.annoying.gamerscompanion.repository.exception.NoConnectivityException;
import lemond.annoying.gamerscompanion.repository.objects.Game;
import lemond.annoying.gamerscompanion.repository.service.DataState;
import lemond.annoying.gamerscompanion.repository.service.GameService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularModel {

    private GameService gameService;

    public PopularModel(GameService gameService) {
        this.gameService = gameService;
    }

    public MutableLiveData<DataState<List<Game>>> getPopularGames() {
        final MutableLiveData<DataState<List<Game>>> data = new MutableLiveData<>();
        gameService.getPopularGames().enqueue(new Callback<List<Game>>() {

            @Override
            @SuppressWarnings("ConstantConditions")
            public void onResponse(@NonNull Call<List<Game>> call, @NonNull Response<List<Game>> response) {
                DataState<List<Game>> dataState = new DataState<>();
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isEmpty()) {
                        dataState.state = DataState.State.EMPTY;
                        dataState.size = 1;
                    } else {
                        dataState.data = response.body();
                        dataState.state = DataState.State.CONTENT;
                        dataState.size = response.body().size();
                    }
                } else {
                    dataState.state = DataState.State.ERROR;
                    dataState.size = 1;
                }
                data.setValue(dataState);
            }

            @Override
            public void onFailure(@NonNull Call<List<Game>> call, @NonNull Throwable t) {
                DataState<List<Game>> dataState = new DataState<>();
                if (t instanceof NoConnectivityException) {
                    dataState.state = DataState.State.NO_INTERNET;
                } else {
                    dataState.state = DataState.State.ERROR;
                }
                dataState.size = 1;
                data.setValue(dataState);
            }
        });
        return data;
    }

}
