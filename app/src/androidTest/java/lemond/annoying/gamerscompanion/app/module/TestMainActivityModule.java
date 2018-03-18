package lemond.annoying.gamerscompanion.app.module;

import android.arch.lifecycle.ViewModelProviders;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.main_activity.view.MainActivity;
import lemond.annoying.gamerscompanion.main_activity.viewmodel.MainActivityViewModel;
import lemond.annoying.gamerscompanion.main_activity.injection.MainActivityScope;
import lemond.annoying.gamerscompanion.main_activity.fragment_news.model.NewsRepository;
import lemond.annoying.gamerscompanion.main_activity.fragment_news.viewmodel.NewsFragmentViewModel;
import lemond.annoying.gamerscompanion.main_activity.fragment_news.viewmodel.NewsFragmentViewModelFactory;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.model.NowPageRepository;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.qualifier.HypedPage;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.qualifier.PopularPage;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.qualifier.TrendingPage;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.viewmodel.NowPageFragmentViewModel;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.viewmodel.NowPageViewModelFactory;
import lemond.annoying.gamerscompanion.repository.service.GameService;
import lemond.annoying.gamerscompanion.repository.service.NewsService;

@Module
public class TestMainActivityModule {

    private final MainActivity mainActivity;

    public TestMainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @MainActivityScope
    MainActivityViewModel provideMainActivityViewModel() {
        return ViewModelProviders.of(mainActivity).get(MainActivityViewModel.class);
    }

    @Provides
    @MainActivityScope
    @TrendingPage
    NowPageRepository provideTrendingRepository(GameService gameService) {
        return Mockito.mock(NowPageRepository.class);
    }

    @Provides
    @MainActivityScope
    @PopularPage
    NowPageRepository providePopularRepository(GameService gameService) {
        return Mockito.mock(NowPageRepository.class);
    }

    @Provides
    @MainActivityScope
    @HypedPage
    NowPageRepository provideHypedRepository(GameService gameService) {
        return Mockito.mock(NowPageRepository.class);
    }

    @Provides
    @MainActivityScope
    NewsRepository provideNewsRepository(NewsService newsService){
        return Mockito.mock(NewsRepository.class);
    }

    @Provides
    @MainActivityScope
    @TrendingPage
    NowPageViewModelFactory provideTrendingViewModelFactory(@TrendingPage NowPageRepository nowPageRepository) {
        return new NowPageViewModelFactory(nowPageRepository);
    }

    @Provides
    @MainActivityScope
    @PopularPage
    NowPageViewModelFactory providePopularViewModelFactory(@PopularPage NowPageRepository nowPageRepository) {
        return new NowPageViewModelFactory(nowPageRepository);
    }

    @Provides
    @MainActivityScope
    @HypedPage
    NowPageViewModelFactory provideHypedViewModelFactory(@HypedPage NowPageRepository nowPageRepository) {
        return new NowPageViewModelFactory(nowPageRepository);
    }

    @Provides
    @MainActivityScope
    NewsFragmentViewModelFactory provideNewsViewModelFactory(NewsRepository newsRepository) {
        return new NewsFragmentViewModelFactory(newsRepository);
    }

    @Provides
    @MainActivityScope
    @TrendingPage
    NowPageFragmentViewModel provideTrendingViewModel(@TrendingPage NowPageViewModelFactory nowPageViewModelFactory) {
        return ViewModelProviders.of(mainActivity, nowPageViewModelFactory).
                get(NowPageRepository.PageType.TRENDING.toString(), NowPageFragmentViewModel.class);
    }

    @Provides
    @MainActivityScope
    @PopularPage
    NowPageFragmentViewModel providePopularViewModel(@PopularPage NowPageViewModelFactory nowPageViewModelFactory) {
        return ViewModelProviders.of(mainActivity, nowPageViewModelFactory).
                get(NowPageRepository.PageType.POPULAR.toString(), NowPageFragmentViewModel.class);
    }

    @Provides
    @MainActivityScope
    @HypedPage
    NowPageFragmentViewModel provideHypedViewModel(@HypedPage NowPageViewModelFactory nowPageViewModelFactory) {
        return ViewModelProviders.of(mainActivity, nowPageViewModelFactory).
                get(NowPageRepository.PageType.HYPED.toString(), NowPageFragmentViewModel.class);
    }


    @Provides
    @MainActivityScope
    NewsFragmentViewModel provideNewsFragmentViewModel(NewsFragmentViewModelFactory newsFragmentViewModelFactory) {
        return ViewModelProviders.of(mainActivity, newsFragmentViewModelFactory).get(NewsFragmentViewModel.class);
    }

}
