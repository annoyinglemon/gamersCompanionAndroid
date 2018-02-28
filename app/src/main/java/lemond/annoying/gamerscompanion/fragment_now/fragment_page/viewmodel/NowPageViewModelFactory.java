package lemond.annoying.gamerscompanion.fragment_now.fragment_page.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import lemond.annoying.gamerscompanion.fragment_now.fragment_page.model.NowPageRepository;

public class NowPageViewModelFactory implements ViewModelProvider.Factory {

    private final NowPageRepository nowPageRepository;

    public NowPageViewModelFactory(NowPageRepository nowPageRepository) {
        this.nowPageRepository = nowPageRepository;
    }

    @Override
    @NonNull
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NowPageFragmentViewModel.class)) {
            return (T) new NowPageFragmentViewModel(nowPageRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
