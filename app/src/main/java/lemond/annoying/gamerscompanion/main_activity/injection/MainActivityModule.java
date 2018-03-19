package lemond.annoying.gamerscompanion.main_activity.injection;

import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.main_activity.view.MainActivity;
import lemond.annoying.gamerscompanion.main_activity.viewmodel.MainActivityViewModel;

@Module
public class MainActivityModule {

    @Provides
    @MainActivityScope
    public MainActivityViewModel provideMainActivityViewModel(MainActivity mainActivity) {
        return ViewModelProviders.of(mainActivity).get(MainActivityViewModel.class);
    }

}
