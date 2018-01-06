package lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.model;


import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.fragment_now.fragment_main.injection.NowFragmentScope;
import lemond.annoying.gamerscompanion.repository.exception.NoConnectivityException;
import lemond.annoying.gamerscompanion.repository.objects.Game;
import lemond.annoying.gamerscompanion.repository.service.DataState;
import lemond.annoying.gamerscompanion.repository.service.GameService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@NowFragmentScope
public class HypedModel {

    private GameService gameService;

    @Inject
    public HypedModel(GameService gameService) {
        this.gameService = gameService;
    }

    public MutableLiveData<DataState<List<Game>>> getHypedGames() {
        final MutableLiveData<DataState<List<Game>>> data = new MutableLiveData<>();
        gameService.getHypedGames(System.currentTimeMillis()).enqueue(new Callback<List<Game>>() {

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
