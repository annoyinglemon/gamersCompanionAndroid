package lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.viewmodel;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.model.HypedRepository;
import lemond.annoying.gamerscompanion.fragment_now.fragment_main.injection.NowFragmentScope;

@NowFragmentScope
public class HypedFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final HypedRepository hypedRepository;

    @Inject
    HypedFragmentViewModelFactory(HypedRepository hypedRepository) {
        this.hypedRepository = hypedRepository;
    }

    @Override
    @NonNull
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HypedFragmentViewModel.class)) {
            return (T) new HypedFragmentViewModel(hypedRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
