package lemond.annoying.gamerscompanion.main_activity.injection;

import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.main_activity.fragment_news.injection.NewsFragmentComponent;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.injection.NowFragmentComponent;
import lemond.annoying.gamerscompanion.main_activity.view.MainActivity;
import lemond.annoying.gamerscompanion.main_activity.viewmodel.MainActivityViewModel;
import lemond.annoying.gamerscompanion.main_activity.fragment_news.viewmodel.NewsFragmentViewModel;
import lemond.annoying.gamerscompanion.main_activity.fragment_news.viewmodel.NewsFragmentViewModelFactory;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.model.NowPageRepository;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.qualifier.HypedPage;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.qualifier.PopularPage;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.qualifier.TrendingPage;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.viewmodel.NowPageFragmentViewModel;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.viewmodel.NowPageViewModelFactory;
import lemond.annoying.gamerscompanion.repository.service.GameService;

@Module(subcomponents = {
        NowFragmentComponent.class,
        NewsFragmentComponent.class
})
public class MainActivityModule {

    @Provides
    @MainActivityScope
    public MainActivityViewModel provideMainActivityViewModel(MainActivity mainActivity) {
        return ViewModelProviders.of(mainActivity).get(MainActivityViewModel.class);
    }

}
