package lemond.annoying.gamerscompanion.main_activity.injection

import android.arch.lifecycle.ViewModelProviders

import dagger.Module
import dagger.Provides
import lemond.annoying.gamerscompanion.main_activity.view.MainActivity
import lemond.annoying.gamerscompanion.main_activity.viewmodel.MainActivityViewModel

@Module
class MainActivityModule {

    @Provides
    @MainActivityScope
    fun provideMainActivityViewModel(mainActivity: MainActivity): MainActivityViewModel {
        return ViewModelProviders.of(mainActivity).get(MainActivityViewModel::class.java)
    }

}
