package lemond.annoying.gamerscompanion.fragment_now.fragment_main.injection;

import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.viewmodel.HypedFragmentViewModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.viewmodel.HypedFragmentViewModelFactory;
import lemond.annoying.gamerscompanion.fragment_now.fragment_main.NowFragment;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.viewmodel.PopularFragmentViewModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.viewmodel.PopularViewModelFactory;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.viewmodel.TrendingFragmentViewModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.viewmodel.TrendingViewModelFactory;

@Module
public class NowModule {

    private final NowFragment nowFragment;

    public NowModule(NowFragment nowFragment) {
        this.nowFragment = nowFragment;
    }

    @Provides
    @NowFragmentScope
    TrendingFragmentViewModel provideTrendingFragmentViewModel(TrendingViewModelFactory trendingViewModelFactory) {
        return ViewModelProviders.of(nowFragment, trendingViewModelFactory).get(TrendingFragmentViewModel.class);
    }

    @Provides
    @NowFragmentScope
    PopularFragmentViewModel providePopularFragmentViewModel(PopularViewModelFactory popularViewModelFactory) {
        return ViewModelProviders.of(nowFragment, popularViewModelFactory).get(PopularFragmentViewModel.class);
    }
    
    @Provides
    @NowFragmentScope
    HypedFragmentViewModel provideHypedFragmentViewModel(HypedFragmentViewModelFactory hypedFragmentViewModelFactory) {
        return ViewModelProviders.of(nowFragment, hypedFragmentViewModelFactory).get(HypedFragmentViewModel.class);
    }

}
