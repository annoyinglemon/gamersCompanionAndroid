package lemond.annoying.gamerscompanion.main_activity.fragment_now.injection

import dagger.Module
import dagger.Provides
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.model.NowPageRepository
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.qualifier.HypedPage
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.qualifier.PopularPage
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.qualifier.TrendingPage
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.viewmodel.NowPageViewModelFactory
import lemond.annoying.gamerscompanion.repository.service.GameService


@Module
class NowFragmentModule {

    @Provides
    @NowFragmentScope
    @TrendingPage
    fun provideTrendingRepository(gameService: GameService): NowPageRepository {
        return NowPageRepository(gameService, NowPageRepository.PageType.TRENDING)
    }

    @Provides
    @NowFragmentScope
    @PopularPage
    fun providePopularRepository(gameService: GameService): NowPageRepository {
        return NowPageRepository(gameService, NowPageRepository.PageType.POPULAR)
    }

    @Provides
    @NowFragmentScope
    @HypedPage
    fun provideHypedRepository(gameService: GameService): NowPageRepository {
        return NowPageRepository(gameService, NowPageRepository.PageType.HYPED)
    }

    @Provides
    @NowFragmentScope
    @TrendingPage
    fun provideTrendingViewModelFactory(@TrendingPage nowPageRepository: NowPageRepository): NowPageViewModelFactory {
        return NowPageViewModelFactory(nowPageRepository)
    }

    @Provides
    @NowFragmentScope
    @PopularPage
    fun providePopularViewModelFactory(@PopularPage nowPageRepository: NowPageRepository): NowPageViewModelFactory {
        return NowPageViewModelFactory(nowPageRepository)
    }

    @Provides
    @NowFragmentScope
    @HypedPage
    fun provideHypedViewModelFactory(@HypedPage nowPageRepository: NowPageRepository): NowPageViewModelFactory {
        return NowPageViewModelFactory(nowPageRepository)
    }

}
