package lemond.annoying.gamerscompanion.game_activity.viewmodel

import lemond.annoying.gamerscompanion.core.viewmodel.DataFetcherViewModel
import lemond.annoying.gamerscompanion.game_activity.model.GameDetailsActivityRepository
import lemond.annoying.gamerscompanion.repository.objects.Game

class GameDetailsActivityViewModel(gameDetailsActivityRepository: GameDetailsActivityRepository) : DataFetcherViewModel<Game>(gameDetailsActivityRepository)
