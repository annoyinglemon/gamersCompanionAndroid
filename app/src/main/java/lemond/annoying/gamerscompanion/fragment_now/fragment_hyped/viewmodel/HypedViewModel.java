package lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.model.HypedModel;
import lemond.annoying.gamerscompanion.repository.objects.Game;
import lemond.annoying.gamerscompanion.repository.service.DataState;

public class HypedViewModel extends ViewModel {

    private MutableLiveData<DataState<List<Game>>> hypedGames;
    private HypedModel hypedModel;
    private boolean isDataInitialized;

    @Inject
    public HypedViewModel(HypedModel hypedModel) {
        this.hypedModel = hypedModel;
    }

    public void initializeData() {
        if (!isDataInitialized) {
            this.hypedGames = hypedModel.getHypedGames();
            isDataInitialized = true;
        }
    }

    public LiveData<DataState<List<Game>>> getHypedGames() {
        if (hypedGames == null) {
            hypedGames = new MutableLiveData<>();
        }
        return this.hypedGames;
    }
}
