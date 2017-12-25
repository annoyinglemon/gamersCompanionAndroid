package lemond.annoying.gamerscompanion.fragment_now.fragment_popular;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Inject;


public class PopularViewModelFactory implements ViewModelProvider.Factory {

    private final PopularModel popularModel;

    @Inject
    public PopularViewModelFactory(PopularModel popularModel) {
        this.popularModel = popularModel;
    }

    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PopularViewModel.class)) {
            return (T) new PopularViewModel(popularModel);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
