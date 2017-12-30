package lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.viewmodel;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.model.HypedModel;

public class HypedViewModelFactory implements ViewModelProvider.Factory {

    private final HypedModel hypedModel;

    @Inject
    public HypedViewModelFactory(HypedModel hypedModel) {
        this.hypedModel = hypedModel;
    }

    @Override
    @NonNull
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HypedViewModel.class)) {
            return (T) new HypedViewModel(hypedModel);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
