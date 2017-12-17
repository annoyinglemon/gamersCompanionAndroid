package lemond.annoying.gamerscompanion;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import lemond.annoying.gamerscompanion.now.viewmodel.NowViewModel;

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NowViewModel.class)
    abstract ViewModel bindNowViewModel(NowViewModel nowViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
