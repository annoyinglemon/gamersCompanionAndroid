package lemond.annoying.gamerscompanion.app.module;

import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.activity.view.MainActivity;
import lemond.annoying.gamerscompanion.app.ViewControllerScope;
import lemond.annoying.gamerscompanion.fragment_news.viewmodel.NewsFragmentViewModel;
import lemond.annoying.gamerscompanion.fragment_news.viewmodel.NewsFragmentViewModelFactory;
import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.viewmodel.HypedFragmentViewModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.viewmodel.HypedFragmentViewModelFactory;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.viewmodel.PopularFragmentViewModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.viewmodel.PopularViewModelFactory;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.viewmodel.TrendingFragmentViewModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.viewmodel.TrendingViewModelFactory;

@Module
public class MainActivityModule {

    private final MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @ViewControllerScope
    NewsFragmentViewModel provideNewsFragmentViewModel(NewsFragmentViewModelFactory newsFragmentViewModelFactory) {
        return ViewModelProviders.of(mainActivity, newsFragmentViewModelFactory).get(NewsFragmentViewModel.class);
    }

    @Provides
    @ViewControllerScope
    PopularFragmentViewModel providePopularFragmentViewModel(PopularViewModelFactory popularViewModelFactory) {
        return ViewModelProviders.of(mainActivity, popularViewModelFactory).get(PopularFragmentViewModel.class);
    }

    @Provides
    @ViewControllerScope
    TrendingFragmentViewModel provideTrendingFragmentViewModel(TrendingViewModelFactory trendingViewModelFactory) {
        return ViewModelProviders.of(mainActivity, trendingViewModelFactory).get(TrendingFragmentViewModel.class);
    }

    @Provides
    @ViewControllerScope
    HypedFragmentViewModel provideHypedFragmentViewModel(HypedFragmentViewModelFactory hypedFragmentViewModelFactory) {
        return ViewModelProviders.of(mainActivity, hypedFragmentViewModelFactory).get(HypedFragmentViewModel.class);
    }


}
