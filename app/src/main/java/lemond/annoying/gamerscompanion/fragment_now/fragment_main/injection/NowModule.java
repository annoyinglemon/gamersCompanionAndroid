package lemond.annoying.gamerscompanion.fragment_now.fragment_main.injection;

import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.model.HypedModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.viewmodel.HypedViewModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.viewmodel.HypedViewModelFactory;
import lemond.annoying.gamerscompanion.fragment_now.fragment_main.NowFragment;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.model.PopularModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.viewmodel.PopularViewModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.viewmodel.PopularViewModelFactory;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.model.TrendingModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.viewmodel.TrendingViewModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.viewmodel.TrendingViewModelFactory;
import lemond.annoying.gamerscompanion.repository.service.GameService;

@Module
public class NowModule {

    private final NowFragment nowFragment;

    public NowModule(NowFragment nowFragment) {
        this.nowFragment = nowFragment;
    }

    @Provides
    @NowFragmentScope
    public TrendingViewModel provideTrendingViewModel(TrendingViewModelFactory trendingViewModelFactory) {
        return ViewModelProviders.of(nowFragment, trendingViewModelFactory).get(TrendingViewModel.class);
    }

    @Provides
    @NowFragmentScope
    public PopularViewModel providePopularViewModel(PopularViewModelFactory popularViewModelFactory) {
        return ViewModelProviders.of(nowFragment, popularViewModelFactory).get(PopularViewModel.class);
    }
    
    @Provides
    @NowFragmentScope
    public HypedViewModel provideHypedViewModel(HypedViewModelFactory hypedViewModelFactory) {
        return ViewModelProviders.of(nowFragment, hypedViewModelFactory).get(HypedViewModel.class);
    }

}
