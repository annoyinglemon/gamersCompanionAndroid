package lemond.annoying.gamerscompanion.app.module;

import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.activity.view.MainActivity;
import lemond.annoying.gamerscompanion.app.ViewControllerScope;
import lemond.annoying.gamerscompanion.fragment_news.viewmodel.NewsFragmentViewModel;
import lemond.annoying.gamerscompanion.fragment_news.viewmodel.NewsFragmentViewModelFactory;
import lemond.annoying.gamerscompanion.fragment_now.fragment_page.model.NowPageRepository;
import lemond.annoying.gamerscompanion.fragment_now.fragment_page.qualifier.HypedPage;
import lemond.annoying.gamerscompanion.fragment_now.fragment_page.qualifier.PopularPage;
import lemond.annoying.gamerscompanion.fragment_now.fragment_page.qualifier.TrendingPage;
import lemond.annoying.gamerscompanion.fragment_now.fragment_page.viewmodel.NowPageFragmentViewModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_page.viewmodel.NowPageViewModelFactory;
import lemond.annoying.gamerscompanion.repository.service.GameService;

@Module
public class MainActivityModule {

    private final MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @ViewControllerScope
    @TrendingPage
    NowPageRepository provideTrendingRepository(GameService gameService) {
        return new NowPageRepository(gameService, NowPageRepository.PageType.TRENDING);
    }

    @Provides
    @ViewControllerScope
    @PopularPage
    NowPageRepository providePopularRepository(GameService gameService) {
        return new NowPageRepository(gameService, NowPageRepository.PageType.POPULAR);
    }

    @Provides
    @ViewControllerScope
    @HypedPage
    NowPageRepository provideHypedRepository(GameService gameService) {
        return new NowPageRepository(gameService, NowPageRepository.PageType.HYPED);
    }

    @Provides
    @ViewControllerScope
    @TrendingPage
    NowPageViewModelFactory provideTrendingViewModelFactory(@TrendingPage NowPageRepository nowPageRepository) {
        return new NowPageViewModelFactory(nowPageRepository);
    }

    @Provides
    @ViewControllerScope
    @PopularPage
    NowPageViewModelFactory providePopularViewModelFactory(@PopularPage NowPageRepository nowPageRepository) {
        return new NowPageViewModelFactory(nowPageRepository);
    }

    @Provides
    @ViewControllerScope
    @HypedPage
    NowPageViewModelFactory provideHypedViewModelFactory(@HypedPage NowPageRepository nowPageRepository) {
        return new NowPageViewModelFactory(nowPageRepository);
    }

    @Provides
    @ViewControllerScope
    @TrendingPage
    NowPageFragmentViewModel provideTrendingViewModel(@TrendingPage NowPageViewModelFactory nowPageViewModelFactory) {
        return ViewModelProviders.of(mainActivity, nowPageViewModelFactory).
                get(NowPageRepository.PageType.TRENDING.toString(), NowPageFragmentViewModel.class);
    }

    @Provides
    @ViewControllerScope
    @PopularPage
    NowPageFragmentViewModel providePopularViewModel(@PopularPage NowPageViewModelFactory nowPageViewModelFactory) {
        return ViewModelProviders.of(mainActivity, nowPageViewModelFactory).
                get(NowPageRepository.PageType.POPULAR.toString(), NowPageFragmentViewModel.class);
    }

    @Provides
    @ViewControllerScope
    @HypedPage
    NowPageFragmentViewModel provideHypedViewModel(@HypedPage NowPageViewModelFactory nowPageViewModelFactory) {
        return ViewModelProviders.of(mainActivity, nowPageViewModelFactory).
                get(NowPageRepository.PageType.HYPED.toString(), NowPageFragmentViewModel.class);
    }


    @Provides
    @ViewControllerScope
    NewsFragmentViewModel provideNewsFragmentViewModel(NewsFragmentViewModelFactory newsFragmentViewModelFactory) {
        return ViewModelProviders.of(mainActivity, newsFragmentViewModelFactory).get(NewsFragmentViewModel.class);
    }

}
