package lemond.annoying.gamerscompanion.main_activity.fragment_now.injection;

import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.model.NowPageRepository;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.qualifier.HypedPage;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.qualifier.PopularPage;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.qualifier.TrendingPage;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.viewmodel.NowPageViewModelFactory;
import lemond.annoying.gamerscompanion.repository.service.GameService;


@Module
public class NowFragmentModule {

    @Provides
    @NowFragmentScope
    @TrendingPage
    public NowPageRepository provideTrendingRepository(GameService gameService) {
        return new NowPageRepository(gameService, NowPageRepository.PageType.TRENDING);
    }

    @Provides
    @NowFragmentScope
    @PopularPage
    public NowPageRepository providePopularRepository(GameService gameService) {
        return new NowPageRepository(gameService, NowPageRepository.PageType.POPULAR);
    }

    @Provides
    @NowFragmentScope
    @HypedPage
    public NowPageRepository provideHypedRepository(GameService gameService) {
        return new NowPageRepository(gameService, NowPageRepository.PageType.HYPED);
    }

    @Provides
    @NowFragmentScope
    @TrendingPage
    public NowPageViewModelFactory provideTrendingViewModelFactory(@TrendingPage NowPageRepository nowPageRepository) {
        return new NowPageViewModelFactory(nowPageRepository);
    }

    @Provides
    @NowFragmentScope
    @PopularPage
    public NowPageViewModelFactory providePopularViewModelFactory(@PopularPage NowPageRepository nowPageRepository) {
        return new NowPageViewModelFactory(nowPageRepository);
    }

    @Provides
    @NowFragmentScope
    @HypedPage
    public NowPageViewModelFactory provideHypedViewModelFactory(@HypedPage NowPageRepository nowPageRepository) {
        return new NowPageViewModelFactory(nowPageRepository);
    }

}
