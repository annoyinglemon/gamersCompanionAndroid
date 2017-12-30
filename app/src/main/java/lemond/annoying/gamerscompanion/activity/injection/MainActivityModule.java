package lemond.annoying.gamerscompanion.activity.injection;


import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.activity.view.MainActivity;
import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.model.HypedModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.viewmodel.HypedViewModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.viewmodel.HypedViewModelFactory;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.model.PopularModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.viewmodel.PopularViewModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.viewmodel.PopularViewModelFactory;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.model.TrendingModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.viewmodel.TrendingViewModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.viewmodel.TrendingViewModelFactory;
import lemond.annoying.gamerscompanion.repository.service.GameService;

@Module
public class MainActivityModule {

    private final MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @MainActivityScope
    public MainActivity provideMainActivity() {
        return mainActivity;
    }

    @Provides
    @MainActivityScope
    public TrendingModel provideTrendingModel(GameService gameService) {
        return new TrendingModel(gameService);
    }

    @Provides
    @MainActivityScope
    public TrendingViewModelFactory provideTrendingViewModelFactory(TrendingModel trendingModel) {
        return new TrendingViewModelFactory(trendingModel);
    }

    @Provides
    @MainActivityScope
    public TrendingViewModel provideTrendingViewModel(MainActivity mainActivity, TrendingViewModelFactory trendingViewModelFactory) {
        return ViewModelProviders.of(mainActivity, trendingViewModelFactory).get(TrendingViewModel.class);
    }

    @Provides
    @MainActivityScope
    public PopularModel popularModel(GameService gameService) {
        return new PopularModel(gameService);
    }

    @Provides
    @MainActivityScope
    public PopularViewModelFactory providePopularViewModelFactory(PopularModel popularModel) {
        return new PopularViewModelFactory(popularModel);
    }

    @Provides
    @MainActivityScope
    public PopularViewModel providePopularViewModel(MainActivity mainActivity, PopularViewModelFactory popularViewModelFactory) {
        return ViewModelProviders.of(mainActivity, popularViewModelFactory).get(PopularViewModel.class);
    }

    @Provides
    @MainActivityScope
    public HypedModel provideHypedModel(GameService gameService) {
        return new HypedModel(gameService);
    }

    @Provides
    @MainActivityScope
    public HypedViewModelFactory provideHypedViewModelFactory(HypedModel hypedModel) {
        return new HypedViewModelFactory(hypedModel);
    }

    @Provides
    @MainActivityScope
    public HypedViewModel provideHypedViewModel(MainActivity mainActivity, HypedViewModelFactory hypedViewModelFactory) {
        return ViewModelProviders.of(mainActivity, hypedViewModelFactory).get(HypedViewModel.class);
    }

}
