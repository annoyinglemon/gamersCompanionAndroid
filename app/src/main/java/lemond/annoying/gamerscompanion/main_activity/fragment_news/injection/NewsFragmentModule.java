package lemond.annoying.gamerscompanion.main_activity.fragment_news.injection;

import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.main_activity.fragment_news.viewmodel.NewsFragmentViewModel;
import lemond.annoying.gamerscompanion.main_activity.fragment_news.viewmodel.NewsFragmentViewModelFactory;
import lemond.annoying.gamerscompanion.main_activity.injection.MainActivityScope;
import lemond.annoying.gamerscompanion.main_activity.view.MainActivity;

@Module
public class NewsFragmentModule {

    @Provides
//    @MainActivityScope
    public NewsFragmentViewModel provideNewsFragmentViewModel(MainActivity mainActivity, NewsFragmentViewModelFactory newsFragmentViewModelFactory) {
        return ViewModelProviders.of(mainActivity, newsFragmentViewModelFactory).get(NewsFragmentViewModel.class);
    }

}
