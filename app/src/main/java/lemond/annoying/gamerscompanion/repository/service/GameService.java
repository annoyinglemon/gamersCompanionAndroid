package lemond.annoying.gamerscompanion.repository.service;


import java.util.List;

import io.reactivex.Single;
import lemond.annoying.gamerscompanion.repository.objects.Game;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GameService {

    @GET("/games/?fields=name,cover&order=popularity:asc&filter[popularity][gt]=200")
    Single<List<Game>> getTrendingGames();

    @GET("/games/?fields=name,cover&order=hypes:asc&filter[hypes][gt]=99")
    Single<List<Game>> getPopularGames(@Query("filter[first_release_date][lt]") long timeNowInMilliSeconds);

    @GET("/games/?fields=name,cover&order=first_release_date:asc&filter[hypes][gt]=19")
    Single<List<Game>> getHypedGames(@Query("filter[first_release_date][gt]") long timeNowInMilliSeconds);

//    @GET("/games/{id}?fields=name,cover,rating,rating_count,aggregated_rating,aggregated_rating_count,total_rating,summary," +
//            "storyline,first_release_date,themes.name,platforms.name,keywords.name,genres.name,game.name,game.cover,version_parent.name,version_parent.cover," +
//            "dlcs.name,dlcs.cover,expansions.name,expansions.cover,standalone_expansions.name,standalone_expansions.cover,collection.name," +
//            "franchise.name,developers.name,developers.logo,developers.website,publishers.name,publishers.logo,publishers.website"+
//            "&expand=themes,platforms,keywords,genres,game,version_parent,dlcs,expansions,standalone_expansions,games,collection.games,franchise.games,developers,publishers")
//    Single<Game> getGameDetails(@Path("id") String gameId);
    @GET("/games/{id}?fields=name,cover,rating,rating_count,aggregated_rating,aggregated_rating_count,total_rating,summary," +
            "storyline,first_release_date,themes.name,platforms.name,genres.name" +
            "&expand=themes,platforms,genres")
    Single<List<Game>> getGameDetails(@Path("id") String gameId);

}
