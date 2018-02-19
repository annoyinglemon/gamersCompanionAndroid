package lemond.annoying.gamerscompanion.repository.service;


import java.util.List;

import io.reactivex.Single;
import lemond.annoying.gamerscompanion.repository.objects.Game;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GameService {

    @GET("/games/?fields=name,cover,themes.name&expand=themes&order=popularity:asc&filter[popularity][gt]=99")
    Single<List<Game>> getTrendingGames();

    @GET("/games/?fields=name,cover,themes.name&expand=themes&order=hypes:asc&filter[hypes][gt]=99")
    Single<List<Game>> getPopularGames(@Query("filter[first_release_date][lt]") long timeNowInMilliSeconds);

    @GET("/games/?fields=name,cover,themes.name&expand=themes&order=first_release_date:asc&filter[hypes][gt]=19")
    Single<List<Game>> getHypedGames(@Query("filter[first_release_date][gt]") long timeNowInMilliSeconds);

}
