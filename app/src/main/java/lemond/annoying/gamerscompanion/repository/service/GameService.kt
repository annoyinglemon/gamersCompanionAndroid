package lemond.annoying.gamerscompanion.repository.service


import io.reactivex.Single
import lemond.annoying.gamerscompanion.repository.objects.Game
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GameService {

    @get:GET("/games/?fields=name,cover&order=popularity:asc&filter[popularity][gt]=200")
    val trendingGames: Single<List<Game>>

    @GET("/games/?fields=name,cover&order=hypes:asc&filter[hypes][gt]=99")
    fun getPopularGames(@Query("filter[first_release_date][lt]") timeNowInMilliSeconds: Long): Single<List<Game>>

    @GET("/games/?fields=name,cover&order=first_release_date:asc&filter[hypes][gt]=19")
    fun getHypedGames(@Query("filter[first_release_date][gt]") timeNowInMilliSeconds: Long): Single<List<Game>>

    //    @GET("/games/{id}?fields=name,cover,rating,rating_count,aggregated_rating,aggregated_rating_count,total_rating,summary," +
    //            "storyline,first_release_date,themes.name,platforms.name,keywords.name,genres.name,game.name,game.cover,version_parent.name,version_parent.cover," +
    //            "dlcs.name,dlcs.cover,expansions.name,expansions.cover,standalone_expansions.name,standalone_expansions.cover,collection.name," +
    //            "franchise.name,developers.name,developers.logo,developers.website,publishers.name,publishers.logo,publishers.website"+
    //            "&expand=themes,platforms,keywords,genres,game,version_parent,dlcs,expansions,standalone_expansions,games,collection.games,franchise.games,developers,publishers")
    //    Single<Game> getGameDetails(@Path("id") String gameId);
    @GET("/games/{id}?fields=name,cover,rating,rating_count,aggregated_rating,aggregated_rating_count,total_rating,summary," +
            "storyline,first_release_date,themes.name,platforms.name,genres.name" +
            "&expand=themes,platforms,genres")
    fun getGameDetails(@Path("id") gameId: String): Single<List<Game>>

}
