package lemond.annoying.gamerscompanion.main_activity.fragment_now.injection;

import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.model.NowPageRepository;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.qualifier.HypedPage;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.qualifier.PopularPage;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.qualifier.TrendingPage;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.viewmodel.NowPageFragmentViewModel;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.viewmodel.NowPageViewModelFactory;
import lemond.annoying.gamerscompanion.main_activity.injection.MainActivityScope;
import lemond.annoying.gamerscompanion.main_activity.view.MainActivity;
import lemond.annoying.gamerscompanion.repository.service.GameService;


@Module
public class NowFragmentModule {

    @Provides
//    @MainActivityScope
    @TrendingPage
    public NowPageRepository provideTrendingRepository(GameService gameService) {
        return new NowPageRepository(gameService, NowPageRepository.PageType.TRENDING);
    }

    @Provides
//    @MainActivityScope
    @PopularPage
    public NowPageRepository providePopularRepository(GameService gameService) {
        return new NowPageRepository(gameService, NowPageRepository.PageType.POPULAR);
    }

    @Provides
//    @MainActivityScope
    @HypedPage
    public NowPageRepository provideHypedRepository(GameService gameService) {
        return new NowPageRepository(gameService, NowPageRepository.PageType.HYPED);
    }

    @Provides
//    @MainActivityScope
    @TrendingPage
    public NowPageViewModelFactory provideTrendingViewModelFactory(@TrendingPage NowPageRepository nowPageRepository) {
        return new NowPageViewModelFactory(nowPageRepository);
    }

    @Provides
//    @MainActivityScope
    @PopularPage
    public NowPageViewModelFactory providePopularViewModelFactory(@PopularPage NowPageRepository nowPageRepository) {
        return new NowPageViewModelFactory(nowPageRepository);
    }

    @Provides
//    @MainActivityScope
    @HypedPage
    public NowPageViewModelFactory provideHypedViewModelFactory(@HypedPage NowPageRepository nowPageRepository) {
        return new NowPageViewModelFactory(nowPageRepository);
    }

    @Provides
//    @MainActivityScope
    @TrendingPage
    public NowPageFragmentViewModel provideTrendingViewModel(MainActivity mainActivity, @TrendingPage NowPageViewModelFactory nowPageViewModelFactory) {
        return ViewModelProviders.of(mainActivity, nowPageViewModelFactory).
                get(NowPageRepository.PageType.TRENDING.toString(), NowPageFragmentViewModel.class);
    }

    @Provides
//    @MainActivityScope
    @PopularPage
    public NowPageFragmentViewModel providePopularViewModel(MainActivity mainActivity, @PopularPage NowPageViewModelFactory nowPageViewModelFactory) {
        return ViewModelProviders.of(mainActivity, nowPageViewModelFactory).
                get(NowPageRepository.PageType.POPULAR.toString(), NowPageFragmentViewModel.class);
    }

    @Provides
//    @MainActivityScope
    @HypedPage
    public NowPageFragmentViewModel provideHypedViewModel(MainActivity mainActivity, @HypedPage NowPageViewModelFactory nowPageViewModelFactory) {
        return ViewModelProviders.of(mainActivity, nowPageViewModelFactory).
                get(NowPageRepository.PageType.HYPED.toString(), NowPageFragmentViewModel.class);
    }

}
