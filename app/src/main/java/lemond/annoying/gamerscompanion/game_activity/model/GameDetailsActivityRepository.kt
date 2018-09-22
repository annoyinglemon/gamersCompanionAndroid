package lemond.annoying.gamerscompanion.game_activity.model

import io.reactivex.Single
import lemond.annoying.gamerscompanion.core.model.DataRepository
import lemond.annoying.gamerscompanion.game_activity.injection.GameDetailsActivityScope
import lemond.annoying.gamerscompanion.repository.objects.Game
import lemond.annoying.gamerscompanion.repository.service.GameService
import javax.inject.Inject

@GameDetailsActivityScope
class GameDetailsActivityRepository @Inject constructor(gameService: GameService) : DataRepository<Game> {

    private var gameService : GameService? = gameService

    private var gameId : String? = null

    fun setGameId(gameId : String) {
        this.gameId = gameId
    }

    /**
     *  Fetches game details. Make sure to set the game Id first by calling GameDetailsActivityRepository.setGameId(gameId)
     **/
    override fun fetchData(): Single<Game> {
        return gameService!!.getGameDetails(gameId).flatMap {
            Single.just(it[0])
        }
    }

}