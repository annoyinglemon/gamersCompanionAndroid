package lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.model

import java.util.ArrayList

import io.reactivex.Single
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_main.viewmodel.GameItemViewModel
import lemond.annoying.gamerscompanion.core.model.DataRepository
import lemond.annoying.gamerscompanion.repository.service.GameService


class NowPageRepository(private val gameService: GameService, private val pageType: PageType) : DataRepository<List<GameItemViewModel>> {

    enum class PageType {
        TRENDING,
        POPULAR,
        HYPED
    }

    override fun fetchData(): Single<List<GameItemViewModel>> {
        when (pageType) {
            NowPageRepository.PageType.TRENDING -> return gameService.trendingGames.flatMap { games ->
                val gameItemViewModels = ArrayList<GameItemViewModel>()
                for (game in games) {
                    val gameItemViewModel = GameItemViewModel(game)
                    gameItemViewModels.add(gameItemViewModel)
                }
                Single.just<List<GameItemViewModel>>(gameItemViewModels)
            }
            NowPageRepository.PageType.POPULAR -> return gameService.getPopularGames(System.currentTimeMillis()).flatMap { games ->
                val gameItemViewModels = ArrayList<GameItemViewModel>()
                for (game in games) {
                    val gameItemViewModel = GameItemViewModel(game)
                    gameItemViewModels.add(gameItemViewModel)
                }
                Single.just<List<GameItemViewModel>>(gameItemViewModels)
            }
            NowPageRepository.PageType.HYPED -> return gameService.getHypedGames(System.currentTimeMillis()).flatMap { games ->
                val gameItemViewModels = ArrayList<GameItemViewModel>()
                for (game in games) {
                    val gameItemViewModel = GameItemViewModel(game)
                    gameItemViewModels.add(gameItemViewModel)
                }
                Single.just<List<GameItemViewModel>>(gameItemViewModels)
            }
            else -> return gameService.getHypedGames(System.currentTimeMillis()).flatMap { games ->
                val gameItemViewModels = ArrayList<GameItemViewModel>()
                for (game in games) {
                    val gameItemViewModel = GameItemViewModel(game)
                    gameItemViewModels.add(gameItemViewModel)
                }
                Single.just<List<GameItemViewModel>>(gameItemViewModels)
            }
        }

    }

}
